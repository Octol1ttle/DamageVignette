package ru.octol1ttle.damagevignette.impl;

import java.util.function.Supplier;
import net.minecraft.entity.player.PlayerEntity;
import ru.octol1ttle.damagevignette.api.Vignette;
import ru.octol1ttle.damagevignette.config.VignetteSettings;

public class HungerVignette extends Vignette {
    public HungerVignette(Supplier<VignetteSettings> configSupplier) {
        super(configSupplier);
    }

    @Override
    public float getStrength(PlayerEntity player) {
        return (20.0f - player.getHungerManager().getFoodLevel()) / 20.0f;
    }
}
