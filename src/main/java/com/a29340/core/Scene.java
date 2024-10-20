package com.a29340.core;

import java.util.function.BooleanSupplier;

public class Scene {
    Runnable setup;
    Runnable scene;
    BooleanSupplier ended;

    public Scene(Runnable setup, Runnable scene, BooleanSupplier ended) {
        this.setup = setup;
        this.ended = ended;
        this.scene = scene;
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
}
