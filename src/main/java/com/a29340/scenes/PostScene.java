package com.a29340.scenes;

import com.a29340.core.Scene;
import com.a29340.core.ClickableText;
import com.a29340.core.Text;
import com.a29340.elements.Score;
import com.a29340.elements.StageService;
import com.a29340.utils.Constants;

import java.awt.*;

public class PostScene extends Scene {


    private ClickableText exit;
    private boolean ended = false;
    private Text finalScore;

    public PostScene() {
        finalScore = new Text("", 30, new Point(Constants.FRAME_SIZE.width / 2, 300), Text.Alignement.CENTER);
        uiElements.add(finalScore);
        exit = new ClickableText("EXIT", 30, new Point(Constants.FRAME_SIZE.width / 2, 500), () -> this.ended = true, 2f);
        ClickableText retry = new ClickableText("RETRY", 30, new Point(Constants.FRAME_SIZE.width / 2, 700),
                () -> {
                    StageService.restartGameplay();
                }, 2f);
        uiElements.add(exit);
        uiElements.add(retry);
        mouseInputListeners.add(exit);
        mouseInputListeners.add(retry);
    }

    @Override
    public void scene() {

    }

    @Override
    public void setup() {
        finalScore.setText("Final score: " + Score.getScore());
    }

    @Override
    public boolean ended() {
        return ended;
    }

}
