package ru.octol1ttle.damagevignette.config;

import dev.isxander.yacl3.config.v2.api.SerialEntry;
import java.awt.Color;
import net.minecraft.util.math.MathHelper;

public class VignetteSettings {
    @SerialEntry
    public Color color;

    @SerialEntry
    public float minimumStrengthThreshold = 0.0f;

    @SerialEntry
    public float maximumStrengthThreshold = 1.0f;

    public VignetteSettings(Color color) {
        this.color = color;
    }

    public float scale(float strength) {
        float min = MathHelper.clamp(this.minimumStrengthThreshold, 0.0f, maximumStrengthThreshold);
        float max = MathHelper.clamp(this.maximumStrengthThreshold, min, 1.0f);

        return MathHelper.clamp((strength - min) / (max - min), 0.0f, 1.0f);
    }
}
