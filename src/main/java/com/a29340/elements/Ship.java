package com.a29340.elements;

import com.a29340.core.Image;
import com.a29340.core.PlayElement;
import com.a29340.core.Velocity;
import com.a29340.utils.Graphics;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

import static com.a29340.utils.Constants.FRAME_SIZE;
import static com.a29340.utils.Constants.PIXEL_DIMENSION;


public class Ship extends PlayElement implements MouseMotionListener, MouseInputListener, KeyListener {
    private static final int VEL_STEP = 5;

    BufferedImage img;
    // Angle in rads
    private double angle = 0;
    private Consumer<PlayElement> beamFunction;
    private HealthBar health;
    private Image image = new Image("images/ship-pixel.png");

    public Ship(HealthBar health, Consumer<PlayElement> beamFunction) {
        this.health = health;
        this.beamFunction = beamFunction;
        Timer slowDownTimer = new Timer(150, e -> {
            if (velocity.getModule() > 0) {
                velocity.increaseX((int) (-velocity.getDx() / velocity.getModule()));
                velocity.increaseY((int) (-velocity.getDy() / velocity.getModule()));
            } else {
                velocity.increaseX(0);
                velocity.increaseY(0);
            }
        });
        getPosition().setLocation(FRAME_SIZE.width / 2, FRAME_SIZE.height / 2);
        slowDownTimer.start();
    }

    private void fireBeam() {
        Point position = getPosition();
        Beam beam = new Beam(new Point(position.x, position.y), new Velocity(angle, 10));
        this.beamFunction.accept(beam);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point position = getPosition();
        int dx = e.getX() - (position.x);
        int dy = e.getY() - (position.y);
        angle = Math.atan2(dy, dx) + Math.PI / 2;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 's':
                this.velocity.increaseY(VEL_STEP);
                break;
            case 'w':
                this.velocity.increaseY(-VEL_STEP);
                break;
            case 'a':
                this.velocity.increaseX(-VEL_STEP);
                break;
            case 'd':
                this.velocity.increaseX(+VEL_STEP);
                break;
            case ' ':
                fireBeam();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        fireBeam();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void updatePlayElement(Graphics2D g2d) {
        Point position = getPosition();
        position.setLocation(velocity.getTargetFromPoint(position));
        if (position.x < 0) {
            position.x = 0;
            velocity.setDx(0);
        }
        if (position.y < 0) {
            position.y = 0;
            velocity.setDy(0);
        }
        if (position.x > FRAME_SIZE.width) {
            position.x = FRAME_SIZE.width;
            velocity.setDx(0);
        }
        if (position.y > FRAME_SIZE.height) {
            position.y = FRAME_SIZE.height;
            velocity.setDy(0);
        }
        drawPlayElement(g2d, image, angle);
    }

    @Override
    public boolean shouldBeRemoved() {
        return this.health.getHealth() < 1;
    }

    @Override
    public void acceptCollision(PlayElement collided) {
        if (collided instanceof Asteroid) {
            this.health.setHealth(this.health.getHealth() - 10);
        }
    }
}
