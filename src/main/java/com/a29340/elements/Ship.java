package com.a29340.elements;

import com.a29340.core.Image;
import com.a29340.core.PlayElement;
import com.a29340.core.Velocity;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.function.Consumer;

import static com.a29340.utils.Constants.*;


public class Ship extends PlayElement implements MouseMotionListener, MouseInputListener, KeyListener {

    private Consumer<PlayElement> beamFunction;
    private HealthBar health;
    private Image image = new Image("images/ship-pixel.png");
    private int coolDownFrame = 0;
    private int explosionFrame = 0;

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
        if (isAlive()) {
            Point position = getPosition();
            // added these v and u to create some space between the center of the ship and the center of the beam
            int v = (int) (Math.sin(angle) * image.getWidth() * PIXEL_DIMENSION * 0.60);
            int u = (int) (Math.cos(angle) * image.getHeight() * PIXEL_DIMENSION * 0.60);
            Beam beam = new Beam(new Point(position.x + v,position.y - u),
                    new Velocity(angle, 10));
            this.beamFunction.accept(beam);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (isAlive()) {
            Point position = getPosition();
            int dx = e.getX() - (position.x);
            int dy = e.getY() - (position.y);
            angle = Math.atan2(dy, dx) + Math.PI / 2;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 's':
                this.velocity.increaseY(SHIP_VEL_STEP);
                break;
            case 'w':
                this.velocity.increaseY(-SHIP_VEL_STEP);
                break;
            case 'a':
                this.velocity.increaseX(-SHIP_VEL_STEP);
                break;
            case 'd':
                this.velocity.increaseX(+SHIP_VEL_STEP);
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
        if (isAlive()) {
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
        }
        if (explosionFrame > 0) {
            explosionFrame = explosionFrame >= SHIP_EXPLOSION_FRAMES ? 0 : explosionFrame + 1;
            drawExplosion(g2d, image, explosionFrame, SHIP_EXPLOSION_FRAMES);
        } else if (coolDownFrame > 0) {
            coolDownFrame = coolDownFrame >= SHIP_COOLDOWN_FRAMES ? 0 : coolDownFrame + 1;
            drawCooldown(g2d, image, coolDownFrame, SHIP_COOLDOWN_FRAMES);
        } else {
            drawPlayElement(g2d, image);
        }
    }

    @Override
    public boolean shouldBeRemoved() {
        return this.explosionFrame >= SHIP_EXPLOSION_FRAMES;
    }

    @Override
    public void acceptCollision(PlayElement collided) {
        if (collided instanceof Asteroid && explosionFrame == 0 && coolDownFrame == 0) {
            this.health.setHealth(this.health.getHealth() - 10);
            if (!isAlive()) {
                explosionFrame = 1;
            } else {
                coolDownFrame = 1;
            }
        }
    }

    private boolean isAlive() {
        return this.health.getHealth() > 0;
    }
}
