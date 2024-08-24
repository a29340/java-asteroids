package com.a29340.elements;

import com.a29340.core.Image;
import com.a29340.core.PlayElement;
import com.a29340.core.Velocity;

import java.awt.*;

public class Beam extends PlayElement {
    private Velocity velocity;
    private boolean hit = false;
    private static Image image = new Image("images/laser beam.png");

    public Beam(Point position, Velocity velocity) {
        this.velocity = velocity;
        this.angle = velocity.getAngle();
        setPosition(position);
    }

    @Override
    public void updatePlayElement(Graphics2D g2d) {
        Point position = getPosition();
        position.setLocation(velocity.getTargetFromPoint(position));
        drawPlayElement(g2d, image);
    }

    @Override
    public boolean shouldBeRemoved() {
        return hit || isInFrame();
    }

    @Override
    public void acceptCollision(PlayElement collided) {
        if (collided instanceof Asteroid) {
            hit = true;
        }
    }
}
