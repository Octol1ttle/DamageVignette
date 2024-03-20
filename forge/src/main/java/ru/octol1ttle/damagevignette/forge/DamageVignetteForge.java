package ru.octol1ttle.damagevignette.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ru.octol1ttle.damagevignette.DamageVignetteCommon;

@SuppressWarnings("unused")
@Mod(DamageVignetteCommon.MOD_ID)
public class DamageVignetteForge {
    public DamageVignetteForge() {
        // Submit our event bus to let architectury register our content on the right time
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(DamageVignetteCommon.MOD_ID, modEventBus);

        DamageVignetteCommon.init();
    }
}
