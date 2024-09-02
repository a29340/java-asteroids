package com.a29340.core;

import com.a29340.utils.Configurations;
import com.a29340.utils.Graphics;

import java.awt.*;

import static com.a29340.utils.Constants.*;

public abstract class PlayElement extends Entity {
    protected Velocity velocity = new Velocity();
    protected Rectangle bounds = new Rectangle();
    protected double angle = 0;
    protected double scale = 1;
    protected int frame = 0;

    public abstract boolean shouldBeRemoved();

    public abstract void acceptCollision(PlayElement collided);

    public Rectangle getBounds() {
        return bounds;
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
            g2d.setColor(Color.RED);
            g2d.draw(bounds);
        }
        g2d1.dispose();
    }

    public abstract void updatePlayElement(Graphics2D g2d);

    protected void drawPlayElement(Graphics2D g2d, Image image) {
        Point position = transform(g2d, image);
        Graphics.drawImage(g2d, image, new Point(-PIXEL_DIMENSION * image.getWidth() / 2, -PIXEL_DIMENSION * image.getHeight() / 2));
        g2d.translate(-position.x, -position.y);
    }

    protected void drawExplosion(Graphics2D g2d, Image image, int explosionFrame) {
        Point position = transform(g2d, image);
        Graphics.drawExplosion(g2d, image, new Point(-PIXEL_DIMENSION * image.getWidth() / 2, -PIXEL_DIMENSION * image.getHeight() / 2), frame, explosionFrame);
        g2d.translate(-position.x, -position.y);
        frame = frame >= explosionFrame ? 0 : frame + 1;
    }

    protected void drawCooldown(Graphics2D g2d, Image image, int cooldownFrame) {
        Point position = transform(g2d, image);
        Graphics.drawCooldown(g2d, image, new Point(-PIXEL_DIMENSION * image.getWidth() / 2, -PIXEL_DIMENSION * image.getHeight() / 2), frame, cooldownFrame);
        g2d.translate(-position.x, -position.y);
        frame = frame >= cooldownFrame ? 0 : frame + 1;
    }

    private Point transform(Graphics2D g2d, Image image) {
        Point position = getPosition();
        position.setLocation(velocity.getTargetFromPoint(position));
        bounds.setBounds(
                (int) (position.x - (scale * PIXEL_DIMENSION * image.getWidth() / 2)),
                (int) (position.y - (scale * PIXEL_DIMENSION * image.getHeight() / 2)),
                (int) (PIXEL_DIMENSION * image.getWidth() * scale),
                (int) (PIXEL_DIMENSION * image.getHeight() * scale)
        );
        // Translate to the center of the component
        g2d.translate(position.x, position.y);
        g2d.scale(scale, scale);
        // Rotate around the center
        g2d.rotate(angle);
        return position;
    }

    public int getFrame() {
        return frame;
    }
}