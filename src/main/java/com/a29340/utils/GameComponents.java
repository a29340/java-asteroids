package com.a29340.utils;

import com.a29340.elements.HealthBar;
import com.a29340.elements.ScoreService;
import com.a29340.elements.Ship;

public class GameComponents {

    HealthBar healthBar;
    ScoreService scoreService;
    Ship ship;

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    public ScoreService getScoreService() {
        return scoreService;
    }

    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
