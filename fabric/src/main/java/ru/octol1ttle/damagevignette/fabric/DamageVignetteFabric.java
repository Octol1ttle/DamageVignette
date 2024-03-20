package ru.octol1ttle.damagevignette.fabric;

import net.fabricmc.api.ModInitializer;
import ru.octol1ttle.damagevignette.DamageVignetteCommon;

public class DamageVignetteFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        DamageVignetteCommon.init();
    }
}
