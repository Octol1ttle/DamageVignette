package ru.octol1ttle.damagevignette.fabric.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.octol1ttle.damagevignette.common.DamageVignetteEvents;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V", shift = At.Shift.AFTER, ordinal = 0))
    private void onVignetteRender(DrawContext context, float tickDelta, CallbackInfo ci) {
        DamageVignetteEvents.RENDER_HUD.renderHud(context, tickDelta);
    }
}
