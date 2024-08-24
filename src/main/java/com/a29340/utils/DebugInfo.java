package com.a29340.utils;

import com.a29340.elements.Ship;

import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.a29340.utils.Constants.FRAME_SIZE;

public class DebugInfo {
    static long lastRepaint = System.currentTimeMillis();
    static Font fpsFont = new Font("Arial", Font.PLAIN, 20);
    static Font debugFont = new Font("Arial", Font.PLAIN, 15);
    static Map<Long, String> input = new HashMap<>();

    static public void print(Graphics2D g2d) {
        if (Configurations.debugMode()) {
            long now = System.currentTimeMillis();
            g2d.setColor(Color.CYAN);
            g2d.setFont(fpsFont);
            if (now - lastRepaint > 0) {
                g2d.drawString("FPS: " + 1000/(now - lastRepaint), 5, FRAME_SIZE.height);
                printDebugMessages(g2d, now);
            }
            lastRepaint = now;
        }
    }

    public static void printDebugMessage(String message) {
        input.put(System.currentTimeMillis(), message);
    }

    private static void printDebugMessages(Graphics2D g2d, long now) {
        g2d.setColor(Color.RED);
        g2d.setFont(debugFont);
        AtomicInteger i = new AtomicInteger();
        input.entrySet().stream().toList().stream().sorted(Comparator.comparingLong(Map.Entry::getKey)).forEachOrdered(entry -> {
            g2d.drawString(entry.getValue(), 5, FRAME_SIZE.height - 30 - i.getAndIncrement() * 20);
            if (now - entry.getKey() > 1500) {
                input.remove(entry.getKey());
            }
        });
    }
}
