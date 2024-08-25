package com.a29340.core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Image {

    private BufferedImage image;
    private List<Pixel[]> frames = new ArrayList<>();

    public Image(String path) {
        try {
            this.image = ImageIO.read(getClass().getClassLoader().getResource(path));
            Pixel[] firstFrame = new Pixel[image.getWidth() * image.getHeight()];
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    int[] rgba = this.image.getData().getPixel(i, j, (int[]) null);
                    Pixel pixel = new Pixel(new Color(rgba[0], rgba[1], rgba[2], rgba[3]));
                    firstFrame[i * image.getHeight() + j] = pixel;
                }
            }
            frames.add(firstFrame);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Pixel[] getPixels(int frame) {
        Pixel[] colors = frames.get(frame);
        return colors;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }
}
