package com.braden.pathtracer.rendering;

import com.braden.pathtracer.math.Color;
import com.braden.pathtracer.math.Ray;
import com.braden.pathtracer.math.RayHit;
import com.braden.pathtracer.math.Vector3;

public class Material {
    private Color albedo;
    private float roughness;
    private Color emissionColor;
    private float emissionStrength;

    public Material() {
        this(new Color(), 0.0f, 0.0f);
    }

    public Material(Color albedo, float roughness) {
        this(albedo, roughness, 0.0f);
    }

    public Material(Color albedo, float roughness, float emission) {
        this.albedo = albedo;
        this.roughness = roughness;
        this.emissionColor = albedo;
        this.emissionStrength = emission;
    }

    public Color getAlbedo() {
        return albedo;
    }

    public void setAlbedo(Color albedo) {
        this.albedo = albedo;
    }

    public float getRoughness() {
        return roughness;
    }

    public void setRoughness(float roughness) {
        this.roughness = roughness;
    }

    public Color getEmissionColor() {
        return emissionColor;
    }

    public void setEmissionColor(Color emissionColor) {
        this.emissionColor = emissionColor;
    }

    public float getEmissionStrength() {
        return emissionStrength;
    }

    public void setEmissionStrength(float emissionStrength) {
        this.emissionStrength = emissionStrength;
    }

    public boolean scatter(Ray incomingRay, RayHit rayHit, Color attenuation, Color emission, Ray outcomingRay) {
        Vector3 reflectedDirection = incomingRay.getDirection().getReflected(rayHit.getNormal()).getNormalized();
        reflectedDirection = reflectedDirection.getAdded(Vector3.getRandomNormalized().getScaled(roughness));

        outcomingRay.setValues(rayHit.getHitPosition(), reflectedDirection);
        attenuation.setValues(albedo);
        emission.setValues(emissionColor.getScaled(emissionStrength));

        if (emissionStrength > 0.0f) {
            return false;
        }

        return outcomingRay.getDirection().getDotProduct(rayHit.getNormal()) > 0.0f;
    }
}