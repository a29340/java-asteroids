package com.a29340.elements;

import com.a29340.core.PlayElement;
import com.a29340.core.UIElement;

import java.awt.*;

import static com.a29340.utils.Constants.FRAME_SIZE;

public class ScoreService extends UIElement {
    private int score = 0;

    @Override
    public void update(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        g2d.setFont(new Font("Joystix Monospace", Font.PLAIN, 25));
        g2d.drawString("Score: " + score, 20, 20);
    }

    public void processCollision(PlayElement a, PlayElement b) {
        if (asteroidHit(a,b) && a.getFrame() == 1 && b.getFrame() == 1) {
            score = score + 10;
        }
    }

    private boolean asteroidHit(PlayElement a, PlayElement b) {
        return (a instanceof Beam && b instanceof Asteroid) || (a instanceof Asteroid && b instanceof Beam);
    }
}
