package ru.octol1ttle.damagevignette;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class DamageVignette {
    public static final String MOD_ID = "damagevignette";
    public static DamageVignetteConfigProvider configProvider;
    public static float curOpacity;

    public static boolean shouldRenderVignette(Minecraft minecraft, boolean vignetteAllowed) {
        LivingEntity entity = (LivingEntity) minecraft.cameraEntity;
        //noinspection DataFlowIssue
        float hpScaled = entity.getHealth() / entity.getMaxHealth() * 100.0f;
        float confLow = Mth.clamp(DamageVignette.configProvider.getLowThreshold(), 0.0f, DamageVignette.configProvider.getHighThreshold());
        float confHigh = Mth.clamp(DamageVignette.configProvider.getHighThreshold(), confLow, 100.0f);
        DamageVignette.curOpacity = (confHigh - hpScaled) / (confHigh - confLow);
        return vignetteAllowed || DamageVignette.configProvider.isModEnabled() && DamageVignette.curOpacity > 0.0f;
    }
}
