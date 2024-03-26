package ru.octol1ttle.damagevignette.common;

import java.util.List;
import ru.octol1ttle.damagevignette.common.api.Vignette;
import ru.octol1ttle.damagevignette.common.config.DamageVignetteConfig;
import ru.octol1ttle.damagevignette.common.impl.HealthVignette;

public class DamageVignetteCommon {
    public static final String MOD_ID = "damagevignette";
    public static final List<Vignette> vignettes = List.of(
            new HealthVignette(() -> DamageVignetteConfig.get().damageVignette)/*,
            new HungerVignette(() -> DamageVignetteConfig.get().hungerVignette),
            new AirVignette(() -> DamageVignetteConfig.get().airVignette)*/
    );

    public static void init() {
        DamageVignetteConfig.load();
    }
}
