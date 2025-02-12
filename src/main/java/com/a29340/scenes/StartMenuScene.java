package com.a29340.scenes;

import com.a29340.core.Scene;
import com.a29340.core.UIElement;
import com.a29340.utils.Constants;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

import static com.a29340.utils.Constants.monospaceFont;

public class StartMenuScene extends Scene {

    private boolean ended = false;

    @Override
    public void scene() {

    }

    @Override
    public void setup() {
        StartMenu e = new StartMenu();
        uiElements.add(e);
        mouseInputListeners.add(e);
    }

    @Override
    public boolean ended() {
        return ended;
    }

    public class StartMenu extends UIElement implements MouseInputListener {
        private Font font = monospaceFont.deriveFont(Font.PLAIN, 30);
        @Override
        public void update(Graphics2D g2d) {
            g2d.setFont(font);
            g2d.setColor(Color.YELLOW);
            g2d.drawString("START", Constants.FRAME_SIZE.width / 2, Constants.FRAME_SIZE.height / 2);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            ended = true;
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            font = monospaceFont.deriveFont(Font.PLAIN, 40);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            font = monospaceFont.deriveFont(Font.PLAIN, 30);

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

}
