package ru.octol1ttle.damagevignette;

import java.util.List;
import ru.octol1ttle.damagevignette.api.Vignette;
import ru.octol1ttle.damagevignette.config.DamageVignetteConfig;
import ru.octol1ttle.damagevignette.impl.AirVignette;
import ru.octol1ttle.damagevignette.impl.HealthVignette;
import ru.octol1ttle.damagevignette.impl.HungerVignette;

public class DamageVignetteCommon {
    public static final String MOD_ID = "damagevignette";
    public static final List<Vignette> vignettes = List.of(
            new HealthVignette(() -> DamageVignetteConfig.get().damageVignette),
            new HungerVignette(() -> DamageVignetteConfig.get().hungerVignette),
            new AirVignette(() -> DamageVignetteConfig.get().airVignette)
    );

    public static void init() {
        DamageVignetteConfig.load();
        DamageVignetteEvents.register();
    }
}
