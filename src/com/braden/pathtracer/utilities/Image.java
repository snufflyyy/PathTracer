package com.braden.pathtracer.utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private int width;
    private int height;
    private int[] pixels;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void setPixels(int[] pixels) {
        this.pixels = pixels;
    }

    public void createOutputImageFile(String path) {
        BufferedImage image = createOutputImage();

        try {
            File outputFile = new File(path);
            if (outputFile.createNewFile()) {
                System.out.println("INFO: Created output file!");
            }

            ImageIO.write(image, "png", outputFile);
            System.out.println("INFO: Saved to output file!");
        } catch (IOException e) {
            System.out.println("ERROR: Failed to save or create output image file!");
            throw new RuntimeException(e);
        }
    }

    private BufferedImage createOutputImage() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        image.setRGB(0 ,0, width, height, pixels, 0, width);

        return image;
    }
}
