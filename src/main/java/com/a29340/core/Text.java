package com.a29340.core;

import com.a29340.utils.Configurations;
import com.a29340.utils.Constants;

import java.awt.*;

import static com.a29340.utils.Constants.monospaceFont;

public class Text extends UIElement {
    protected Font font;
    private Color color = Color.YELLOW;
    private String text;
    protected int height;
    protected float aspectRatio;
    protected float fontAspectRatio = 1.05f;
    protected Alignement alignement;

    public Text(String text, int height, Point position, Alignement alignement) {
        this.text = text;
        this.height = height;
        this.font = monospaceFont.deriveFont(Font.PLAIN, height * 1.25f);
        this.alignement = alignement;
        this.aspectRatio = fontAspectRatio * text.length();
        setPosition(position.x, position.y);
    }

    public void setFontSize() {
        this.font = monospaceFont.deriveFont(Font.PLAIN, height * 1.25f);
    }

    @Override
    public void update(Graphics2D g2d) {
        g2d.setFont(font);
        g2d.setColor(color);
        g2d.drawString(text, getLeftBound(), getBottomBound());
        if (Configurations.debugMode()) {
            g2d.setColor(Color.BLUE);
            g2d.draw(new Rectangle(getLeftBound(), getTopBound(), getWidth(), getHeight()));
            g2d.drawLine(0, getPosition().y, Constants.FRAME_SIZE.width, getPosition().y);
            switch (alignement) {
                case CENTER:
                    g2d.drawLine(getPosition().x, 0, getPosition().x, Constants.FRAME_SIZE.height);
                    break;
                case LEFT:
                    g2d.drawLine(getLeftBound(), 0, getLeftBound(), Constants.FRAME_SIZE.height);
                    break;
            }
            g2d.setColor(color);
        }
    }

    public void setText(String text) {
        this.text = text;
        this.aspectRatio = fontAspectRatio * text.length();
    }

    @Override
    public void setPosition(int x, int y) {
        switch (this.alignement) {
            case CENTER:
                super.setPosition(x, y);
                break;
            case LEFT:
                super.setPosition(x + getWidth() / 2, y);
                break;
            case RIGHT:
                super.setPosition(x - getWidth() / 2, y);
                break;
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return (int) (height * aspectRatio);
    }

    public int getLeftBound() {
        return getPosition().x - getWidth() / 2;
    }

    public int getRightBound() {
        return getPosition().x + getWidth() / 2;
    }

    public int getTopBound() {
        return getPosition().y - getHeight() / 2;
    }

    public int getBottomBound() {
        return getPosition().y + getHeight() / 2;
    }


    public enum Alignement {
        LEFT,
        CENTER,
        RIGHT
    }
}