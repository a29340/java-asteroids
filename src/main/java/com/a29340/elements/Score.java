package com.a29340.elements;

import com.a29340.core.PlayElement;
import com.a29340.core.Text;
import com.a29340.core.UIElement;

import java.awt.*;

public class Score {
    private static Integer score = 0;
    private static final String text = "Score: ";
    public static final Point gameplayPosition = new Point(40, 30);

    private static Score instance =  new Score();
    private static Text scoreText;


    public static Score getInstance() {
        return instance;
    }

    private Score() {
        scoreText = new Text(text + score, 20, gameplayPosition, Text.Alignement.LEFT);
    }

    public static UIElement getTextInstance() {
        return scoreText;
    }

    public static void processCollision(PlayElement a, PlayElement b) {
        if (asteroidHit(a,b) && a.getFrame() == 1 && b.getFrame() == 1) {
            score = score + 10;
            scoreText.setText(text + score);
        }
    }

    private static boolean asteroidHit(PlayElement a, PlayElement b) {
        return (a instanceof Beam && b instanceof Asteroid) || (a instanceof Asteroid && b instanceof Beam);
    }

    public  static void resetScore() {
        score = 0;
        scoreText.setText(text + score);
    }

    public static void setTextPosition(Point point) {
        scoreText.setPosition(point.x, point.y);
    }

    public static int getScore() {
        return score;
    }
}