package ru.octol1ttle.damagevignette.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import ru.octol1ttle.damagevignette.DamageVignette;

@Mixin(targets = "net.minecraftforge.client.gui.overlay.VanillaGuiOverlay")
@Pseudo
public class ForgeVanillaGuiOverlayMixin {
    @ModifyExpressionValue(method = "lambda$static$0", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;useFancyGraphics()Z"))
    private static boolean forceRenderDamageVignette(boolean vignetteAllowed) {
        return DamageVignette.shouldRenderVignette(Minecraft.getInstance(), vignetteAllowed);
    }
}
