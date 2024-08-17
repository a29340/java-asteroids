package com.a29340;

import com.a29340.core.PlayElement;
import com.a29340.core.UIElement;
import com.a29340.elements.Asteroid;
import com.a29340.elements.HealthBar;
import com.a29340.elements.Ship;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.a29340.utils.Constants.FRAME_SIZE;

public class MainPanel extends JPanel {
    BufferedImage background;
    List<PlayElement> playElements = new ArrayList<>();
    List<UIElement> uiElements = new ArrayList<>();
    long lastRepaint = System.currentTimeMillis();

    public MainPanel() {
        setSize(FRAME_SIZE);
        setPreferredSize(FRAME_SIZE);
        setFocusable(true);
        requestFocus();
        configureBackground();
        HealthBar healthBar = configureDashboard();
        Ship ship = configureShip(healthBar);
        configureAsteroids(ship);
        configureCollisions();
    }

    private HealthBar configureDashboard() {
        HealthBar healthBar = new HealthBar();
        uiElements.add(healthBar);
        return healthBar;
    }

    private void configureBackground() {
        try {
            background = ImageIO.read(getClass().getClassLoader().getResource("images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configureAsteroids(Ship ship) {
        Timer asteroidTimer = new Timer(1000, e -> {
            playElements.add(new Asteroid(ship.getPosition()));
        });
        asteroidTimer.start();
    }

    private Ship configureShip(HealthBar healthBar) {
        Ship ship = new Ship(healthBar, beam -> {
            playElements.add(beam);
        });
        playElements.add(ship);
        addKeyListener(ship);
        addMouseMotionListener(ship);
        addMouseListener(ship);
        return ship;
    }

    private void configureCollisions() {
        Timer timer = new Timer(16, e -> {
            detectCollision();
            repaint();
        });
        timer.start();
    }

    private void detectCollision() {
        for (int i = 0; i < playElements.size(); i++) {
            for (int j = i; j < playElements.size(); j++) {
                PlayElement a = playElements.get(i);
                PlayElement b = playElements.get(j);
                if (a!=b && a.getBounds().intersects(b.getBounds()))  {
                   a.acceptCollision(b);
                   b.acceptCollision(a);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        long now = System.currentTimeMillis();
        System.out.println("FPS: " + 1000/(now - lastRepaint));
        lastRepaint = now;
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (background != null) {
            g2d.drawImage(background, 0, 0, this);
        }
        playElements.forEach(c -> {
            c.update(g2d);
        });
        uiElements.forEach(e -> {
            e.update(g2d);
        });
        playElements = playElements.stream().filter(c -> !c.shouldBeRemoved()).collect(Collectors.toList());
        g2d.dispose();
    }
}
