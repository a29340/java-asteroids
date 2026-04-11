package com.a29340.elements;

import com.a29340.core.Velocity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.a29340.utils.Constants.FRAME_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @BeforeEach
    void setUp() {
        Score.resetScore();
        HealthBar.setHealth(HealthBar.INITIAL_HEALTH);
    }

    @AfterEach
    void tearDown() {
        Score.resetScore();
        HealthBar.setHealth(HealthBar.INITIAL_HEALTH);
    }

    @Test
    void getInstance_returnsSameInstance() {
        Score instance1 = Score.getInstance();
        Score instance2 = Score.getInstance();

        assertThat(instance1).isSameAs(instance2);
    }

    @Test
    void getScore_initiallyZero() {
        Score.resetScore();

        assertThat(Score.getScore()).isEqualTo(0);
    }

    @Test
    void resetScore_setsScoreToZero() {
        Score.resetScore();

        assertThat(Score.getScore()).isEqualTo(0);
    }

    @Test
    void getTextInstance_returnsNotNull() {
        assertThat(Score.getTextInstance()).isNotNull();
    }

    @Test
    void asteroidHit_beamAndAsteroid_bothAcceptCollision() {
        Beam beam = new Beam(new Point(0, 0), new Velocity(1, 1));
        Asteroid asteroid = new Asteroid(new Point(FRAME_SIZE.width / 2, FRAME_SIZE.height / 2));

        beam.acceptCollision(asteroid);
        asteroid.acceptCollision(beam);

        assertThat(beam.shouldBeRemoved()).isNotNull();
    }
}
