package ru.octol1ttle.damagevignette.api;

import net.minecraft.entity.player.PlayerEntity;

public interface IVignetteStrengthProvider {
    float getStrength(PlayerEntity player);
}
