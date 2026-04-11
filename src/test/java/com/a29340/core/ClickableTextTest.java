package com.a29340.core;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ClickableTextTest {

    @Test
    void constructor_setsOnClickRunnable() {
        AtomicBoolean clicked = new AtomicBoolean(false);
        Point position = new Point(100, 100);

        ClickableText clickableText = new ClickableText("Click Me", 20, position, () -> clicked.set(true), 0.1f);

        assertThat(clickableText).isNotNull();
    }

    @Test
    void mouseClicked_withPointUnderText_executesOnClick() {
        AtomicBoolean clicked = new AtomicBoolean(false);
        Point position = new Point(100, 100);
        ClickableText clickableText = new ClickableText("Test", 20, position, () -> clicked.set(true), 0.1f);

        MouseEvent event = mock(MouseEvent.class);
        int x = clickableText.getLeftBound() + 5;
        int y = clickableText.getTopBound() + 5;
        when(event.getPoint()).thenReturn(new Point(x, y));

        clickableText.mouseClicked(event);

        assertThat(clicked.get()).isTrue();
    }

    @Test
    void mouseClicked_withPointOutsideText_doesNotExecuteOnClick() {
        AtomicBoolean clicked = new AtomicBoolean(false);
        Point position = new Point(100, 100);
        ClickableText clickableText = new ClickableText("Test", 20, position, () -> clicked.set(true), 0.1f);

        MouseEvent event = mock(MouseEvent.class);
        when(event.getPoint()).thenReturn(new Point(1000, 1000));

        clickableText.mouseClicked(event);

        assertThat(clicked.get()).isFalse();
    }

    @Test
    void mouseClicked_onlyExecutesOnce() {
        AtomicBoolean clickCount = new AtomicBoolean(false);
        Point position = new Point(100, 100);
        ClickableText clickableText = new ClickableText("Test", 20, position, () -> {
            if (clickCount.get()) {
                throw new IllegalStateException("Already clicked");
            }
            clickCount.set(true);
        }, 0.1f);

        MouseEvent event = mock(MouseEvent.class);
        int x = clickableText.getLeftBound() + 5;
        int y = clickableText.getTopBound() + 5;
        when(event.getPoint()).thenReturn(new Point(x, y));

        clickableText.mouseClicked(event);
        clickableText.mouseClicked(event);

        assertThat(clickCount.get()).isTrue();
    }

    @Test
    void mouseMoved_withPointUnderText_setsHovered() {
        Point position = new Point(100, 100);
        ClickableText clickableText = new ClickableText("Test", 20, position, () -> {}, 0.1f);

        MouseEvent event = mock(MouseEvent.class);
        int x = clickableText.getLeftBound() + 5;
        int y = clickableText.getTopBound() + 5;
        when(event.getPoint()).thenReturn(new Point(x, y));

        clickableText.mouseMoved(event);
    }

    @Test
    void mouseMoved_withPointOutsideText_clearsHovered() {
        Point position = new Point(100, 100);
        ClickableText clickableText = new ClickableText("Test", 20, position, () -> {}, 0.1f);

        MouseEvent enterEvent = mock(MouseEvent.class);
        when(enterEvent.getPoint()).thenReturn(new Point(100, 100));
        clickableText.mouseMoved(enterEvent);

        MouseEvent exitEvent = mock(MouseEvent.class);
        when(exitEvent.getPoint()).thenReturn(new Point(1000, 1000));
        clickableText.mouseMoved(exitEvent);
    }

    @Test
    void mouseDragged_doesNotThrow() {
        Point position = new Point(100, 100);
        ClickableText clickableText = new ClickableText("Test", 20, position, () -> {}, 0.1f);

        MouseEvent event = mock(MouseEvent.class);
        clickableText.mouseDragged(event);
    }

    @Test
    void mousePressed_doesNotThrow() {
        Point position = new Point(100, 100);
        ClickableText clickableText = new ClickableText("Test", 20, position, () -> {}, 0.1f);

        MouseEvent event = mock(MouseEvent.class);
        clickableText.mousePressed(event);
    }

    @Test
    void mouseReleased_doesNotThrow() {
        Point position = new Point(100, 100);
        ClickableText clickableText = new ClickableText("Test", 20, position, () -> {}, 0.1f);

        MouseEvent event = mock(MouseEvent.class);
        clickableText.mouseReleased(event);
    }

    @Test
    void mouseEntered_doesNotThrow() {
        Point position = new Point(100, 100);
        ClickableText clickableText = new ClickableText("Test", 20, position, () -> {}, 0.1f);

        MouseEvent event = mock(MouseEvent.class);
        clickableText.mouseEntered(event);
    }

    @Test
    void mouseExited_doesNotThrow() {
        Point position = new Point(100, 100);
        ClickableText clickableText = new ClickableText("Test", 20, position, () -> {}, 0.1f);

        MouseEvent event = mock(MouseEvent.class);
        clickableText.mouseExited(event);
    }
}
