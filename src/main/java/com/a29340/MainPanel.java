package com.a29340;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.a29340.Constants.*;

public class MainPanel extends JPanel {
    BufferedImage background;
    List<Entity> objectsInGame = new ArrayList<>();
    long lastRepaint = System.currentTimeMillis();
    final Ship ship;
    public MainPanel() {
        setSize(FRAME_SIZE);
        setPreferredSize(FRAME_SIZE);
        setFocusable(true);
        requestFocus();
        try {
            background = ImageIO.read(getClass().getClassLoader().getResource("images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ship = new Ship(beam -> {
            objectsInGame.add(beam);
        });
        objectsInGame.add(ship);
        addKeyListener(ship);
        addMouseMotionListener(ship);
        addMouseListener(ship);
        Timer asteroidTimer = new Timer(1000, e -> {
            objectsInGame.add(new Asteroid(ship.position));
        });
        asteroidTimer.start();
        Timer timer = new Timer(16, e -> {
           detectCollision();
           repaint();
        });
        timer.start();
    }

    private void detectCollision() {
        for (int i = 0; i < objectsInGame.size(); i++) {
            for (int j = i; j < objectsInGame.size(); j++) {
                Entity a = objectsInGame.get(i);
                Entity b = objectsInGame.get(j);
                if (a!=b && a.getBounds().intersects(b.getBounds()))  {
                    if (a instanceof Beam && b instanceof Beam || a instanceof Ship || b instanceof Ship) {

                    } else {
                        System.out.println(String.format("Collision between %s and %s", a, b));
                        a.acceptCollision();
                        b.acceptCollision();
                    }
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        long now = System.currentTimeMillis();
        System.out.println("FPS: " + 1000/(now - lastRepaint));
        lastRepaint = now;
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (background != null) {
            g2d.drawImage(background, 0, 0, this);
        }
        objectsInGame.forEach(c -> {
            c.update(g2d);
        });
        objectsInGame = objectsInGame.stream().filter(c -> !c.shouldBeRemoved()).collect(Collectors.toList());
        g2d.dispose();
    }
}
