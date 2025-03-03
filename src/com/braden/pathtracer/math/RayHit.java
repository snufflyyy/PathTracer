package com.braden.pathtracer.math;

import com.braden.pathtracer.rendering.Material;

public class RayHit {
    private float t;
    private Vector3 hitPosition;
    private Vector3 normal;
    private Material material;

    public RayHit() {
        t = 0.0f;
        hitPosition = new Vector3();
        normal = new Vector3();
        material = new Material();
    }

    public float getT() {
        return t;
    }

    public void setT(float t) {
        this.t = t;
    }

    public Vector3 getHitPosition() {
        return hitPosition;
    }

    public void setHitPosition(Vector3 hitPosition) {
        this.hitPosition = hitPosition;
    }

    public Vector3 getNormal() {
        return normal;
    }

    public void setNormal(Vector3 normal) {
        this.normal = normal;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
