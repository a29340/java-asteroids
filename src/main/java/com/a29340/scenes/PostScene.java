package com.a29340.scenes;

import com.a29340.core.Scene;
import com.a29340.core.ClickableText;
import com.a29340.elements.Score;
import com.a29340.elements.StageService;
import com.a29340.utils.Constants;

import java.awt.*;

public class PostScene extends Scene {


    private ClickableText exit;
    private boolean ended = false;

    @Override
    public void scene() {

    }

    @Override
    public void setup() {
        Score.setInstancePosition(Constants.FRAME_SIZE.width / 2, Constants.FRAME_SIZE.height / 2);
        uiElements.add(Score.getInstance());
        exit = new ClickableText("EXIT", 130, 30, new Point(Constants.FRAME_SIZE.width / 2, Constants.FRAME_SIZE.height / 2 + 40), () -> this.ended = true);
        ClickableText retry = new ClickableText("RETRY", 130, 30, new Point(Constants.FRAME_SIZE.width / 2, Constants.FRAME_SIZE.height / 2 + 100),
                () -> {
                    StageService.restartGameplay();
                });
        uiElements.add(exit);
        uiElements.add(retry);
        mouseInputListeners.add(exit);
        mouseInputListeners.add(retry);
    }

    @Override
    public boolean ended() {
        return ended;
    }

}
