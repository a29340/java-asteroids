package com.a29340.core;

import com.a29340.utils.Configurations;
import com.a29340.utils.Graphics;

import java.awt.*;

import static com.a29340.utils.Constants.*;

public abstract class PlayElement extends Entity {
    protected Velocity velocity = new Velocity();
    protected Rectangle bounds = new Rectangle();
    protected double angle = 0;

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
                || position.x < -300
                || position.y > FRAME_SIZE.height + 300
                || position.y < -300;
    }

    @Override
    public void update(Graphics2D g2d) {
        Graphics2D g2d1 = (Graphics2D) g2d.create();
        updatePlayElement(g2d1);
        if (Configurations.debugMode()) {
            g2d1.setColor(Color.RED);
            g2d1.draw(bounds);
        }
        g2d1.dispose();
    }

    public abstract void updatePlayElement(Graphics2D g2d);

    protected void drawPlayElement(Graphics2D g2d, Image image) {
        Point position = transform(g2d);
        Graphics.drawImage(g2d, image, new Point(-PIXEL_DIMENSION * image.getWidth() / 2, -PIXEL_DIMENSION * image.getHeight() / 2));
        untrasform(g2d, image, position);
    }

    protected void drawExplosion(Graphics2D g2d, Image image, int frame, int explosionFrame) {
        Point position = transform(g2d);
        Graphics.drawExplosion(g2d, image, new Point(-PIXEL_DIMENSION * image.getWidth() / 2, -PIXEL_DIMENSION * image.getHeight() / 2), frame, explosionFrame);
        untrasform(g2d, image, position);
    }

    protected void drawCooldown(Graphics2D g2d, Image image, int frame, int cooldownFrame) {
        Point position = transform(g2d);
        Graphics.drawCooldown(g2d, image, new Point(-PIXEL_DIMENSION * image.getWidth() / 2, -PIXEL_DIMENSION * image.getHeight() / 2), frame, cooldownFrame);
        untrasform(g2d, image, position);
    }

    private Point transform(Graphics2D g2d) {
        Point position = getPosition();
        position.setLocation(velocity.getTargetFromPoint(position));
        // Translate to the center of the component
        g2d.translate(position.x, position.y);
        // Rotate around the center
        g2d.rotate(angle);
        return position;
    }

    private void untrasform(Graphics2D g2d, Image image, Point position) {
        g2d.translate(-position.x, -position.y);
        bounds.setBounds(position.x - PIXEL_DIMENSION * image.getWidth() / 2,
                position.y - PIXEL_DIMENSION * image.getHeight() / 2,
                PIXEL_DIMENSION * image.getWidth(),
                PIXEL_DIMENSION * image.getHeight());
    }
}