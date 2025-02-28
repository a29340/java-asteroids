package com.a29340.scenes;

import com.a29340.core.Scene;
import com.a29340.elements.StartMenu;

public class StartMenuScene extends Scene {

    StartMenu startMenu = new StartMenu();


    @Override
    public void scene() {

    }

    @Override
    public void setup() {
        uiElements.add(startMenu);
        mouseInputListeners.add(startMenu);
    }

    @Override
    public boolean ended() {
        return startMenu.isEnded();
    }

}
