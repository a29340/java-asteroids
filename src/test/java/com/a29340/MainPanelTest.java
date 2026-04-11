package com.a29340;

import com.a29340.elements.HealthBar;
import com.a29340.elements.Score;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static com.a29340.elements.HealthBar.INITIAL_HEALTH;
import static org.assertj.core.api.Assertions.assertThat;

class MainPanelTest {

    @BeforeEach
    void setUp() {
        Score.resetScore();
        HealthBar.setHealth(INITIAL_HEALTH);
    }

    @AfterEach
    void tearDown() {
        Score.resetScore();
        HealthBar.setHealth(INITIAL_HEALTH);
    }

    @Test
    void mainPanel_constructor_doesNotThrow() {
        MainPanel panel = new MainPanel();

        assertThat(panel).isNotNull();
    }

    @Test
    void keyTyped_doesNotThrow() {
        MainPanel panel = new MainPanel();

        KeyEvent event = new KeyEvent(new Component() {}, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, 0, 'x');
        panel.keyTyped(event);
    }

    @Test
    void keyPressed_doesNotThrow() {
        MainPanel panel = new MainPanel();

        KeyEvent event = new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'w');
        panel.keyPressed(event);
    }

    @Test
    void keyReleased_doesNotThrow() {
        MainPanel panel = new MainPanel();

        KeyEvent event = new KeyEvent(new Component() {}, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'w');
        panel.keyReleased(event);
    }

    @Test
    void mouseClicked_doesNotThrow() {
        MainPanel panel = new MainPanel();

        MouseEvent event = new MouseEvent(new Component() {}, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 100, 100, 1, false);
        panel.mouseClicked(event);
    }

    @Test
    void mousePressed_doesNotThrow() {
        MainPanel panel = new MainPanel();

        MouseEvent event = new MouseEvent(new Component() {}, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 100, 100, 1, false);
        panel.mousePressed(event);
    }

    @Test
    void mouseReleased_doesNotThrow() {
        MainPanel panel = new MainPanel();

        MouseEvent event = new MouseEvent(new Component() {}, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 100, 100, 1, false);
        panel.mouseReleased(event);
    }

    @Test
    void mouseEntered_doesNotThrow() {
        MainPanel panel = new MainPanel();

        MouseEvent event = new MouseEvent(new Component() {}, MouseEvent.MOUSE_ENTERED, System.currentTimeMillis(), 0, 100, 100, 1, false);
        panel.mouseEntered(event);
    }

    @Test
    void mouseExited_doesNotThrow() {
        MainPanel panel = new MainPanel();

        MouseEvent event = new MouseEvent(new Component() {}, MouseEvent.MOUSE_EXITED, System.currentTimeMillis(), 0, 100, 100, 1, false);
        panel.mouseExited(event);
    }

    @Test
    void mouseDragged_doesNotThrow() {
        MainPanel panel = new MainPanel();

        MouseEvent event = new MouseEvent(new Component() {}, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), 0, 100, 100, 1, false);
        panel.mouseDragged(event);
    }

    @Test
    void mouseMoved_doesNotThrow() {
        MainPanel panel = new MainPanel();

        MouseEvent event = new MouseEvent(new Component() {}, MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, 100, 100, 1, false);
        panel.mouseMoved(event);
    }
}
