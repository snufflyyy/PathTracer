package com.braden.pathtracer.math;

public class Color {
    private float red, green, blue;

    public Color() {
        setValues(0.0f, 0.0f, 0.0f);
    }

    public Color(float red, float green, float blue) {
        setValues(red, green, blue);
    }

    public float[] getValues() {
        return new float[] {red, green, blue};
    }

    public void setValues(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void setValues(Color color) {
        this.red = color.red;
        this.green = color.green;
        this.blue = color.blue;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    public Color getAdded(Color color) {
        return new Color(red + color.getRed(), green + color.getGreen(), blue + color.getBlue());
    }

    public Color getMultiplied(Color color) {
        return new Color(this.red * color.getRed(), this.green * color.getGreen(), this.blue * color.getBlue());
    }

    public Color getScaled(float scalar) {
        return new Color(red * scalar, green * scalar, blue * scalar);
    }

    public Color getGammaCorrected() {
        return new Color(linearToGamma(red), linearToGamma(green), linearToGamma(blue));
    }

    private float linearToGamma(float component) {
        if (component < 0.0f) {
            return 0.0f;
        }

        return (float) Math.sqrt(component);
    }

    public int getRGB() {
        return (0xFF << 24) | (((int) (red * 255.0f) & 0xFF) << 16) | (((int) (green * 255.0f) & 0xFF) << 8) | ((int) (blue * 255.0f) & 0xFF);
    }

    public static Color lerp(Color a, Color b, float t) {
        // lerp formula: (1.0f - t) * a + t * b;
        float red   = (1.0f - t) * a.getRed() + t * b.getRed();
        float green = (1.0f - t) * a.getGreen() + t * b.getGreen();
        float blue  = (1.0f - t) * a.getBlue() + t * b.getBlue();

        return new Color(red, green, blue);
    }
}