package com.a29340.core;

import java.awt.*;

public class Velocity {
    private int dx;
    private int dy;
    private double angle;
    private double module;

    public Velocity() {
        this.dx = 0;
        this.dy = 0;
        this.angle = 0;
        this.module = 0;
    }

    public Velocity(double angle, double module) {
        this.angle = angle;
        this.module = module;
        computeProjectedComponents();
    }

    public Velocity(int dx, int dy) {
        this.dy = dy;
        this.dx = dx;
        computeModule(dx, dy);
        computeAngle(dx, dy);
    }

    private void computeProjectedComponents() {
        this.dx = (int) (module * Math.cos(this.angle - Math.PI/2));
        this.dy = (int) (module * Math.sin(this.angle - Math.PI/2));
    }

    private void computeAngle(int dx, int dy) {
        this.angle = Math.atan2(dy, dx);
    }

    private void computeModule(int x, int y) {
        this.module = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public int getDx() {
        return dx;
    }

    public void increaseX(int dx) {
        this.dx += dx;
        computeModule(this.dx, dy);
        computeAngle(this.dx, dy);
    }

    public int getDy() {
        return dy;
    }

    public void increaseY(int dy) {
        this.dy += dy;
        computeModule(dx, this.dy);
        computeAngle(dx, this.dy);
    }

    public double getModule() {
        return module;
    }

    public Point getTargetFromPoint(Point point) {
        Point target = new Point();
        target.setLocation(point.getX() + dx, point.getY() + dy);
        return target;
    }

    public double getAngle() {
        return angle;
    }



    public void setDx(int dx) {
        this.dx = dx;
        computeModule(dx, dy);
        computeAngle(dx, dy);
    }

    public void setDy(int dy) {
        this.dy = dy;
        computeModule(dx, dy);
        computeAngle(dx, dy);
    }

    @Override
    public String toString() {
        return "Velocity{" +
                "dx=" + dx +
                ", dy=" + dy +
                ", angle=" + angle +
                ", module=" + module +
                '}';
    }
}
