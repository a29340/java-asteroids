package com.a29340.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayElementTest {

    private static class TestPlayElement extends PlayElement {
        @Override
        public boolean shouldBeRemoved() {
            return false;
        }

        @Override
        public void acceptCollision(PlayElement collided) {
        }

        @Override
        public void updatePlayElement(java.awt.Graphics2D g2d) {
        }
    }

    @Test
    void getVelocity_returnsInitializedVelocity() {
        TestPlayElement element = new TestPlayElement();

        Velocity velocity = element.getVelocity();

        assertThat(velocity).isNotNull();
        assertThat(velocity.getDx()).isEqualTo(0);
        assertThat(velocity.getDy()).isEqualTo(0);
    }

    @Test
    void getFrame_initiallyZero() {
        TestPlayElement element = new TestPlayElement();

        assertThat(element.getFrame()).isEqualTo(0);
    }

    @Test
    void getBounds_returnsNotNull() {
        TestPlayElement element = new TestPlayElement();

        assertThat(element.getBounds()).isNotNull();
    }

}
