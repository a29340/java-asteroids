package com.a29340.elements;

import com.a29340.core.Scene;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.a29340.utils.Constants.FPS;

public class StageService {

    private static List<Scene> scenes = new ArrayList<>();
    private static Scene currentScene;
    private static Boolean isPaused = false;

    static List<Timer> timers = new ArrayList<>();

    public static void addScene(Scene scene) {
        scenes.add(scene);
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static void start() {
        Iterator<Scene> iterator = scenes.iterator();
        currentScene = iterator.next();
        currentScene.getSetup().run();
        Timer timer = new Timer(1000 / FPS, e -> {
            if (currentScene != null) {
                if (currentScene.getEnded().getAsBoolean()) {
                    currentScene = iterator.hasNext() ? iterator.next() : null;
                    currentScene.getSetup().run();
                } else {
                    currentScene.getScene().run();
                }
            }
        });
        timer.start();
        timers.add(timer);
    }

    public static void pause() {
        timers.forEach(Timer::stop);
        isPaused = true;
    }

    public static void resume() {
        timers.forEach(Timer::start);
        isPaused = false;
    }

    public static Boolean isPaused() {
        return isPaused;
    }

    public static void addTimer(Timer timer) {
        if (timer != null) {
            timers.add(timer);
        }
    }
}
