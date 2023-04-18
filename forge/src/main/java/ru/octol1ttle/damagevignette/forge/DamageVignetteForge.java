package ru.octol1ttle.damagevignette.forge;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import ru.octol1ttle.damagevignette.DamageVignette;
import ru.octol1ttle.damagevignette.DamageVignetteConfigProvider;

@Mod(DamageVignette.MOD_ID)
public class DamageVignetteForge implements DamageVignetteConfigProvider {
    private static ForgeConfigSpec.BooleanValue enabled;
    private static ForgeConfigSpec.IntValue lowThreshold;
    private static ForgeConfigSpec.IntValue highThreshold;
    private static final ForgeConfigSpec CONFIG = buildConfig();

    public DamageVignetteForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CONFIG);
        DamageVignette.configProvider = this;
    }

    private static ForgeConfigSpec buildConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.translation("config.damagevignette.title");

        enabled = builder.translation("config.damagevignette.enabled").define("enabled", true);
        lowThreshold = builder.translation("config.damagevignette.lowthreshold").defineInRange("lowThreshold", 5, 0, 100);
        highThreshold = builder.translation("config.damagevignette.highthreshold").defineInRange("highThreshold", 100, 0, 100);

        return builder.build();
    }

    @Override
    public boolean isModEnabled() {
        return enabled.get();
    }

    @Override
    public int getLowThreshold() {
        return lowThreshold.get();
    }

    @Override
    public int getHighThreshold() {
        return highThreshold.get();
    }
}
