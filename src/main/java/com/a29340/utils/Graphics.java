package com.a29340.utils;

import com.a29340.core.Image;

import java.awt.*;

import static com.a29340.utils.Constants.PIXEL_DIMENSION;

public final class Graphics {

    private static void drawPixel(Graphics2D g2d, Point position, Color color) {
        g2d.setColor(color);
        g2d.fillRect(position.x, position.y, PIXEL_DIMENSION + 1, PIXEL_DIMENSION + 1);
    }

    public static void drawImage(Graphics2D g2d, Image image, Point position) {
        int height = image.getHeight();
        int width = image.getWidth();
        Color[] colors = image.getColors();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Point pixelPosition = new Point(position.x + i * PIXEL_DIMENSION, position.y + j * PIXEL_DIMENSION);
                int index = i * height + j;
                Color color = colors[index];
                drawPixel(g2d, pixelPosition, color);
            }
        }
    }
}
