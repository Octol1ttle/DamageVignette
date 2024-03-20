package ru.octol1ttle.damagevignette.impl;

import java.util.function.Supplier;
import net.minecraft.entity.player.PlayerEntity;
import ru.octol1ttle.damagevignette.api.Vignette;
import ru.octol1ttle.damagevignette.config.VignetteSettings;

public class AirVignette extends Vignette {
    public AirVignette(Supplier<VignetteSettings> configSupplier) {
        super(configSupplier);
    }

    @Override
    public float getStrength(PlayerEntity player) {
        return (player.getMaxAir() - player.getAir()) / (float) player.getMaxAir();
    }
}
