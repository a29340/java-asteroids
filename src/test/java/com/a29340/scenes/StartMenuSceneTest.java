package com.a29340.scenes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StartMenuSceneTest {

    private StartMenuScene scene;

    @BeforeEach
    void setUp() {
        scene = new StartMenuScene();
        scene.setup();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setup_createsStartText() {
        assertThat(scene.startText).isNotNull();
    }

    @Test
    void setup_addsStartTextToUiElements() {
        assertThat(scene.getUiElements()).contains(scene.startText);
    }

    @Test
    void setup_addsStartTextToMouseListeners() {
        assertThat(scene.getMouseInputListeners()).contains(scene.startText);
    }

    @Test
    void ended_initiallyFalse() {
        assertThat(scene.ended()).isFalse();
    }

    @Test
    void scene_doesNotThrow() {
        scene.scene();
    }
}
