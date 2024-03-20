package ru.octol1ttle.damagevignette.api;

import java.util.function.Supplier;
import ru.octol1ttle.damagevignette.config.VignetteSettings;

public abstract class Vignette implements IVignetteStrengthProvider {
    private final Supplier<VignetteSettings> settingsSupplier;

    public Vignette(Supplier<VignetteSettings> settingsSupplier) {
        this.settingsSupplier = settingsSupplier;
    }

    public Supplier<VignetteSettings> settingsSupplier() {
        return settingsSupplier;
    }
}
