package com.a29340.elements;

import com.a29340.core.Image;
import com.a29340.core.PlayElement;
import com.a29340.core.Velocity;

import java.awt.*;

import static com.a29340.utils.Constants.*;

public class Asteroid extends PlayElement {
    private boolean hit = false;
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
        Velocity velocity = new Velocity((target.x - randomPosition.x)*60/(FPS * 100), (target.y - randomPosition.y)*60/(FPS*100));
        this.scale = Math.random() + 0.5;
        this.dgamma = Math.random() / 10;
        setPosition(randomPosition);
        this.velocity = velocity;
    }

    @Override
    public void updatePlayElement(Graphics2D g2d) {
        if (!hit) {
            angle += dgamma;
            drawPlayElement(g2d, image);
        } else {
            drawExplosion(g2d, image, ASTEROID_EXPLOSION_FRAMES);
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
