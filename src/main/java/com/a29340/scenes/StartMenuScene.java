package com.a29340.scenes;

import com.a29340.core.Scene;
import com.a29340.core.ClickableText;
import com.a29340.utils.Constants;

import java.awt.*;

public class StartMenuScene extends Scene {

    ClickableText startText;
    private boolean ended = false;

    @Override
    public void scene() {

    }

    @Override
    public void setup() {
        startText = new ClickableText("START", 20, new Point(Constants.FRAME_SIZE.width/2, Constants.FRAME_SIZE.height/2), () -> this.ended = true, 3f);
        uiElements.add(startText);
        mouseInputListeners.add(startText);
    }

    @Override
    public boolean ended() {
        return ended;
    }

}
