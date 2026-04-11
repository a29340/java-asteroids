package com.a29340.core;

import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.assertj.core.api.Assertions.assertThat;

class PixelTest {

    @Test
    void constructor_withColor_setsColor() {
        Color color = Color.RED;
        Pixel pixel = new Pixel(color);

        assertThat(pixel.getColor()).isEqualTo(color);
    }

    @Test
    void constructor_withColorAndVelocity_setsBoth() {
        Color color = Color.BLUE;
        Velocity velocity = new Velocity(1, 2);
        Pixel pixel = new Pixel(color, velocity);

        assertThat(pixel.getColor()).isEqualTo(color);
        assertThat(pixel.getVelocity()).isEqualTo(velocity);
    }

    @Test
    void getVelocity_returnsNullWhenNotSet() {
        Pixel pixel = new Pixel(Color.GREEN);

        assertThat(pixel.getVelocity()).isNull();
    }

    @Test
    void setVelocity_updatesVelocity() {
        Pixel pixel = new Pixel(Color.WHITE);
        Velocity velocity = new Velocity(5, 10);

        pixel.setVelocity(velocity);

        assertThat(pixel.getVelocity()).isEqualTo(velocity);
    }

    @Test
    void getColor_returnsSetColor() {
        Pixel pixel = new Pixel(Color.YELLOW);

        assertThat(pixel.getColor()).isEqualTo(Color.YELLOW);
    }

    @Test
    void setColor_updatesColor() {
        Pixel pixel = new Pixel(Color.BLACK);

        pixel.setColor(Color.CYAN);

        assertThat(pixel.getColor()).isEqualTo(Color.CYAN);
    }

    @Test
    void constructor_withNullVelocity_setsNullVelocity() {
        Color color = Color.MAGENTA;
        Pixel pixel = new Pixel(color, null);

        assertThat(pixel.getVelocity()).isNull();
    }

    @Test
    void setVelocity_toNull_clearsVelocity() {
        Pixel pixel = new Pixel(Color.ORANGE);
        Velocity velocity = new Velocity(1, 1);

        pixel.setVelocity(velocity);
        assertThat(pixel.getVelocity()).isEqualTo(velocity);

        pixel.setVelocity(null);
        assertThat(pixel.getVelocity()).isNull();
    }

    @Test
    void setColor_toSameColor_noException() {
        Pixel pixel = new Pixel(Color.RED);
        
        // Setting the same color should not throw an exception
        pixel.setColor(Color.RED);
        assertThat(pixel.getColor()).isEqualTo(Color.RED);
    }

    @Test
    void constructor_withTransparentPixel_setsTransparent() {
        Color transparentColor = new Color(255, 0, 0, 0); // Red with alpha=0
        Pixel pixel = new Pixel(transparentColor);

        assertThat(pixel.getColor()).isEqualTo(transparentColor);
        assertThat(pixel.getColor().getAlpha()).isEqualTo(0);
    }
}
