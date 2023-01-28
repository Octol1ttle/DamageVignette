package ru.octol1ttle.damagevignette.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.Reader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class DamageVignette implements ClientModInitializer {
    public static float curOpacity;
    @Override
    public void onInitializeClient() {
        try (Reader reader = new FileReader("config/damagevignette.json")) {
            Gson gson = new GsonBuilder().create();
            DamageVignetteConfig.CONFIG = gson.fromJson(reader, DamageVignetteConfig.class);
        } catch (Exception e) {
            DamageVignetteConfig.CONFIG = new DamageVignetteConfig();
            e.printStackTrace();
        }
    }
}
