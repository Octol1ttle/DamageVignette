package ru.octol1ttle.damagevignette.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Final
    @Shadow
    private MinecraftClient client;

    @ModifyConstant(method = "renderVignetteOverlay", constant = @Constant(floatValue = 0.0f, ordinal = 0))
    private float healthVignetteOverlay(float f) {
        assert this.client.player != null;
        return 1 - (this.client.player.getHealth() / this.client.player.getMaxHealth());
    }
}
