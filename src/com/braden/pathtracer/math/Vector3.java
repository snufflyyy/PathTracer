package com.braden.pathtracer.math;

public class Vector3 {
    private float x, y, z;

    public Vector3() {
        setValues(0.0f, 0.0f, 0.0f);
    }

    public Vector3(float x, float y, float z) {
        setValues(x, y, z);
    }

    public float[] getValues() {
        return new float[] {x, y, z};
    }

    public void setValues(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Vector3 getAdded(Vector3 vector) {
        return new Vector3(this.x + vector.getX(), this.y + vector.getY(), this.z + vector.getZ());
    }

    public Vector3 getSubtracted(Vector3 vector) {
        return new Vector3(this.x - vector.getX(), this.y - vector.getY(), this.z - vector.getZ());
    }

    public Vector3 getMultiplied(Vector3 vector) {
        return new Vector3(this.x * vector.getX(), this.y * vector.getY(), this.z * vector.getZ());
    }

    public Vector3 getDivided(Vector3 vector) {
        return new Vector3(this.x / vector.getX(), this.y / vector.getY(), this.z / vector.getZ());
    }

    public Vector3 getScaled(float scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public float getLengthSquared() {
        return (x * x) + (y * y) + (z * z);
    }

    public float getLength() {
        return (float) Math.sqrt(getLengthSquared());
    }

    public Vector3 getNormalized() {
        float length = getLength();
        return new Vector3(x / length, y / length, z / length);
    }

    public float getDotProduct(Vector3 vector) {
        return (this.x * vector.x) + (this.y * vector.y) + (this.z * vector.z);
    }

    public Vector3 getReflected(Vector3 normal) {
        return this.getSubtracted(normal.getScaled(this.getDotProduct(normal) * 2.0f));
    }

    public static Vector3 getRandom(float min, float max) {
        float x = (float) (Math.random() * (max - min + 1)) + min;
        float y = (float) (Math.random() * (max - min + 1)) + min;
        float z = (float) (Math.random() * (max - min + 1)) + min;

        return new Vector3(x, y, z);
    }

    public static Vector3 getRandomNormalized() {
        while (true) {
            Vector3 vector = getRandom(-1.0f, 1.0f);
            float lengthSquared = vector.getLengthSquared();
            if (lengthSquared >= 0.001f && lengthSquared <= 1.0f) {
                return vector.getScaled(1 / (float) Math.sqrt(lengthSquared));
            }
        }
    }

    public static Vector3 getRandomOnHemisphere(Vector3 normal) {
        Vector3 random = getRandomNormalized();

        if (random.getDotProduct(normal) < 0.0f) {
            random = random.getScaled(-1.0f);
        }

        return random;
    }
}
