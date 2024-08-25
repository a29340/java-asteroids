package com.a29340.utils;

import com.a29340.core.Image;
import com.a29340.core.Pixel;
import com.a29340.core.Velocity;

import java.awt.*;

import static com.a29340.utils.Constants.*;

public final class Graphics {


    private static void drawPixel(Graphics2D g2d, Point position, Color color) {
        g2d.setColor(color);
        g2d.fillRect(position.x, position.y, PIXEL_DIMENSION + 1, PIXEL_DIMENSION + 1);
    }

    public static void drawImage(Graphics2D g2d, Image image, Point position) {
        int height = image.getHeight();
        int width = image.getWidth();
        Pixel[] pixels = image.getPixels(0);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Point pixelPosition = new Point(position.x + i * PIXEL_DIMENSION, position.y + j * PIXEL_DIMENSION);
                int index = i * height + j;
                Color color = pixels[index].getColor();
                drawPixel(g2d, pixelPosition, color);
            }
        }
    }

    public static void drawExplosion(Graphics2D g2d, Image image, Point position, int frame, int numFrame) {
        int height = image.getHeight();
        int width = image.getWidth();
        float perc = frame/(float) numFrame;
        Pixel[] pixels = image.getPixels(0);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int index = i * height + j;
                Pixel pixel = pixels[index];
                if (pixel.getVelocity() == null) {
                    pixel.setVelocity(new Velocity((Math.random() - 0.5) * DISPERSION_RATE, (Math.random() - 0.5) * DISPERSION_RATE));
                }
                int vx = (int) (pixel.getVelocity().getDx() * perc);
                int vy = (int) (pixel.getVelocity().getDy() * perc);
                Point pixelPosition = new Point(position.x + i * PIXEL_DIMENSION + vx, position.y + j * PIXEL_DIMENSION + vy);
                Color color = pixel.getColor();
                int newAlpha = (int) (color.getAlpha() * (1 - perc));
                drawPixel(g2d, pixelPosition, new Color(color.getRed(), color.getGreen(), color.getBlue(), newAlpha));
            }
        }
    }

    public static void drawCooldown(Graphics2D g2d, Image image, Point position, int frame, int numFrame) {
        int height = image.getHeight();
        int width = image.getWidth();
        Pixel[] pixels = image.getPixels(0);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int index = i * height + j;
                Pixel pixel = pixels[index];
                Point pixelPosition = new Point(position.x + i * PIXEL_DIMENSION, position.y + j * PIXEL_DIMENSION);                Color color = pixel.getColor();
                int red = frame % 10 < 5 ? color.getRed() : Math.min(color.getRed() + 150, 255);
                drawPixel(g2d, pixelPosition, new Color(red, color.getGreen(), color.getBlue(), color.getAlpha()));
            }
        }
    }
}
