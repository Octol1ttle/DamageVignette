package ru.octol1ttle.damagevignette.client.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import ru.octol1ttle.damagevignette.client.DamageVignette;
import ru.octol1ttle.damagevignette.client.DamageVignetteConfig;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Final
    @Shadow
    private MinecraftClient client;

    @ModifyExpressionValue(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;isFancyGraphicsOrBetter()Z"))
    private boolean forceRenderDamageVignette(boolean vignetteAllowed) {
        LivingEntity entity = (LivingEntity) client.getCameraEntity();
        //noinspection DataFlowIssue
        float hpScaled = entity.getHealth() / entity.getMaxHealth() * 100.0f;
        float confLow = MathHelper.clamp(DamageVignetteConfig.CONFIG.lowThreshold, 0.0f, DamageVignetteConfig.CONFIG.highThreshold);
        float confHigh = MathHelper.clamp(DamageVignetteConfig.CONFIG.highThreshold, confLow, 100.0f);
        DamageVignette.curOpacity = (confHigh - hpScaled) / (confHigh - confLow);
        return vignetteAllowed || (DamageVignetteConfig.CONFIG.enabled && DamageVignette.curOpacity > 0.0f);
    }

    @ModifyConstant(method = "renderVignetteOverlay", constant = @Constant(floatValue = 0.0f, ordinal = 0))
    private float healthVignetteOverlay(float f) {
        return DamageVignetteConfig.CONFIG.enabled ? MathHelper.clamp(DamageVignette.curOpacity, 0.0f, 1.0f) : f;
    }
}
