package com.a29340.elements;

import com.a29340.core.Image;
import com.a29340.core.PlayElement;
import com.a29340.core.Velocity;

import java.awt.*;

import static com.a29340.utils.Constants.ASTEROID_EXPLOSION_FRAMES;
import static com.a29340.utils.Constants.FRAME_SIZE;

public class Asteroid extends PlayElement {
    private boolean hit = false;
    private int frame = 0;
    private double scale = 1;
    private double dgamma;
    private static Image image = new Image("images/asteroid.png");

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
        this.scale = Math.random() + 0.5;
        this.dgamma = Math.random() / 10;
        setPosition(randomPosition);
        this.velocity = velocity;
    }

    @Override
    public void updatePlayElement(Graphics2D g2d) {
        g2d.scale(scale, scale);
        if (!hit) {
            angle += dgamma;
            drawPlayElement(g2d, image);
        } else {
            frame += 1;
            drawExplosion(g2d, image, frame, ASTEROID_EXPLOSION_FRAMES);
        }
    }

    @Override
    public boolean shouldBeRemoved() {
        return frame == ASTEROID_EXPLOSION_FRAMES || isInFrame();
    }

    @Override
    public void acceptCollision(PlayElement collided) {
        hit = true;
    }
}
