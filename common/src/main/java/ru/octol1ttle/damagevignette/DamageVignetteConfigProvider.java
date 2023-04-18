package ru.octol1ttle.damagevignette;

public interface DamageVignetteConfigProvider {
    boolean isModEnabled();

    int getLowThreshold();

    int getHighThreshold();
}
