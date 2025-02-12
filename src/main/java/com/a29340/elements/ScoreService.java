package com.a29340.elements;

import com.a29340.core.PlayElement;
import com.a29340.core.UIElement;

import java.awt.*;

import static com.a29340.utils.Constants.monospaceFont;

public class ScoreService extends UIElement {
    private static Integer score = 0;

    private static ScoreService instance =  new ScoreService();

    public static ScoreService getInstance() {
        return instance;
    }

    private ScoreService() {
    }

    @Override
    public void update(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        g2d.setFont(monospaceFont);
        g2d.drawString("Score: " + score, 20, 40);
    }

    public static void processCollision(PlayElement a, PlayElement b) {
        if (asteroidHit(a,b) && a.getFrame() == 1 && b.getFrame() == 1) {
            score = score + 10;
        }
    }

    private static boolean asteroidHit(PlayElement a, PlayElement b) {
        return (a instanceof Beam && b instanceof Asteroid) || (a instanceof Asteroid && b instanceof Beam);
    }
}