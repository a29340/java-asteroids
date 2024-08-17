package com.a29340.core;

import java.awt.*;

public abstract class Entity {
    private Point position = new Point();

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        this.position.x = x;
        this.position.y = y;
    }

    public abstract void update(Graphics2D g2d);


}
