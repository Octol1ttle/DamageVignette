package ru.octol1ttle.damagevignette.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import static ru.octol1ttle.damagevignette.client.DamageVignetteConfig.CONFIG;

public class DamageVignetteModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(MinecraftClient.getInstance().currentScreen)
                    .setTitle(Text.translatable("config.damagevignette.title"))
                    .setSavingRunnable(() -> {
                        try (Writer writer = new FileWriter("config/damagevignette.json")) {
                            Gson gson = new GsonBuilder().create();
                            gson.toJson(CONFIG, writer);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

            ConfigCategory category = builder.getOrCreateCategory(Text.of(""));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            category.addEntry(entryBuilder.startIntSlider(Text.translatable("config.damagevignette.minthreshold"), CONFIG.minThreshold, 0, 100)
                    .setDefaultValue(0)
                    .setSaveConsumer(newValue -> CONFIG.minThreshold = newValue)
                    .build());

            category.addEntry(entryBuilder.startIntSlider(Text.translatable("config.damagevignette.maxthreshold"), CONFIG.maxThreshold, 0, 100)
                    .setDefaultValue(100)
                    .setSaveConsumer(newValue -> CONFIG.maxThreshold = newValue)
                    .build());
            return builder.build();
        };
    }
}
