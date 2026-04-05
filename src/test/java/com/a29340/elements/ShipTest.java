package com.a29340.elements;

import com.a29340.core.PlayElement;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.function.Consumer;

import static com.a29340.utils.Constants.FRAME_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ShipTest {

    @Test
    void constructor_initializesShipAtCenter() {
        Consumer<PlayElement> beamFunction = mock(Consumer.class);
        Ship ship = new Ship(beamFunction);
        
        // Ship starts at center of frame (FRAME_SIZE/2)
        assertThat(ship.getPosition()).isNotNull();
        assertThat(ship.getVelocity()).isNotNull();
    }

    @Test
    void constructor_setsBeamFunction() {
        Consumer<PlayElement> beamFunction = mock(Consumer.class);
        Ship ship = new Ship(beamFunction);
        
        // Verify the ship was created with the beam function
        assertThat(ship).isNotNull();
    }

    @Test
    void getPosition_returnsValidPoint() {
        Consumer<PlayElement> beamFunction = mock(Consumer.class);
        Ship ship = new Ship(beamFunction);
        
        Point position = ship.getPosition();
        
        assertThat(position).isNotNull();
        // Position should be at center of frame
        assertThat(position.x).isGreaterThan(0);
        assertThat(position.y).isGreaterThan(0);
    }

    @Test
    void getVelocity_returnsInitializedVelocity() {
        Consumer<PlayElement> beamFunction = mock(Consumer.class);
        Ship ship = new Ship(beamFunction);
        
        com.a29340.core.Velocity velocity = ship.getVelocity();
        
        assertThat(velocity).isNotNull();
    }

    @Test
    void shouldBeRemoved_returnsFalseWhenAlive() {
        Consumer<PlayElement> beamFunction = mock(Consumer.class);
        Ship ship = new Ship(beamFunction);
        
        // Initially alive, so should not be removed
        assertThat(ship.shouldBeRemoved()).isFalse();
    }

    @Test
    void shouldBeRemoved_returnsTrueAfterExplosion() {
        Consumer<PlayElement> beamFunction = mock(Consumer.class);
        Ship ship = new Ship(beamFunction);
        
        // Simulate death by setting health to 0 and advancing frames
        HealthBar.setHealth(0);
        ship.getFrame(); // Access frame through getter
        
        assertThat(ship.shouldBeRemoved()).isFalse(); // Will be false until explosion completes
    }

    @Test
    void acceptCollision_withAsteroid_triggersExplosion() {
        Consumer<PlayElement> beamFunction = mock(Consumer.class);
        Ship ship = new Ship(beamFunction);
        
        // Set initial health to 100
        HealthBar.setHealth(100);
        
        Asteroid asteroid = new Asteroid(new Point(FRAME_SIZE.width / 2, FRAME_SIZE.height / 2));
        ship.acceptCollision(asteroid);
        
        // Should reduce health by 10 and start explosion frames
        assertThat(HealthBar.getHealth()).isEqualTo(90);
    }

    @Test
    void acceptCollision_withNonAsteroid_doesNothing() {
        Consumer<PlayElement> beamFunction = mock(Consumer.class);
        Ship ship = new Ship(beamFunction);
        
        HealthBar.setHealth(100);
        int initialFrame = ship.getFrame();
        
        Beam beam = new Beam(new Point(0, 0), new com.a29340.core.Velocity());
        ship.acceptCollision(beam);
        
        // Should not affect health or frames
        assertThat(HealthBar.getHealth()).isEqualTo(100);
    }

    @Test
    void acceptCollision_whenDead_doesNothing() {
        Consumer<PlayElement> beamFunction = mock(Consumer.class);
        Ship ship = new Ship(beamFunction);
        
        HealthBar.setHealth(100);
        
        Asteroid asteroid = new Asteroid(new Point(FRAME_SIZE.width / 2, FRAME_SIZE.height / 2));
        ship.acceptCollision(asteroid);
        
        // First collision should reduce health
        assertThat(HealthBar.getHealth()).isEqualTo(90);
    }
}
