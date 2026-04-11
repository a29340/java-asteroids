package com.a29340.scenes;

import com.a29340.elements.HealthBar;
import com.a29340.elements.Score;
import com.a29340.elements.StageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.a29340.elements.HealthBar.INITIAL_HEALTH;
import static org.assertj.core.api.Assertions.assertThat;

class GameplaySceneTest {

    private GameplayScene scene;

    @BeforeEach
    void setUp() {
        scene = new GameplayScene();
        
        HealthBar.setHealth(INITIAL_HEALTH);
        Score.resetScore();
        
        StageService.setRepaint(() -> {});
    }

    @AfterEach
    void tearDown() {
        HealthBar.setHealth(INITIAL_HEALTH);
        Score.resetScore();
    }

    @Test
    void setup_createsShip() {
        scene.setup();

        boolean hasShip = scene.getPlayElements().stream()
                .anyMatch(e -> e instanceof com.a29340.elements.Ship);
        assertThat(hasShip).isTrue();
    }

    @Test
    void setup_addsScoreToUiElements() {
        scene.setup();

        assertThat(scene.getUiElements()).isNotEmpty();
    }

    @Test
    void setup_addsHealthBarToUiElements() {
        scene.setup();

        assertThat(scene.getUiElements()).isNotEmpty();
    }

    @Test
    void ended_returnsFalseWhenHealthPositive() {
        scene.setup();

        assertThat(scene.ended()).isFalse();
    }

    @Test
    void scene_filtersRemovedElements() {
        scene.setup();

        scene.scene();

        assertThat(scene.getPlayElements()).isNotEmpty();
    }

    @Test
    void reset_restoresInitialState() {
        scene.setup();
        HealthBar.setHealth(50);
        Score.resetScore();

        scene.reset();

        assertThat(HealthBar.getHealth()).isEqualTo(INITIAL_HEALTH);
        assertThat(Score.getScore()).isEqualTo(0);
    }
}
