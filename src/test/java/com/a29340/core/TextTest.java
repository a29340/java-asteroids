package com.a29340.core;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.a29340.core.Text.Alignement;
import static org.assertj.core.api.Assertions.assertThat;

class TextTest {

    @Test
    void constructor_setsTextAndPosition() {
        Point position = new Point(100, 100);
        Text text = new Text("Hello", 20, position, Alignement.LEFT);

        assertThat(text.getPosition()).isNotNull();
        assertThat(text.getHeight()).isEqualTo(20);
    }

    @Test
    void getHeight_returnsSetHeight() {
        Point position = new Point(100, 100);
        Text text = new Text("Test", 25, position, Alignement.CENTER);

        assertThat(text.getHeight()).isEqualTo(25);
    }

    @Test
    void getWidth_returnsCalculatedWidth() {
        Point position = new Point(100, 100);
        Text text = new Text("Test", 20, position, Alignement.LEFT);

        int width = text.getWidth();

        assertThat(width).isGreaterThan(0);
    }

    @Test
    void getWidth_differentTextLengths_differentWidths() {
        Point position = new Point(100, 100);
        Text shortText = new Text("A", 20, position, Alignement.LEFT);
        Text longText = new Text("ABCDEFGHIJ", 20, position, Alignement.LEFT);

        assertThat(longText.getWidth()).isGreaterThan(shortText.getWidth());
    }

    @Test
    void getLeftBound_withLeftAlign_returnsCorrectBound() {
        Point position = new Point(100, 100);
        Text text = new Text("Test", 20, position, Alignement.LEFT);

        int leftBound = text.getLeftBound();

        assertThat(leftBound).isEqualTo(position.x);
    }

    @Test
    void getRightBound_withLeftAlign_returnsCorrectBound() {
        Point position = new Point(100, 100);
        Text text = new Text("Test", 20, position, Alignement.LEFT);

        int rightBound = text.getRightBound();

        assertThat(rightBound).isGreaterThan(position.x);
    }

    @Test
    void getTopBound_withCenterAlign_returnsCorrectBound() {
        Point position = new Point(100, 100);
        Text text = new Text("Test", 20, position, Alignement.CENTER);

        int topBound = text.getTopBound();

        assertThat(topBound).isLessThan(position.y);
    }

    @Test
    void getBottomBound_withCenterAlign_returnsCorrectBound() {
        Point position = new Point(100, 100);
        Text text = new Text("Test", 20, position, Alignement.CENTER);

        int bottomBound = text.getBottomBound();

        assertThat(bottomBound).isGreaterThan(position.y);
    }

    @Test
    void setText_updatesTextAndWidth() {
        Point position = new Point(100, 100);
        Text text = new Text("Short", 20, position, Alignement.LEFT);
        int originalWidth = text.getWidth();

        text.setText("Much Longer Text");

        assertThat(text.getWidth()).isNotEqualTo(originalWidth);
        assertThat(text.getWidth()).isGreaterThan(originalWidth);
    }

    @Test
    void setPosition_withLeftAlign_setsCorrectPosition() {
        Text text = new Text("Test", 20, new Point(100, 100), Alignement.LEFT);

        text.setPosition(200, 150);

        assertThat(text.getPosition().x).isEqualTo(200 + text.getWidth() / 2);
    }

    @Test
    void setPosition_withCenterAlign_setsCorrectPosition() {
        Text text = new Text("Test", 20, new Point(100, 100), Alignement.CENTER);

        text.setPosition(200, 150);

        assertThat(text.getPosition().x).isEqualTo(200);
        assertThat(text.getPosition().y).isEqualTo(150);
    }

    @Test
    void setPosition_withRightAlign_setsCorrectPosition() {
        Text text = new Text("Test", 20, new Point(100, 100), Alignement.RIGHT);

        text.setPosition(200, 150);

        assertThat(text.getPosition().x).isEqualTo(200 - text.getWidth() / 2);
    }

    @Test
    void alignment_enum_hasAllValues() {
        assertThat(Alignement.values()).containsExactly(Alignement.LEFT, Alignement.CENTER, Alignement.RIGHT);
    }
}
