package com.a29340.core;

import java.awt.*;

public class Pixel {
    private Color color;
    private Velocity velocity;

    public Pixel(Color color) {
        this.color = color;
    }

    public Pixel(Color color, Velocity velocity) {
        this.color = color;
        this.velocity = velocity;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
