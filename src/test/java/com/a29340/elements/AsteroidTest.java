package com.a29340.elements;

import com.a29340.core.Velocity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.a29340.utils.Constants.FRAME_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

class AsteroidTest {

    @BeforeEach
    void setUp() {
        HealthBar.setHealth(HealthBar.INITIAL_HEALTH);
        Score.resetScore();
    }

    @AfterEach
    void tearDown() {
        HealthBar.setHealth(HealthBar.INITIAL_HEALTH);
        Score.resetScore();
    }

    @Test
    void constructor_createsAsteroidWithVelocity() {
        Point target = new Point(FRAME_SIZE.width / 2, FRAME_SIZE.height / 2);
        Asteroid asteroid = new Asteroid(target);

        assertThat(asteroid.getVelocity()).isNotNull();
        assertThat(asteroid.getPosition()).isNotNull();
    }

    @Test
    void shouldBeRemoved_returnsFalseWhenNotHitAndInFrame() {
        Point target = new Point(FRAME_SIZE.width / 2, FRAME_SIZE.height / 2);
        Asteroid asteroid = new Asteroid(target);
        asteroid.setPosition(0, 0);

        assertThat(asteroid.shouldBeRemoved()).isFalse();
    }

    @Test
    void shouldBeRemoved_returnsTrueWhenOutOfFrame() {
        Point target = new Point(FRAME_SIZE.width / 2, FRAME_SIZE.height / 2);
        Asteroid asteroid = new Asteroid(target);
        asteroid.setPosition(-500, -500);

        assertThat(asteroid.shouldBeRemoved()).isTrue();
    }

    @Test
    void acceptCollision_setsHitFlag() {
        Point target = new Point(FRAME_SIZE.width / 2, FRAME_SIZE.height / 2);
        Asteroid asteroid = new Asteroid(target);
        Beam beam = new Beam(new Point(0, 0), new Velocity());

        asteroid.acceptCollision(beam);

        assertThat(asteroid.shouldBeRemoved()).isFalse();
    }

    @Test
    void acceptCollision_withNonBeam_doesNothing() {
        Point target = new Point(FRAME_SIZE.width / 2, FRAME_SIZE.height / 2);
        Asteroid asteroid = new Asteroid(target);
        Asteroid otherAsteroid = new Asteroid(new Point(0, 0));

        asteroid.acceptCollision(otherAsteroid);

        assertThat(asteroid.shouldBeRemoved()).isFalse();
    }

    @Test
    void getBounds_returnsNotNull() {
        Point target = new Point(FRAME_SIZE.width / 2, FRAME_SIZE.height / 2);
        Asteroid asteroid = new Asteroid(target);

        Rectangle bounds = asteroid.getBounds();

        assertThat(bounds).isNotNull();
    }
}
