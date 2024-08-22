package com.a29340.elements;

import com.a29340.core.Image;
import com.a29340.core.PlayElement;
import com.a29340.core.Velocity;
import com.a29340.utils.Graphics;

import java.awt.*;

import static com.a29340.utils.Constants.*;
import static com.a29340.utils.Constants.PIXEL_DIMENSION;

public class Asteroid extends PlayElement {
    private boolean hit = false;
    private int frame = 0;
    private final int animationFrames = 1 * FPS;
    private double scale = 1;
    private double gamma = 0;
    private double dgamma;
    private static Image image;
    static {
      image = new Image("images/asteroid.png");
    }

    public Asteroid(Point target) {
        double side = Math.random();
        Point randomPosition;
        if (side < 0.25) {
            randomPosition = new Point(- 250, (int) (Math.random() * FRAME_SIZE.height));
        } else if (side < 0.5) {
            randomPosition = new Point((int) (Math.random() * FRAME_SIZE.width), - 250);
        } else if (side < 75) {
            randomPosition = new Point(FRAME_SIZE.width + 250, (int) (Math.random() * FRAME_SIZE.height));
        } else {
            randomPosition = new Point((int) (Math.random() * FRAME_SIZE.width), FRAME_SIZE.height + 250);
        }
        Velocity velocity = new Velocity((target.x - randomPosition.x)/100, (target.y - randomPosition.y)/100);
//        this.scale = Math.random() + 0.5;
        this.dgamma = Math.random() / 10;
        setPosition(randomPosition);
        this.velocity = velocity;
    }

    public Asteroid(Point position, Velocity velocity, double scale, double dgamma) {
        this.scale = scale;
        this.dgamma = dgamma;
        setPosition(position);
        this.velocity = velocity;
    }

    @Override
    public void updatePlayElement(Graphics2D g2d) {
        g2d.scale(scale, scale);
        gamma += dgamma;
        if (!hit) {
            drawPlayElement(g2d, image, gamma);
        } else {
            frame += 1;
            Point position = getPosition();
            position.setLocation(velocity.getTargetFromPoint(position));
            // Translate to the center of the component
            g2d.translate(position.x, position.y);
            // Rotate around the center
            g2d.rotate(gamma);
            Graphics.drawExplosion(g2d, image, new Point(-PIXEL_DIMENSION * image.getWidth() / 2, -PIXEL_DIMENSION * image.getHeight() / 2), frame, animationFrames);
            g2d.translate(-position.x, -position.y);
            bounds.setBounds(position.x - PIXEL_DIMENSION * image.getWidth() / 2, position.y - PIXEL_DIMENSION * image.getHeight() / 2, PIXEL_DIMENSION * image.getWidth(), PIXEL_DIMENSION * image.getHeight());
        }
    }

    @Override
    public boolean shouldBeRemoved() {
        return frame == animationFrames || isInFrame();
    }

    @Override
    public void acceptCollision(PlayElement collided) {
        hit = true;
    }
}
