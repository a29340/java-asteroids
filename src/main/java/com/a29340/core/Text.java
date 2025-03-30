package com.a29340.core;

import java.awt.*;

import static com.a29340.utils.Constants.monospaceFont;

public class Text extends UIElement {
    private Font font = monospaceFont.deriveFont(Font.PLAIN, 20);
    private Color color = Color.YELLOW;
    private String text;
    private int width;
    private int height;

    public Text(String text, int width, int height, Point position) {
        this.text = text;
        this.width = width;
        this.height = height;
        setPosition(position.x - width/2, position.y - height/2);
    }

    public void setFont(Font font) {
        this.font = font;
    }

    @Override
    public void update(Graphics2D g2d) {
        g2d.setFont(font);
        g2d.setColor(color);
        g2d.drawString(text, getPosition().x, getPosition().y+height);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}