package com.a29340.elements;

import com.a29340.core.Velocity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.a29340.utils.Constants.FRAME_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

class BeamTest {

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
    void constructor_setsPosition() {
        Point position = new Point(100, 100);
        Velocity velocity = new Velocity(1, 2);
        Beam beam = new Beam(position, velocity);

        assertThat(beam.getPosition()).isEqualTo(position);
    }

    @Test
    void constructor_setsAngleFromVelocity() {
        Point position = new Point(100, 100);
        Velocity velocity = new Velocity(3, 4);
        Beam beam = new Beam(position, velocity);

        assertThat(beam.getFrame()).isEqualTo(0);
    }

    @Test
    void shouldBeRemoved_returnsFalseWhenNotHitAndInFrame() {
        Point position = new Point(100, 100);
        Velocity velocity = new Velocity(1, 0);
        Beam beam = new Beam(position, velocity);

        assertThat(beam.shouldBeRemoved()).isFalse();
    }

    @Test
    void shouldBeRemoved_returnsTrueWhenOutOfFrame() {
        Point position = new Point(-500, -500);
        Velocity velocity = new Velocity(1, 0);
        Beam beam = new Beam(position, velocity);

        assertThat(beam.shouldBeRemoved()).isTrue();
    }

    @Test
    void shouldBeRemoved_returnsTrueWhenHitAndFrameComplete() {
        Point position = new Point(-500, -500);
        Velocity velocity = new Velocity(0, 0);
        Beam beam = new Beam(position, velocity);
        Asteroid asteroid = new Asteroid(new Point(FRAME_SIZE.width / 2, FRAME_SIZE.height / 2));

        beam.acceptCollision(asteroid);

        assertThat(beam.shouldBeRemoved()).isTrue();
    }

    @Test
    void acceptCollision_withAsteroid_stopsVelocity() {
        Point position = new Point(100, 100);
        Velocity velocity = new Velocity(10, 20);
        Beam beam = new Beam(position, velocity);
        Asteroid asteroid = new Asteroid(new Point(FRAME_SIZE.width / 2, FRAME_SIZE.height / 2));

        beam.acceptCollision(asteroid);

        assertThat(beam.shouldBeRemoved()).isFalse();
    }

    @Test
    void acceptCollision_withNonAsteroid_doesNothing() {
        Point position = new Point(100, 100);
        Velocity velocity = new Velocity(10, 20);
        Beam beam = new Beam(position, velocity);
        Beam otherBeam = new Beam(new Point(0, 0), new Velocity());

        beam.acceptCollision(otherBeam);

        assertThat(beam.shouldBeRemoved()).isFalse();
    }

    @Test
    void acceptCollision_onlyTriggersOnce() {
        Point position = new Point(100, 100);
        Velocity velocity = new Velocity(10, 20);
        Beam beam = new Beam(position, velocity);
        Asteroid asteroid = new Asteroid(new Point(FRAME_SIZE.width / 2, FRAME_SIZE.height / 2));

        beam.acceptCollision(asteroid);
        beam.acceptCollision(asteroid);

        assertThat(beam.shouldBeRemoved()).isFalse();
    }
}
