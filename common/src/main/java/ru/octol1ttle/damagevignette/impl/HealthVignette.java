package ru.octol1ttle.damagevignette.impl;

import java.util.function.Supplier;
import net.minecraft.entity.player.PlayerEntity;
import ru.octol1ttle.damagevignette.api.Vignette;
import ru.octol1ttle.damagevignette.config.VignetteSettings;

public class HealthVignette extends Vignette {
    public HealthVignette(Supplier<VignetteSettings> configSupplier) {
        super(configSupplier);
    }

    @Override
    public float getStrength(PlayerEntity player) {
        return (player.getMaxHealth() - player.getHealth()) / player.getMaxHealth();
    }
}
