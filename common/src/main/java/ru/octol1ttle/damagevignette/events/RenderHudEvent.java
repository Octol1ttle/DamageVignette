package ru.octol1ttle.damagevignette.events;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.architectury.event.events.client.ClientGuiEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import ru.octol1ttle.damagevignette.DamageVignetteCommon;
import ru.octol1ttle.damagevignette.api.Vignette;
import ru.octol1ttle.damagevignette.config.DamageVignetteConfig;
import ru.octol1ttle.damagevignette.config.VignetteSettings;
import ru.octol1ttle.damagevignette.util.FloatColor;

public class RenderHudEvent implements ClientGuiEvent.RenderHud {
    private static final Identifier VIGNETTE_TEXTURE = new Identifier("minecraft", "textures/misc/vignette.png");

    @Override
    public void renderHud(DrawContext graphics, float tickDelta) {
        if (!DamageVignetteConfig.get().enabled) {
            return;
        }

        FloatColor color = computeVignetteColor();
        if (color == null) {
            return;
        }

        // Prepare
        RenderSystem.enableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        // TODO: THIS SHIT DOESNT WORK LMAOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO I HATE THIUS FUCKING GAME
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_COLOR, GlStateManager.DstFactor.DST_COLOR, GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.DST_ALPHA);

        // Draw
        drawVignette(graphics, color);

        // Cleanup
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
    }

    private void drawVignette(DrawContext graphics, FloatColor color) {
        int width = graphics.getScaledWindowWidth();
        int height = graphics.getScaledWindowHeight();

        RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
        RenderSystem.setShaderTexture(0, RenderHudEvent.VIGNETTE_TEXTURE);

        Matrix4f matrix4f = graphics.getMatrices().peek().getPositionMatrix();
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        bufferBuilder.vertex(matrix4f, 0, 0, -90).texture(0.0f, 0.0f).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).next();
        bufferBuilder.vertex(matrix4f, 0, height, -90).texture(0.0f, 1.0f).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).next();
        bufferBuilder.vertex(matrix4f, width, height, -90).texture(1.0f, 1.0f).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).next();
        bufferBuilder.vertex(matrix4f, width, 0, -90).texture(1.0f, 0.0f).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).next();

        BufferRenderer.drawWithGlobalProgram(bufferBuilder.end());
    }

    private @Nullable FloatColor computeVignetteColor() {
        VignetteSettings activeSettings = null;
        float maxStrength = 0.0f;
        for (Vignette vignette : DamageVignetteCommon.vignettes) {
            VignetteSettings settings = vignette.settingsSupplier().get();
            float strength = settings.scale(vignette.getStrength(MinecraftClient.getInstance().player));

            if (strength > maxStrength) {
                activeSettings = settings;
                maxStrength = strength;
            }
        }

        if (activeSettings == null) {
            return null;
        }

        return new FloatColor(
                activeSettings.color.getRed() / 255.0f * maxStrength,
                activeSettings.color.getGreen() / 255.0f * maxStrength,
                activeSettings.color.getBlue() / 255.0f * maxStrength,
                maxStrength
        );
    }
}
