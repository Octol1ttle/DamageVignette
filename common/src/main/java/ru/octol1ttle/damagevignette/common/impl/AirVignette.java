package ru.octol1ttle.damagevignette.common.impl;

import java.util.function.Supplier;
import net.minecraft.entity.player.PlayerEntity;
import ru.octol1ttle.damagevignette.common.api.Vignette;
import ru.octol1ttle.damagevignette.common.config.VignetteSettings;

public class AirVignette extends Vignette {
    public AirVignette(Supplier<VignetteSettings> configSupplier) {
        super(configSupplier);
    }

    @Override
    public float getStrength(PlayerEntity player) {
        return (player.getMaxAir() - player.getAir()) / (float) player.getMaxAir();
    }
}
