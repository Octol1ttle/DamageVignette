package ru.octol1ttle.damagevignette.client.mixin.compat;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.octol1ttle.damagevignette.client.DamageVignette;

@Mixin(targets = "net.coderbot.iris.pipeline.newshader.NewWorldRenderingPipeline", remap = false)
@Pseudo
public class IrisNewWorldRenderingPipelineMixin {

    @Inject(method = "shouldRenderVignette", at = @At("RETURN"), cancellable = true)
    public void forceVignetteRendering(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(cir.getReturnValueZ() || DamageVignette.curOpacity > 0.0f);
    }
}
