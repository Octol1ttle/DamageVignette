package ru.octol1ttle.damagevignette.client.mixin.compat;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import ru.octol1ttle.damagevignette.client.DamageVignette;

@Mixin(targets = "net.coderbot.iris.pipeline.newshader.NewWorldRenderingPipeline", remap = false)
@Pseudo
public class IrisNewWorldRenderingPipelineMixin {
    @ModifyReturnValue(method = "shouldRenderVignette", at = @At("RETURN"))
    public boolean forceVignetteRendering(boolean original) {
        return original || DamageVignette.curOpacity > 0.0f;
    }
}
