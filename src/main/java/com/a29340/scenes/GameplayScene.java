package com.a29340.scenes;

import com.a29340.core.PlayElement;
import com.a29340.core.Scene;
import com.a29340.elements.*;
import com.a29340.utils.DebugInfo;

import javax.swing.*;
import java.util.stream.Collectors;

public class GameplayScene extends Scene {
    private Ship ship;

    @Override
    public void setup() {
        configureDashboard();
        uiElements.add(Score.getInstance());
        configureShip();
        configureAsteroids();
    }
    @Override
    public void scene() {
        detectCollision();
        playElements = playElements.stream()
                .filter(c -> !c.shouldBeRemoved())
                .collect(Collectors.toList());
    }

    @Override
    public boolean ended() {
        return HealthBar.getHealth() <= 0;
    }

    private void configureAsteroids() {
        Timer asteroidTimer = new Timer(1000, e -> playElements.add(new Asteroid(ship.getPosition())));
        asteroidTimer.start();
        StageService.addTimer(asteroidTimer);
    }

    private void configureShip() {
        ship = new Ship(beam -> playElements.add(beam));
        playElements.add(ship);
        keyListeners.add(ship);
        mouseInputListeners.add(ship);
    }

    private void configureDashboard() {
        HealthBar healthBar = HealthBar.getInstance();
        uiElements.add(healthBar);
    }

    private void detectCollision() {
        for (int i = 0; i < playElements.size(); i++) {
            for (int j = i; j < playElements.size(); j++) {
                PlayElement a = playElements.get(i);
                PlayElement b = playElements.get(j);
                if (a!=b && a.getBounds().intersects(b.getBounds()))  {
                    a.acceptCollision(b);
                    b.acceptCollision(a);
                    Score.processCollision(a,b);
                    DebugInfo.printDebugMessage("Collision detected between " + a.getClass().getSimpleName() + " and " + b.getClass().getSimpleName());
                }
            }
        }
    }
}
