package com.a29340.core;

import javax.swing.event.MouseInputListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class Scene {
    protected Runnable setup;
    protected Runnable scene;
    protected BooleanSupplier ended;

    protected List<PlayElement> playElements = new ArrayList<>();
    protected List<UIElement> uiElements = new ArrayList<>();
    protected List<KeyListener> keyListeners = new ArrayList<>();
    protected List<MouseInputListener> mouseInputListeners = new ArrayList<>();

    protected Scene() {
    }

    public Runnable getSetup() {
        return setup;
    }

    public Runnable getScene() {
        return scene;
    }

    public BooleanSupplier getEnded() {
        return ended;
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
