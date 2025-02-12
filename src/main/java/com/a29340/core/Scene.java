package com.a29340.core;

import javax.swing.event.MouseInputListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    protected List<PlayElement> playElements = new ArrayList<>();
    protected List<UIElement> uiElements = new ArrayList<>();
    protected List<KeyListener> keyListeners = new ArrayList<>();
    protected List<MouseInputListener> mouseInputListeners = new ArrayList<>();

    public abstract void scene();

    public abstract void setup();

    public abstract boolean ended();

    public void runScene() {
        scene();
    }

    public List<PlayElement> getPlayElements() {
        return playElements;
    }

    public List<UIElement> getUiElements() {
        return uiElements;
    }

    public List<KeyListener> getKeyListeners() {
        return keyListeners;
    }

    public List<MouseInputListener> getMouseInputListeners() {
        return mouseInputListeners;
    }
}
