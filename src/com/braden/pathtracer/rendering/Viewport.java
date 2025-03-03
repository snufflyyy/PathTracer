package com.braden.pathtracer;

import com.braden.pathtracer.math.Vector3;

public class Viewport {
    private float width;
    private float height;

    private final float pixelDeltaX;
    private final float pixelDeltaY;

    private final Vector3 firstPixel;

    public Viewport(int imageWidth, int imageHeight) {
        height = 1.0f;
        width = height * ((float) imageWidth / (float) imageHeight);

        pixelDeltaX = width / (float) imageWidth;
        pixelDeltaY = -height / (float) imageHeight;

        firstPixel = new Vector3((-width / 2.0f) + (pixelDeltaX / 2.0f), (height / 2.0f) + (pixelDeltaY / 2.0f), 0.0f);
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getPixelDeltaX() {
        return pixelDeltaX;
    }

    public float getPixelDeltaY() {
        return pixelDeltaY;
    }

    public Vector3 getFirstPixel() {
        return firstPixel;
    }

    public Vector3 getPixel(int x, int y) {
        float offsetX = (float) Math.random() - 0.5f;
        float offsetY = (float) Math.random() - 0.5f;
        return firstPixel.add(new Vector3(pixelDeltaX * ((float) x + offsetX), pixelDeltaY * ((float) y + offsetY), 0.0f));
    }
}
