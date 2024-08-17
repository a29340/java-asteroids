package com.a29340.elements;

import com.a29340.core.UIElement;

import java.awt.*;

public class HealthBar extends UIElement {

    public final static Integer INITIAL_HEALTH = 100;
    private Integer health = INITIAL_HEALTH;

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    @Override
    public void update(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        g2d.fillRect(0, 0, health * 2, 100);
    }
}
