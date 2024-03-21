package ru.octol1ttle.damagevignette.common.api;

import net.minecraft.entity.player.PlayerEntity;

public interface IVignetteStrengthProvider {
    float getStrength(PlayerEntity player);
}
