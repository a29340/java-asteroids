package com.a29340;

import com.a29340.elements.StageService;
import com.a29340.scenes.GameplayScene;
import com.a29340.scenes.StartMenuScene;
import com.a29340.utils.DebugInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.a29340.utils.Constants.FRAME_SIZE;

public class MainPanel extends JPanel implements MouseInputListener, KeyListener {
    BufferedImage background;

    public MainPanel() {
        setSize(FRAME_SIZE);
        setPreferredSize(FRAME_SIZE);
        setFocusable(true);
        requestFocus();
        configureBackground();
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        StageService.setRepaint(this::repaint);
        // --- create start menu scene
        StageService.addScene(new StartMenuScene());
        StageService.addScene(new GameplayScene());
        StageService.start();
        // --- create end titles scene
//        Scene displayScore = new Scene(() -> {
//            uiElements = uiElements.stream().filter(el -> !el.equals(gcm.getHealthBar()))
//                    .collect(Collectors.toList());
//        }, () -> {
//            detectCollision();
//            repaint();
//        }, () -> false);
//        StageService.addScene(displayScore);

    }





    private void configureBackground() {
        try {
            background = ImageIO.read(getClass().getClassLoader().getResource("images/background-pixel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
        StageService.getCurrentScene().getUiElements().forEach(uiElement -> uiElement.update(g2d));
        StageService.getCurrentScene().getPlayElements().forEach(playElement -> playElement.update(g2d));
        DebugInfo.print(g2d);
        g2d.dispose();
    }

    private void drawBackground(Graphics2D g2d) {
        if (background != null) {
            int x = 0;
            while (x < FRAME_SIZE.getWidth()) {
                int y = 0;
                while (y < FRAME_SIZE.getHeight()) {
                    g2d.drawImage(background, x, y, this);
                    y += background.getHeight();
                }
                x += background.getWidth();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            if (StageService.isPaused()) {
                StageService.resume();
            } else {
                StageService.pause();
            }
            return;
        }
        if (!StageService.isPaused()) {
            StageService.getCurrentScene().getKeyListeners().forEach(key -> key.keyTyped(e));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!StageService.isPaused()) {
            StageService.getCurrentScene().getKeyListeners().forEach(key -> key.keyPressed(e));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!StageService.isPaused()) {
            StageService.getCurrentScene().getKeyListeners().forEach(key -> key.keyReleased(e));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!StageService.isPaused()) {
            StageService.getCurrentScene().getMouseInputListeners().forEach(m -> m.mouseClicked(e));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!StageService.isPaused()) {
            StageService.getCurrentScene().getMouseInputListeners().forEach(m -> m.mousePressed(e));
        }


    }   @Override
    public void mouseReleased(MouseEvent e) {
        if (!StageService.isPaused()) {
            StageService.getCurrentScene().getMouseInputListeners().forEach(m -> m.mouseReleased(e));
        }


    }   @Override
    public void mouseEntered(MouseEvent e) {
        if (!StageService.isPaused()) {
            StageService.getCurrentScene().getMouseInputListeners().forEach(m -> m.mouseEntered(e));
        }


    }   @Override
    public void mouseExited(MouseEvent e) {
        if (!StageService.isPaused()) {
            StageService.getCurrentScene().getMouseInputListeners().forEach(m -> m.mouseExited(e));
        }


    }   @Override
    public void mouseDragged(MouseEvent e) {
        if (!StageService.isPaused()) {
            StageService.getCurrentScene().getMouseInputListeners().forEach(m -> m.mouseDragged(e));
        }


    }   @Override
    public void mouseMoved(MouseEvent e) {
        if (!StageService.isPaused()) {
            StageService.getCurrentScene().getMouseInputListeners().forEach(m -> m.mouseMoved(e));
        }
    }
}
