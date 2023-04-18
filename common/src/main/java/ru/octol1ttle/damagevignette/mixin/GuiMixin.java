package ru.octol1ttle.damagevignette.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import ru.octol1ttle.damagevignette.DamageVignette;

@Mixin(Gui.class)
public class GuiMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @ModifyExpressionValue(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;useFancyGraphics()Z"))
    private boolean forceRenderDamageVignette(boolean vignetteAllowed) {
        return DamageVignette.shouldRenderVignette(minecraft, vignetteAllowed);
    }

    @ModifyConstant(method = "renderVignette", constant = @Constant(floatValue = 0.0f, ordinal = 0))
    private float healthVignetteOverlay(float f) {
        return DamageVignette.configProvider.isModEnabled() ? Mth.clamp(DamageVignette.curOpacity, 0.0f, 1.0f) : f;
    }
}
