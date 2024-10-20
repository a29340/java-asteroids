package com.a29340;

import com.a29340.core.PlayElement;
import com.a29340.core.Scene;
import com.a29340.core.UIElement;
import com.a29340.elements.*;
import com.a29340.utils.DebugInfo;
import com.a29340.utils.GameComponents;

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
    StageService stageService = new StageService();
    GameComponents gcm = new GameComponents();

    public MainPanel() {
        setSize(FRAME_SIZE);
        setPreferredSize(FRAME_SIZE);
        setFocusable(true);
        requestFocus();
        configureBackground();
        Scene gamePlay = new Scene(this::setupGame, () -> {
            detectCollision();
            repaint();
        }, () -> gcm.getHealthBar().getHealth() <= 0);
        Scene displayScore = new Scene(() -> {
            uiElements = uiElements.stream().filter(el -> !el.equals(gcm.getHealthBar()))
                    .collect(Collectors.toList());
        }, () -> {
            detectCollision();
            repaint();
        }, () -> false);
        stageService.addScene(gamePlay);
        stageService.addScene(displayScore);
        stageService.start();
        // show starting screen
        // run game until health > 0
        // show end page
    }

    private void setupGame() {
        configureDashboard();
        ScoreService scoreService = new ScoreService();
        gcm.setScoreService(scoreService);
        uiElements.add(scoreService);
        configureShip();
        configureAsteroids();
    }

    private HealthBar configureDashboard() {
        HealthBar healthBar = new HealthBar();
        uiElements.add(healthBar);
        gcm.setHealthBar(healthBar);
        return healthBar;
    }

    private void configureBackground() {
        try {
            background = ImageIO.read(getClass().getClassLoader().getResource("images/background-pixel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configureAsteroids() {
        Timer asteroidTimer = new Timer(1000, e -> {
            playElements.add(new Asteroid(gcm.getShip().getPosition()));
        });
        asteroidTimer.start();
    }

    private Ship configureShip() {
        Ship ship = new Ship(gcm.getHealthBar(), beam -> {
            playElements.add(beam);
        });
        playElements.add(ship);
        gcm.setShip(ship);
        addKeyListener(ship);
        addMouseMotionListener(ship);
        addMouseListener(ship);
        return ship;
    }

    private void detectCollision() {
        for (int i = 0; i < playElements.size(); i++) {
            for (int j = i; j < playElements.size(); j++) {
                PlayElement a = playElements.get(i);
                PlayElement b = playElements.get(j);
                if (a!=b && a.getBounds().intersects(b.getBounds()))  {
                   a.acceptCollision(b);
                   b.acceptCollision(a);
                   gcm.getScoreService().processCollision(a,b);
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
            c.update(g2d);
        });
        uiElements.forEach(e -> {
            e.update(g2d);
        });
        playElements = playElements.stream().filter(c -> !c.shouldBeRemoved()).collect(Collectors.toList());
        DebugInfo.print(g2d);
        g2d.dispose();
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
