package com.a29340.elements;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.a29340.elements.HealthBar.INITIAL_HEALTH;
import static org.assertj.core.api.Assertions.assertThat;

class HealthBarTest {

    @BeforeEach
    void setUp() {
        HealthBar.setHealth(INITIAL_HEALTH);
    }

    @AfterEach
    void tearDown() {
        HealthBar.setHealth(INITIAL_HEALTH);
    }

    @Test
    void getInstance_returnsSameInstance() {
        HealthBar instance1 = HealthBar.getInstance();
        HealthBar instance2 = HealthBar.getInstance();

        assertThat(instance1).isSameAs(instance2);
    }

    @Test
    void getHealth_returnsInitialHealth() {
        HealthBar.setHealth(100);

        assertThat(HealthBar.getHealth()).isEqualTo(100);
    }

    @Test
    void setHealth_updatesHealth() {
        HealthBar.setHealth(50);

        assertThat(HealthBar.getHealth()).isEqualTo(50);
    }

    @Test
    void setHealth_toZero() {
        HealthBar.setHealth(0);

        assertThat(HealthBar.getHealth()).isEqualTo(0);
    }

    @Test
    void setHealth_toNegativeValue() {
        HealthBar.setHealth(-10);

        assertThat(HealthBar.getHealth()).isEqualTo(-10);
    }

    @Test
    void setHealth_toValueGreaterThanInitial() {
        HealthBar.setHealth(200);

        assertThat(HealthBar.getHealth()).isEqualTo(200);
    }

    @Test
    void resetRestoresInitialHealth() {
        HealthBar.setHealth(50);
        HealthBar.setHealth(INITIAL_HEALTH);

        assertThat(HealthBar.getHealth()).isEqualTo(INITIAL_HEALTH);
    }

    @Test
    void multipleSetHealth_callsWork() {
        HealthBar.setHealth(100);
        assertThat(HealthBar.getHealth()).isEqualTo(100);

        HealthBar.setHealth(80);
        assertThat(HealthBar.getHealth()).isEqualTo(80);

        HealthBar.setHealth(60);
        assertThat(HealthBar.getHealth()).isEqualTo(60);
    }
}
