package com.a29340.elements;

import com.a29340.core.Scene;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.a29340.utils.Constants.FPS;

public class StageService {

    private List<Scene> scenes = new ArrayList<>();
    private Scene currentScene;

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public void start() {
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
    }
}
