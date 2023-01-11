package com.georgeneokq.lab1.entity;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Stickman extends ControlledActor {
    public Stickman(Drawable drawable, float width, float height, float x, float y, float dx, float dy) {
        super(drawable, width, height, x, y, dx, dy, null);
    }

    public Stickman(Drawable drawable, float width, float height, float x, float y, float dx, float dy, Controls controls) {
        super(drawable, width, height, x, y, dx, dy, controls);
    }
}
