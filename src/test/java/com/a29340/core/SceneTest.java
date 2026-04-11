package com.a29340.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SceneTest {

    private static class TestScene extends Scene {
        @Override
        public void scene() {
            // no-op
        }

        @Override
        public void setup() {
            // no-op
        }

        @Override
        public boolean ended() {
            return false;
        }
    }

    @Test
    void getPlayElements_returnsEmptyListInitially() {
        TestScene scene = new TestScene();

        assertThat(scene.getPlayElements()).isEmpty();
    }

    @Test
    void getUiElements_returnsEmptyListInitially() {
        TestScene scene = new TestScene();

        assertThat(scene.getUiElements()).isEmpty();
    }

}
