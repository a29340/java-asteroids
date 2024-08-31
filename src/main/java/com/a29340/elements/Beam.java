package com.a29340.elements;

import com.a29340.core.Image;
import com.a29340.core.PlayElement;
import com.a29340.core.Velocity;

import java.awt.*;

import static com.a29340.utils.Constants.ASTEROID_EXPLOSION_FRAMES;

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
        if (frame > 0) {
            drawExplosion(g2d, image, ASTEROID_EXPLOSION_FRAMES);
        } else {
            drawPlayElement(g2d, image);
        }
    }

    @Override
    public boolean shouldBeRemoved() {
        return (hit && (frame >=  ASTEROID_EXPLOSION_FRAMES)) || isInFrame();
    }

    @Override
    public void acceptCollision(PlayElement collided) {
        if (collided instanceof Asteroid) {
            if (hit == false) {
                hit = true;
                frame = 1;
                velocity.setDx(0);
                velocity.setDy(0);
            }
        }
    }
}
