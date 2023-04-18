package ru.octol1ttle.damagevignette.fabric;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.ClientModInitializer;
import ru.octol1ttle.damagevignette.DamageVignette;
import ru.octol1ttle.damagevignette.DamageVignetteConfigProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class DamageVignetteFabric implements ClientModInitializer, DamageVignetteConfigProvider {
    protected static DamageVignetteFabricConfig fabricConfig;

    @Override
    public void onInitializeClient() {
        try (Reader reader = new FileReader("config/damagevignette.json")) {
            Gson gson = new GsonBuilder().create();
            fabricConfig = gson.fromJson(reader, DamageVignetteFabricConfig.class);
        } catch (Exception e) {
            fabricConfig = new DamageVignetteFabricConfig();
            if (!(e instanceof FileNotFoundException)) e.printStackTrace();
        }
        DamageVignette.configProvider = this;
    }

    @Override
    public boolean isModEnabled() {
        return fabricConfig.enabled;
    }

    @Override
    public int getLowThreshold() {
        return fabricConfig.lowThreshold;
    }

    @Override
    public int getHighThreshold() {
        return fabricConfig.highThreshold;
    }
}
