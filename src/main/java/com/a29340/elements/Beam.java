package com.a29340.elements;

import com.a29340.core.Entity;
import com.a29340.core.PlayElement;
import com.a29340.core.Velocity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Beam extends PlayElement {
    private Velocity velocity;
    private boolean hit = false;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(Entity.class.getClassLoader().getResource("images/laser beam.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Beam(Point position, Velocity velocity) {
        this.velocity = velocity;
        setPosition(position);
    }

    @Override
    public void update(Graphics2D graphics) {
        Point position = getPosition();
        int dx = (int) (img.getWidth() * this.velocity.getDx()/this.velocity.getModule()) + 1;
        int x2 =  position.x + dx;
        int dy = (int) (img.getWidth() * this.velocity.getDy() / this.velocity.getModule()) + 1;
        int y2 =  position.y + dy;
        position = velocity.getTargetFromPoint(position);
        setPosition(position);
        if (dx > 0 && dy > 0) {
            bounds = new Rectangle(position.x, position.y, dx , dy);
        } else if (dx < 0 && dy < 0) {
            bounds = new Rectangle(x2, y2, -dx , -dy);
        } else if (dx > 0 && dy < 0) {
            bounds = new Rectangle(position.x, y2, dx , -dy);
        } else if (dx < 0 && dy > 0) {
            bounds = new Rectangle(x2, position.y, -dx , dy);
        }
        if (img != null) {
            Graphics2D g2d = (Graphics2D) graphics.create();
            int centerX = position.x ;
            int centerY = position.y;
            // Translate to the center of the component
            g2d.translate(centerX, centerY);
            // Rotate around the center
            g2d.rotate(velocity.getAngle() - Math.PI / 2);
            g2d.drawImage(img, 0, - img.getHeight() / 2, null);
            // Translate back to the top-left corner of the component
            g2d.translate(-centerX, -centerY);
            g2d.dispose();
        }
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
