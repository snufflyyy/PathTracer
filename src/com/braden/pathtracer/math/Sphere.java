package com.braden.pathtracer.math;

import com.braden.pathtracer.rendering.Material;

public class Sphere {
    private Vector3 center;
    private float radius;
    private Material material;

    public Sphere(Vector3 center, float radius, Material material) {
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    public Vector3 getCenter() {
        return center;
    }

    public void setCenter(Vector3 center) {
        this.center = center;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public boolean rayHit(Ray ray, float min, float max, RayHit rayHit) {
        Vector3 originToCenter = center.getSubtracted(ray.getOrigin());
        float a = ray.getDirection().getLengthSquared();
        float h = ray.getDirection().getDotProduct(originToCenter);
        float c = originToCenter.getLengthSquared() - (radius * radius);

        float discriminant = (h * h) - (a * c);
        if (discriminant < 0.0f) {
            return false;
        }

        float sqrtDiscriminant = (float) Math.sqrt(discriminant);

        float root = (h - sqrtDiscriminant) / a;
        if (root <= min || root >= max) {
            root = (h + sqrtDiscriminant) / a;
            if (root <= min || root >= max) {
                return false;
            }
        }

        rayHit.setT(root);
        rayHit.setHitPosition(ray.at(root));

        Vector3 normal = rayHit.getHitPosition().getSubtracted(center).getNormalized();
        if (ray.getDirection().getDotProduct(normal) > 0.0f) {
            normal = normal.getScaled(-1.0f);
        }
        rayHit.setNormal(normal);

        rayHit.setMaterial(material);

        return true;
    }
}
