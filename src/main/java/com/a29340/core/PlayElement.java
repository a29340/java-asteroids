package com.a29340.core;

import java.awt.*;

import static com.a29340.utils.Constants.FRAME_SIZE;

public abstract class PlayElement extends Entity {
    protected Velocity velocity = new Velocity();
    protected Rectangle bounds = new Rectangle();
    public abstract boolean shouldBeRemoved();
    public abstract void acceptCollision(PlayElement collided);

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    protected boolean isInFrame() {
        Point position = this.getPosition();
        return position.x > FRAME_SIZE.width + 300
                || position.x < - 300
                || position.y > FRAME_SIZE.height + 300
                || position.y < - 300;
    }
}