package ru.octol1ttle.damagevignette.common.impl;

import java.util.function.Supplier;
import net.minecraft.entity.player.PlayerEntity;
import ru.octol1ttle.damagevignette.common.api.Vignette;
import ru.octol1ttle.damagevignette.common.config.VignetteSettings;

public class HealthVignette extends Vignette {
    public HealthVignette(Supplier<VignetteSettings> configSupplier) {
        super(configSupplier);
    }

    @Override
    public float getStrength(PlayerEntity player) {
        return (player.getMaxHealth() - player.getHealth()) / player.getMaxHealth();
    }
}
