package com.a29340.elements;

import com.a29340.core.Text;
import com.a29340.core.UIElement;
import com.a29340.utils.Constants;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

import static com.a29340.utils.Constants.monospaceFont;

public class StartMenu extends UIElement implements MouseInputListener {

    private Text startText = new Text("START", 130, 30, new Point(Constants.FRAME_SIZE.width/2, Constants.FRAME_SIZE.height/2));
    private boolean ended = false;

    public boolean isEnded() {
        return ended;
    }

    @Override
    public void update(Graphics2D g2d) {
        startText.update(g2d);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ended = startText.isUnder(e.getPoint());
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
        if (startText.isUnder(e.getPoint())) {
            startText.setFont(monospaceFont.deriveFont(Font.PLAIN, 30));
        } else {
            startText.setFont(monospaceFont.deriveFont(Font.PLAIN, 20));
        }
    }
}