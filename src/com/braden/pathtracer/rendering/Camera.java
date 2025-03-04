package com.braden.pathtracer.rendering;

import com.braden.pathtracer.math.*;

import java.util.ArrayList;

public class Camera {
    private Vector3 position;
    private float focalLength;
    private int samplesPerPixel;
    private int maxBounces;

    public Camera(Vector3 position, float focalLength) {
        this.position = position;
        this.focalLength = focalLength;
        samplesPerPixel = 10;
        maxBounces = 10;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public float getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(float focalLength) {
        this.focalLength = focalLength;
    }

    public int getSamplesPerPixel() {
        return samplesPerPixel;
    }

    public void setSamplesPerPixel(int samplesPerPixel) {
        this.samplesPerPixel = samplesPerPixel;
    }

    public int getMaxBounces() {
        return maxBounces;
    }

    public void setMaxBounces(int maxBounces) {
        this.maxBounces = maxBounces;
    }

    public int[] render(int imageWidth, int imageHeight, ArrayList<Sphere> world) {
        int[] pixels = new int[imageWidth * imageHeight];
        Viewport viewport = new Viewport(imageWidth, imageHeight);

        long startTime = System.currentTimeMillis();
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                Color pixelColor = new Color();

                for (int i = 0; i < samplesPerPixel; i++) {
                    Vector3 currentPixel = position.getAdded(viewport.getPixel(x, y).getSubtracted(new Vector3(0.0f, 0.0f, focalLength)));
                    Ray ray = new Ray(position, currentPixel.getSubtracted(position));

                    pixelColor = pixelColor.getAdded(getColor(ray, world, maxBounces));
                }

                pixels[y * imageWidth + x] = pixelColor.getScaled(1.0f / samplesPerPixel).getGammaCorrected().getRGB();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("INFO: Render took " + (endTime - startTime) + " milliseconds");

        return pixels;
    }

    public Color getColor(Ray ray, ArrayList<Sphere> world, int bounceCount) {
        if (bounceCount <= 0) {
            return new Color();
        }

        RayHit rayHit = new RayHit();

        float min = 0.001f;
        float max = Float.MAX_VALUE;
        boolean hitAnything = false;

        for (Sphere sphere : world) {
            if (sphere.rayHit(ray, min, max, rayHit)) {
                if (max > rayHit.getT()) { max = rayHit.getT(); }
                hitAnything = true;
            }
        }

        if (hitAnything) {
            Ray outcomingRay = new Ray();
            Color attenuation = new Color();
            Color emissive = new Color();

            if (rayHit.getMaterial().scatter(ray, rayHit, attenuation, emissive, outcomingRay)) {
                return emissive.getAdded(attenuation.getMultiplied(getColor(outcomingRay, world, bounceCount - 1)));
            }

            return emissive;
        }

        // sky gradiant
        Vector3 rayDirectionNormalized = ray.getDirection().getNormalized();
        float t = (rayDirectionNormalized.getY() + 1.0f) / 2.0f;
        return Color.lerp(new Color(1.0f, 1.0f, 1.0f), new Color(0.5f, 0.7f, 1.0f), t);
    }
}