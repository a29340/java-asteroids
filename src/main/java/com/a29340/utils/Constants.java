package com.a29340.utils;

import java.awt.*;

public final class Constants {
    final public static Dimension FRAME_SIZE = new Dimension(1400, 1000);
    final public static int FPS = 60;
    final public static int PIXEL_DIMENSION = 4;
    final public static int SHIP_VEL_STEP = (int) (300l / FPS);
    final public static int BEAM_SPEED = (int) (900l / FPS);
    final public static int DISPERSION_RATE = 180;
    final public static int SHIP_COOLDOWN_FRAMES = (int) (0.7 * FPS);
    final public static int ASTEROID_EXPLOSION_FRAMES = (int) (0.6 * FPS);
    final public static int SHIP_EXPLOSION_FRAMES = (int) (2.0 * FPS);
}
