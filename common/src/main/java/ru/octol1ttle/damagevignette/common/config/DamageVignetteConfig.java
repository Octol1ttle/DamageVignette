package ru.octol1ttle.damagevignette.common.config;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import java.awt.Color;
import net.minecraft.util.Identifier;
import ru.octol1ttle.damagevignette.common.DamageVignetteCommon;

public class DamageVignetteConfig {
    private static final ConfigClassHandler<DamageVignetteConfig> HANDLER = ConfigClassHandler.createBuilder(DamageVignetteConfig.class)
            .id(new Identifier(DamageVignetteCommon.MOD_ID, "main"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(YACLPlatform.getConfigDir().resolve("%s/main.json5".formatted(DamageVignetteCommon.MOD_ID)))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting) // not needed, pretty print by default
                    .setJson5(true)
                    .build())
            .build();

    @SerialEntry
    public boolean enabled = true;
    @SerialEntry
    public VignetteSettings damageVignette = new VignetteSettings(Color.RED);
    @SerialEntry
    public VignetteSettings hungerVignette = new VignetteSettings(Color.BLACK);
    @SerialEntry
    public VignetteSettings airVignette = new VignetteSettings(Color.WHITE);

    public static void load() {
        HANDLER.serializer().load();
    }

    public static DamageVignetteConfig get() {
        return HANDLER.instance();
    }
}
