package com.a29340.core;

import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class VelocityTest {

    @Test
    void constructor_default_createsZeroVelocity() {
        Velocity velocity = new Velocity();

        assertThat(velocity.getDx()).isEqualTo(0);
        assertThat(velocity.getDy()).isEqualTo(0);
        assertThat(velocity.getModule()).isCloseTo(0, within(0.001));
        assertThat(velocity.getAngle()).isCloseTo(0, within(0.001));
    }

    @Test
    void constructor_angle_module_computesComponents() {
        Velocity velocity = new Velocity(Math.PI / 2, 10);

        assertThat(velocity.getModule()).isCloseTo(10, within(0.01));
        assertThat(velocity.getAngle()).isCloseTo(Math.PI / 2, within(0.01));
    }

    @Test
    void constructor_dx_dy_computesModuleAndAngle() {
        Velocity velocity = new Velocity(3, 4);

        assertThat(velocity.getDx()).isEqualTo(3);
        assertThat(velocity.getDy()).isEqualTo(4);
        assertThat(velocity.getModule()).isCloseTo(5, within(0.01));
    }

    @Test
    void increaseX_updatesDxAndRecalculates() {
        Velocity velocity = new Velocity(3, 4);
        velocity.increaseX(2);

        assertThat(velocity.getDx()).isEqualTo(5);
        assertThat(velocity.getDy()).isEqualTo(4);
        assertThat(velocity.getModule()).isCloseTo(Math.sqrt(41), within(0.01));
    }

    @Test
    void increaseY_updatesDyAndRecalculates() {
        Velocity velocity = new Velocity(3, 4);
        velocity.increaseY(2);

        assertThat(velocity.getDx()).isEqualTo(3);
        assertThat(velocity.getDy()).isEqualTo(6);
        assertThat(velocity.getModule()).isCloseTo(Math.sqrt(45), within(0.01));
    }

    @Test
    void setDx_updatesAndRecalculates() {
        Velocity velocity = new Velocity(3, 4);
        velocity.setDx(6);

        assertThat(velocity.getDx()).isEqualTo(6);
        assertThat(velocity.getDy()).isEqualTo(4);
        assertThat(velocity.getModule()).isCloseTo(Math.sqrt(52), within(0.01));
    }

    @Test
    void setDy_updatesAndRecalculates() {
        Velocity velocity = new Velocity(3, 4);
        velocity.setDy(8);

        assertThat(velocity.getDx()).isEqualTo(3);
        assertThat(velocity.getDy()).isEqualTo(8);
        assertThat(velocity.getModule()).isCloseTo(Math.sqrt(73), within(0.01));
    }

    @Test
    void getTargetFromPoint_returnsCorrectOffset() {
        Velocity velocity = new Velocity(10, 20);
        Point start = new Point(100, 100);
        Point target = velocity.getTargetFromPoint(start);

        assertThat(target.x).isEqualTo(110);
        assertThat(target.y).isEqualTo(120);
    }

    @Test
    void toString_returnsExpectedFormat() {
        Velocity velocity = new Velocity(5, 12);
        String result = velocity.toString();

        assertThat(result).contains("dx=5");
        assertThat(result).contains("dy=12");
        assertThat(result).contains("module=");
        assertThat(result).contains("angle=");
    }
}
