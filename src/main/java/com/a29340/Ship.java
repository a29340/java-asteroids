package com.a29340;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.function.Consumer;

import static com.a29340.Constants.FRAME_SIZE;


public class Ship extends Entity implements MouseMotionListener, MouseInputListener, KeyListener {
    private static final int VEL_STEP = 5;

    BufferedImage img;
    // Angle in rads
    private double angle = 0;
    private Consumer<Entity> beamFunction;


    public Ship(Consumer<Entity> beamFunction) {
        try {
            this.img = ImageIO.read(getClass().getClassLoader().getResource("images/ship.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.beamFunction = beamFunction;
        Timer slowDownTimer = new Timer(150, e -> {
            if (velocity.getModule() > 0) {
                velocity.increaseX((int) (- velocity.getDx()/velocity.getModule()));
                velocity.increaseY((int) (- velocity.getDy()/velocity.getModule()));
            } else {
                velocity.increaseX(0);
                velocity.increaseY(0);
            }
        });
        position.setLocation(FRAME_SIZE.width/2, FRAME_SIZE.height/2);
        slowDownTimer.start();
    }
    private void fireBeam() {
        Beam beam = new Beam(new Point(position.x, position.y), new Velocity(angle, 10));
        this.beamFunction.accept(beam);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int dx = e.getX() - (position.x) ;
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
                this.velocity.increaseX( - VEL_STEP);
                break;
            case 'd':
                this.velocity.increaseX( + VEL_STEP);
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
    public void update(Graphics2D graphics) {
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
        Graphics2D g2d = (Graphics2D) graphics.create();
        if (img != null) {
            int centerX = position.x ;
            int centerY = position.y;
            // Translate to the center of the component
            g2d.translate(centerX, centerY);
            // Rotate around the center
            g2d.rotate(angle);
            g2d.drawImage(img, - img.getWidth() / 2, - img.getHeight() / 2, null);
            // Translate back to the top-left corner of the component
            g2d.translate(-centerX, -centerY);
            bounds.setBounds(position.x - img.getWidth()/2, position.y - img.getHeight()/2, img.getWidth(), img.getHeight());
        }
        g2d.dispose();
    }

    @Override
    public boolean shouldBeRemoved() {
        return false;
    }

    @Override
    public void acceptCollision() {

    }
}
