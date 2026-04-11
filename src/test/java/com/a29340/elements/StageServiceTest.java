package com.a29340.elements;

import com.a29340.core.Scene;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class StageServiceTest {

    @BeforeEach
    void setUp() {
        for (Timer timer : StageServiceTest.timers) {
            if (timer.isRunning()) {
                timer.stop();
            }
        }
        StageServiceTest.timers.clear();
    }

    @AfterEach
    void tearDown() {
        for (Timer timer : StageServiceTest.timers) {
            if (timer.isRunning()) {
                timer.stop();
            }
        }
        StageServiceTest.timers.clear();
    }

    static java.util.List<Timer> timers = new java.util.ArrayList<>();

    @Test
    void addScene_addsSceneToList() {
        Scene mockScene = mock(Scene.class);

        StageService.addScene(mockScene);
    }

    @Test
    void getCurrentScene_afterAddScene_returnsNotNull() {
        Scene mockScene = mock(Scene.class);
        StageService.addScene(mockScene);

        Scene scene = StageService.getCurrentScene();

        assertThat(scene).isNotNull();
    }

    @Test
    void isPaused_initiallyFalse() {
        Boolean paused = StageService.isPaused();

        assertThat(paused).isFalse();
    }

    @Test
    void setRepaint_acceptsRunnable() {
        Runnable mockRunnable = mock(Runnable.class);

        StageService.setRepaint(mockRunnable);

        mockRunnable.run();
        verify(mockRunnable).run();
    }

    @Test
    void addTimer_withNull_doesNotThrow() {
        StageService.addTimer(null);
    }

    @Test
    void addTimer_withValidTimer_addsToList() {
        Timer timer = mock(Timer.class);

        StageService.addTimer(timer);
    }
}
