package com.a29340.core;

import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.assertj.core.api.Assertions.assertThat;

class EntityTest {

    @Test
    void getPosition_returnsInitializedPoint() {
        TestEntity entity = new TestEntity();
        
        assertThat(entity.getPosition()).isNotNull();
        assertThat(entity.getPosition().x).isEqualTo(0);
        assertThat(entity.getPosition().y).isEqualTo(0);
    }

    @Test
    void setPosition_withPoint_updatesPosition() {
        TestEntity entity = new TestEntity();
        Point newPoint = new Point(100, 200);
        
        entity.setPosition(newPoint);
        
        assertThat(entity.getPosition()).isSameAs(newPoint);
    }

    @Test
    void setPosition_withCoordinates_updatesPosition() {
        TestEntity entity = new TestEntity();
        
        entity.setPosition(50, 75);
        
        assertThat(entity.getPosition().x).isEqualTo(50);
        assertThat(entity.getPosition().y).isEqualTo(75);
    }

    private static class TestEntity extends Entity {
        @Override
        public void update(java.awt.Graphics2D g2d) {
            // No-op for testing
        }
    }
}
