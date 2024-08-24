package com.a29340;

import com.a29340.core.PlayElement;
import com.a29340.core.UIElement;
import com.a29340.elements.Asteroid;
import com.a29340.elements.HealthBar;
import com.a29340.elements.Ship;
import com.a29340.utils.DebugInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.a29340.utils.Constants.FPS;
import static com.a29340.utils.Constants.FRAME_SIZE;

public class MainPanel extends JPanel {
    BufferedImage background;
    List<PlayElement> playElements = new ArrayList<>();
    List<UIElement> uiElements = new ArrayList<>();
    private static MainPanel INSTANCE;

    public MainPanel() {
        setSize(FRAME_SIZE);
        setPreferredSize(FRAME_SIZE);
        setFocusable(true);
        requestFocus();
        configureBackground();
        HealthBar healthBar = configureDashboard();
        Ship ship = configureShip(healthBar);
        configureAsteroids(ship);
        runGameLoop();
        INSTANCE = this;
    }

    private HealthBar configureDashboard() {
        HealthBar healthBar = new HealthBar();
        uiElements.add(healthBar);
        this.add(healthBar);
        return healthBar;
    }

    private void configureBackground() {
        try {
            background = ImageIO.read(getClass().getClassLoader().getResource("images/background-pixel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configureAsteroids(Ship ship) {
        Timer asteroidTimer = new Timer(1000, e -> {
            Asteroid asteroid = new Asteroid(ship.getPosition());
            playElements.add(asteroid);
            INSTANCE.add(asteroid);
        });
//        asteroidTimer.start();
    }

    private Ship configureShip(HealthBar healthBar) {
        Ship ship = new Ship(healthBar, beam -> {
            playElements.add(beam);
            INSTANCE.add(beam);
        });
        playElements.add(ship);
        this.add(ship);
        addKeyListener(ship);
        addMouseMotionListener(ship);
        addMouseListener(ship);
        return ship;
    }

    private void runGameLoop() {
        Timer timer = new Timer(1000/FPS, e -> {
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
                   DebugInfo.printDebugMessage("Collision detected between " + a.getClass().getSimpleName() + " and " + b.getClass().getSimpleName());
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
        playElements.forEach(c -> {
            c.paint(g);
            System.out.println(c);
//            c.repaint();
        });
        uiElements.forEach(e -> {
            e.paint(g);
//            e.repaint();
        });
//        List<PlayElement> elementsToRemove = playElements.stream().filter(c -> c.shouldBeRemoved()).collect(Collectors.toList());
  //      elementsToRemove.forEach(e -> this.remove(e));
        DebugInfo.print(g2d);
    }

    private void drawBackground(Graphics2D g2d) {
        if (background != null) {
            int x = 0;
            while (x < FRAME_SIZE.getWidth()) {
                int y = 0;
                while (y < FRAME_SIZE.getHeight()) {
                    g2d.drawImage(background, x, y, this);
                    y += background.getHeight();
                }
                x += background.getWidth();
            }
        }
    }
}
