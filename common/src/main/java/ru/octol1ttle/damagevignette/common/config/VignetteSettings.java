package ru.octol1ttle.damagevignette.common.config;

import java.awt.Color;
import net.minecraft.util.math.MathHelper;

public class VignetteSettings {
    public Color color;

    public float minimumStrengthThreshold = 0.0f;

    public float maximumStrengthThreshold = 1.0f;

    public VignetteSettings(Color color) {
        this.color = color;
    }

    public float scale(float strength) {
        strength = MathHelper.clamp(strength, 0.0f, 1.0f);

        float min = MathHelper.clamp(this.minimumStrengthThreshold, 0.0f, maximumStrengthThreshold);
        float max = MathHelper.clamp(this.maximumStrengthThreshold, min, 1.0f);

        return MathHelper.clamp((strength - min) / (max - min), 0.0f, 1.0f);
    }
}
