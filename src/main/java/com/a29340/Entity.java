package com.a29340;

import java.awt.*;

import static com.a29340.Constants.FRAME_SIZE;

public abstract class Entity {
    protected Velocity velocity = new Velocity();
    protected Point position = new Point();
    protected Rectangle bounds = new Rectangle();

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        this.position.x = x;
        this.position.y = y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    protected boolean isInFrame() {
        return position.x > FRAME_SIZE.width + 300
                || position.x < - 300
                || position.y > FRAME_SIZE.height + 300
                || position.y < -300;
    }

    public abstract void update(Graphics2D g2d);

    public abstract boolean shouldBeRemoved();

    public abstract void acceptCollision();
}
