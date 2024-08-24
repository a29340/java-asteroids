package com.a29340.core;

import javax.swing.*;
import java.awt.*;

public abstract class Entity extends JComponent {
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


}
