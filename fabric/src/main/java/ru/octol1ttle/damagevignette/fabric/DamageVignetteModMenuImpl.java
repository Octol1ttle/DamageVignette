package ru.octol1ttle.damagevignette.fabric;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static ru.octol1ttle.damagevignette.fabric.DamageVignetteFabric.fabricConfig;

public class DamageVignetteModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(Minecraft.getInstance().screen)
                    .setTitle(new TranslatableComponent("config.damagevignette.title"))
                    .setSavingRunnable(() -> {
                        try (Writer writer = new FileWriter("config/damagevignette.json")) {
                            Gson gson = new GsonBuilder().create();
                            gson.toJson(fabricConfig, writer);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

            ConfigCategory category = builder.getOrCreateCategory(new TextComponent(""));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            category.addEntry(entryBuilder.startBooleanToggle(new TranslatableComponent("config.damagevignette.enabled"), fabricConfig.enabled)
                    .setDefaultValue(true)
                    .setSaveConsumer(newValue -> fabricConfig.enabled = newValue)
                    .build());

            category.addEntry(entryBuilder.startIntSlider(new TranslatableComponent("config.damagevignette.lowthreshold"), fabricConfig.lowThreshold, 0, 100)
                    .setDefaultValue(5)
                    .setSaveConsumer(newValue -> fabricConfig.lowThreshold = newValue)
                    .build());

            category.addEntry(entryBuilder.startIntSlider(new TranslatableComponent("config.damagevignette.highthreshold"), fabricConfig.highThreshold, 0, 100)
                    .setDefaultValue(100)
                    .setSaveConsumer(newValue -> fabricConfig.highThreshold = newValue)
                    .build());
            return builder.build();
        };
    }
}
