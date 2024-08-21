package com.a29340.core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Image {

    private int frame = 0;
    private BufferedImage image;
    private List<Color[]> frames = new ArrayList<>();

    public Image(String path) {
        try {
            this.image = ImageIO.read(getClass().getClassLoader().getResource(path));
            Color[] firstFrame = new Color[image.getWidth() * image.getHeight()];
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    int[] pixel = this.image.getData().getPixel(i, j, (int[]) null);
                    Color color = new Color(pixel[0], pixel[1], pixel[2], pixel[3]);
                    firstFrame[i * image.getHeight() + j] = color;
                }
            }
            frames.add(firstFrame);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Color[] getColors() {
        Color[] colors = frames.get(frame);
        if (frame < frames.size() - 1) {
            frame = frame + 1;
        } else {
            frame = 0;
        }
        return colors;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }
}
