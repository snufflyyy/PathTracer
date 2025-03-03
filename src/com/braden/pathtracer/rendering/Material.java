package com.braden.pathtracer.rendering;

import com.braden.pathtracer.math.Color;
import com.braden.pathtracer.math.Ray;
import com.braden.pathtracer.math.RayHit;
import com.braden.pathtracer.math.Vector3;

public class Material {
    private Color albedo;
    private float roughness;

    public Material() {
        albedo = new Color();
        roughness = 0.0f;
    }

    public Material(Color albedo, float roughness) {
        this.albedo = albedo;
        this.roughness = roughness;
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

    public boolean scatter(Ray incomingRay, RayHit rayHit, Color attenuation, Ray outcomingRay) {
        Vector3 reflectedDirection = incomingRay.getDirection().getReflected(rayHit.getNormal()).getNormalized();
        reflectedDirection = reflectedDirection.getAdded(Vector3.getRandomNormalized().getScaled(roughness));

        outcomingRay.setValues(rayHit.getHitPosition(), reflectedDirection);
        attenuation.setValues(albedo);

        return outcomingRay.getDirection().getDotProduct(rayHit.getNormal()) > 0.0f;
    }
}