package com.a29340.scenes;

import com.a29340.elements.HealthBar;
import com.a29340.elements.Score;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.a29340.elements.HealthBar.INITIAL_HEALTH;
import static org.assertj.core.api.Assertions.assertThat;

class PostSceneTest {

    private PostScene scene;

    @BeforeEach
    void setUp() {
        scene = new PostScene();
        scene.setup();
    }

    @AfterEach
    void tearDown() {
        HealthBar.setHealth(INITIAL_HEALTH);
        Score.resetScore();
    }

    @Test
    void setup_displaysFinalScore() {
        Score.setTextPosition(com.a29340.elements.Score.gameplayPosition);
    }

    @Test
    void ended_initiallyFalse() {
        assertThat(scene.ended()).isFalse();
    }

    @Test
    void scene_doesNotThrow() {
        scene.scene();
    }

    @Test
    void getMouseInputListeners_notEmpty() {
        assertThat(scene.getMouseInputListeners()).isNotEmpty();
    }
}
