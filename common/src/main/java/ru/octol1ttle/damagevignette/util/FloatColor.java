package ru.octol1ttle.damagevignette.util;

import org.apache.commons.lang3.Validate;

public class FloatColor {
    private boolean invert = false;
    private final float red;
    private final float green;
    private final float blue;
    private final float alpha;

    public FloatColor(float red, float green, float blue, float alpha) {
        Validate.inclusiveBetween(0.0f, 1.0f, red);
        this.red = red;

        Validate.inclusiveBetween(0.0f, 1.0f, green);
        this.green = green;

        Validate.inclusiveBetween(0.0f, 1.0f, blue);
        this.blue = blue;

        Validate.inclusiveBetween(0.0f, 1.0f, alpha);
        this.alpha = alpha;
    }

    public FloatColor invert() {
        invert = !invert;
        return this;
    }

    public float getRed() {
        return invertIfNeeded(red);
    }

    public float getGreen() {
        return invertIfNeeded(green);
    }

    public float getBlue() {
        return invertIfNeeded(blue);
    }

    public float getAlpha() {
        return alpha;
    }

    private float invertIfNeeded(float component) {
        return invert ? 1.0f - component : component;
    }
}
