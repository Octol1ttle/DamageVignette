package ru.octol1ttle.damagevignette.client.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Final
    @Shadow
    private static Identifier VIGNETTE_TEXTURE;
    @Shadow
    public float vignetteDarkness;
    @Final
    @Shadow
    private MinecraftClient client;
    @Shadow
    private int scaledWidth;
    @Shadow
    private int scaledHeight;

    /**
     * @author Octol1ttle
     * @reason fuck you
     */
    @Overwrite
    private void renderVignetteOverlay(Entity entity) {
        assert this.client.world != null;
        WorldBorder worldBorder = this.client.world.getWorldBorder();
        float f = (float) worldBorder.getDistanceInsideBorder(entity);
        double d = Math.min(worldBorder.getShrinkingSpeed() * (double) worldBorder.getWarningTime() * 1000.0, Math.abs(worldBorder.getSizeLerpTarget() - worldBorder.getSize()));
        double e = Math.max(worldBorder.getWarningBlocks(), d);
        if ((double) f < e) {
            f = 1.0F - (float) ((double) f / e);
        } else {
            assert this.client.player != null;
            f = (this.client.player.getMaxHealth() - this.client.player.getHealth()) / this.client.player.getHealth();
        }

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.ZERO, GlStateManager.DstFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
        if (f > 0.0F) {
            f = MathHelper.clamp(f, 0.0F, 1.0F);
            RenderSystem.setShaderColor(0.0F, f, f, 1.0F);
        } else {
            float g = this.vignetteDarkness;
            g = MathHelper.clamp(g, 0.0F, 1.0F);
            RenderSystem.setShaderColor(g, g, g, 1.0F);
        }

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, VIGNETTE_TEXTURE);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        bufferBuilder.vertex(0.0, this.scaledHeight, -90.0).texture(0.0F, 1.0F).next();
        bufferBuilder.vertex(this.scaledWidth, this.scaledHeight, -90.0).texture(1.0F, 1.0F).next();
        bufferBuilder.vertex(this.scaledWidth, 0.0, -90.0).texture(1.0F, 0.0F).next();
        bufferBuilder.vertex(0.0, 0.0, -90.0).texture(0.0F, 0.0F).next();
        tessellator.draw();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.defaultBlendFunc();
    }
}
