package com.a29340.core;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ClickableText extends Text implements MouseInputListener {

    private boolean clicked = false;
    private Runnable onClick;
    private boolean isHovered = false;
    private int hoveredHeight;
    private int standardHeight;
    public ClickableText(String text, Integer size, Point position, Runnable onClick, Float scaleFactorOnHover) {
        super(text, size, position, Alignement.CENTER);
        this.onClick = onClick;
        float dh = size * scaleFactorOnHover / aspectRatio;
        hoveredHeight = (int) (size + dh);
        standardHeight = size;
    }

    @Override
    public void update(Graphics2D g2d) {
        super.update(g2d);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!clicked && isUnder(e.getPoint())) {
            clicked = true;
            onClick.run();
        }
    }

    private boolean isUnder(Point point) {
        return getLeftBound() < point.x && getRightBound() > point.x
                &&
                getTopBound() < point.y && getBottomBound() > point.y;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (isUnder(e.getPoint())) {
            if (!isHovered) {
                isHovered = true;
                // move text to left 5 px
                height = hoveredHeight;
                setFontSize();
            }
        } else {
            if (isHovered) {
                isHovered = false;
                height = standardHeight;
                setFontSize();
            }
        }
    }
}