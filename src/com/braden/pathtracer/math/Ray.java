package com.braden.pathtracer.math;

public class Ray {
    private Vector3 origin;
    private Vector3 direction;

    public Ray() {
        origin = new Vector3();
        direction = new Vector3();
    }

    public Ray(Vector3 origin, Vector3 direction) {
        setValues(origin, direction);
    }

    public void setValues(Vector3 origin, Vector3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Vector3 getOrigin() {
        return origin;
    }

    public void setOrigin(Vector3 origin) {
        this.origin = origin;
    }

    public Vector3 getDirection() {
        return direction;
    }

    public void setDirection(Vector3 direction) {
        this.direction = direction;
    }

    public Vector3 at(float t) {
        return origin.getAdded(direction.getScaled(t));
    }
}
