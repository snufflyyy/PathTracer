package com.braden.pathtracer;

import com.braden.pathtracer.math.Color;
import com.braden.pathtracer.math.Sphere;
import com.braden.pathtracer.math.Vector3;
import com.braden.pathtracer.rendering.Camera;
import com.braden.pathtracer.rendering.Material;
import com.braden.pathtracer.utilities.Image;

import java.util.ArrayList;

public class PathTracer {
    private final Camera camera;
    private final ArrayList<Sphere> world;
    private final Image image;

    public PathTracer() {
        camera = new Camera(new Vector3(0.0f, 0.0f, 4.0f), 1.0f);
        camera.setSamplesPerPixel(100);
        camera.setMaxBounces(100);

        Material purple = new Material(new Color(1.0f, 0.0f, 1.0f), 1.0f);
        Material purpleReflective = new Material(new Color(1.0f, 0.0f, 1.0f), 0.0f);
        Material red = new Material(new Color(1.0f, 0.0f, 0.0f), 1.0f);
        Material grey = new Material(new Color(0.75f, 0.75f, 0.75f), 1.0f);

        world = new ArrayList<Sphere>();
        world.add(new Sphere(new Vector3(1.0f, 0.0f, 0.0f), 1.0f, purpleReflective));
        world.add(new Sphere(new Vector3(-1.0f, 0.0f, 0.0f), 1.0f, red));
        world.add(new Sphere(new Vector3(0.0f, -101.0f, 0.0f), 100.0f, grey));

        image = new Image(1280, 720);
        image.setPixels(camera.render(image.getWidth(), image.getHeight(), world));
        image.createOutputImageFile("output.png");
    }

    public static void main(String[] args) {
        new PathTracer();
    }
}