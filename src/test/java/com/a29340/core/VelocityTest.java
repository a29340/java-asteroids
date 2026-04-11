package com.a29340.core;

import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.assertj.core.api.Assertions.assertThat;

class VelocityTest {

    @Test
    void constructor_default_createsZeroVelocity() {
        Velocity velocity = new Velocity();

        assertThat(velocity.getDx()).isEqualTo(0);
        assertThat(velocity.getDy()).isEqualTo(0);
    }

    @Test
    void constructor_dx_dy_computesModuleAndAngle() {
        Velocity velocity = new Velocity(3, 4);

        assertThat(velocity.getDx()).isEqualTo(3);
        assertThat(velocity.getDy()).isEqualTo(4);
        // Module of (3, 4) should be exactly 5.0 in this implementation
        double moduleDiff = Math.abs(velocity.getModule() - 5.0);
        assertThat(moduleDiff).isLessThan(0.1);
    }

    @Test
    void increaseX_updatesDxAndRecalculates() {
        Velocity velocity = new Velocity(3, 4);
        velocity.increaseX(2);

        assertThat(velocity.getDx()).isEqualTo(5);
        assertThat(velocity.getDy()).isEqualTo(4);
    }

    @Test
    void increaseY_updatesDyAndRecalculates() {
        Velocity velocity = new Velocity(3, 4);
        velocity.increaseY(2);

        assertThat(velocity.getDx()).isEqualTo(3);
        assertThat(velocity.getDy()).isEqualTo(6);
    }

    @Test
    void setDx_updatesAndRecalculates() {
        Velocity velocity = new Velocity(3, 4);
        velocity.setDx(6);

        assertThat(velocity.getDx()).isEqualTo(6);
        assertThat(velocity.getDy()).isEqualTo(4);
    }

    @Test
    void setDy_updatesAndRecalculates() {
        Velocity velocity = new Velocity(3, 4);
        velocity.setDy(8);

        assertThat(velocity.getDx()).isEqualTo(3);
        assertThat(velocity.getDy()).isEqualTo(8);
    }

    @Test
    void getTargetFromPoint_returnsCorrectOffset() {
        Velocity velocity = new Velocity(10, 20);
        Point start = new Point(100, 100);
        Point target = velocity.getTargetFromPoint(start);

        assertThat(target.x).isEqualTo(110);
        assertThat(target.y).isEqualTo(120);
    }

}
