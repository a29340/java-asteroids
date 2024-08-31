package com.a29340.elements;

import com.a29340.core.Image;
import com.a29340.core.UIElement;
import com.a29340.utils.Graphics;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.a29340.utils.Constants.FRAME_SIZE;
import static com.a29340.utils.Constants.PIXEL_DIMENSION;

public class HealthBar extends UIElement {

    public final static Integer INITIAL_HEALTH = 160;
    private Integer health = INITIAL_HEALTH;
    private final static Point position;
    private final static List<Image> barsImages = new ArrayList<>();
    private static Image healthBarImage;
    static {
            healthBarImage = new Image("images/healthbar/data/000.png");
            String[] names = new String[] {"002-000.png", "002-001.png", "002-002.png", "002-003.png", "002-004.png", "002-005.png", "002-006.png", "002-007.png", "002-008.png", "002-009.png", "002-010.png", "002-011.png", "002-012.png", "002-013.png", "002-014.png", "002-015.png"};
            for(String name : names) {
                barsImages.add(new Image("images/healthbar/data/" + name));
            }
        int x = FRAME_SIZE.width / 2 - healthBarImage.getWidth() * PIXEL_DIMENSION / 2;
        int y = FRAME_SIZE.height - healthBarImage.getHeight() * PIXEL_DIMENSION - 50;
        position = new Point(x,y);
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    @Override
    public void update(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        Graphics.drawImage(g2d, healthBarImage, position);
        int index = (int) Math.ceil((health.floatValue() / INITIAL_HEALTH) * (barsImages.size()));
        if (index > 0) {
            for (int i = 0; i < index; i++) {
                Graphics.drawImage(g2d, barsImages.get(i), position);
            }
        }
    }
}
