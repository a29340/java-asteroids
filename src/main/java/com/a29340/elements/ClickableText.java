package com.a29340.elements;

import com.a29340.core.Text;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

import static com.a29340.utils.Constants.monospaceFont;

public class ClickableText extends Text implements MouseInputListener {

    private boolean clicked = false;
    private Runnable onClick;

    public ClickableText(String text, Integer width, Integer height, Point position, Runnable onClick) {
        super(text, width, height, position);
        this.onClick = onClick;
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
        return getPosition().x < point.x && getPosition().x + getWidth() > point.x &&
                getPosition().y < point.y && getPosition().y + getHeight() > point.y;
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
            setFont(monospaceFont.deriveFont(Font.PLAIN, 30));
        } else {
            setFont(monospaceFont.deriveFont(Font.PLAIN, 20));
        }
    }
}