package com.a29340.elements;

import com.a29340.core.Entity;
import com.a29340.core.PlayElement;
import com.a29340.core.Velocity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.a29340.utils.Constants.FRAME_SIZE;

public class Asteroid extends PlayElement {
    private boolean hit = false;
    private float animation = 50;
    private double scale;
    private double gamma = 0;
    private double dgamma;
    private static BufferedImage img;

    static {
        try {
            img = ImageIO.read(Entity.class.getClassLoader().getResource("images/asteroid.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        this.scale = Math.random() + 0.1;
        this.dgamma = Math.random() / 10;
        setPosition(randomPosition);
        this.velocity = velocity;
    }

    @Override
    public void update(Graphics2D graphics) {
        Graphics2D g2d = (Graphics2D) graphics.create();
        gamma += dgamma;
        Point position = velocity.getTargetFromPoint(getPosition());
        setPosition(position);
        bounds = new Rectangle(position.x - (int) (img.getWidth() * scale)/2,
                position.y - (int) (img.getHeight() * scale)/2,
                (int) (img.getWidth() * scale),
                (int) (img.getHeight() * scale));
        // Translate to the center of the component
        if (!hit) {
            g2d.translate(position.x, position.y);
            g2d.scale(scale, scale);
            // Rotate around the center
            g2d.rotate(gamma);
            g2d.drawImage(img, - img.getWidth()/2, - img.getHeight() / 2, null);
        } else {
            g2d.setColor(Color.RED);
            scale = scale * (animation/50);
            int x = position.x - (int) (img.getWidth() * scale) / 2;
            int y = position.y - (int) (img.getHeight() * scale) / 2;
            g2d.draw(new Polygon(
                    new int[]{ x, x, x + (int) (img.getWidth() * scale), x + (int) (img.getWidth() * scale)},
                    new int[]{ y, y + (int) (img.getHeight() * scale), y + (int) (img.getHeight() * scale), y},
                    4));
            animation--;
        }
        g2d.dispose();
    }

    @Override
    public boolean shouldBeRemoved() {
        return animation < 0 || isInFrame();
    }

    @Override
    public void acceptCollision(PlayElement collided) {
        if (!hit) {
            hit = true;
            velocity.setDx(0);
            velocity.setDy(0);
        }
    }
}
