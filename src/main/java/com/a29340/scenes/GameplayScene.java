package com.a29340.scenes;

import com.a29340.core.PlayElement;
import com.a29340.core.Scene;
import com.a29340.elements.*;
import com.a29340.utils.DebugInfo;

import javax.swing.*;
import java.util.stream.Collectors;

public class GameplayScene extends Scene {
    Ship ship;
    public GameplayScene(Runnable repaint) {
        setup = this::setupGame;
        scene = () -> {
            detectCollision();
            playElements = playElements.stream()
                    .filter(c -> !c.shouldBeRemoved())
                    .collect(Collectors.toList());
            repaint.run();
        };
        ended = () -> HealthBar.getInstance().getHealth() <= 0;
    }

    private void configureAsteroids() {
        Timer asteroidTimer = new Timer(1000, e -> {
            playElements.add(new Asteroid(ship.getPosition()));
        });
        asteroidTimer.start();
        StageService.addTimer(asteroidTimer);
    }

    private Ship configureShip() {
        ship = new Ship(beam -> {
            playElements.add(beam);
        });
        playElements.add(ship);
        keyListeners.add(ship);
        mouseInputListeners.add(ship);
        return ship;
    }

    private void setupGame() {
        configureDashboard();
        uiElements.add(ScoreService.getInstance());
        configureShip();
        configureAsteroids();
    }

    private HealthBar configureDashboard() {
        HealthBar healthBar = HealthBar.getInstance();
        uiElements.add(healthBar);
        return healthBar;
    }

    private void detectCollision() {
        for (int i = 0; i < playElements.size(); i++) {
            for (int j = i; j < playElements.size(); j++) {
                PlayElement a = playElements.get(i);
                PlayElement b = playElements.get(j);
                if (a!=b && a.getBounds().intersects(b.getBounds()))  {
                    a.acceptCollision(b);
                    b.acceptCollision(a);
                    ScoreService.processCollision(a,b);
                    DebugInfo.printDebugMessage("Collision detected between " + a.getClass().getSimpleName() + " and " + b.getClass().getSimpleName());
                }
            }
        }
    }
}
