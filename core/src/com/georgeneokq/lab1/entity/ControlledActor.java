package com.georgeneokq.lab1.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class ControlledActor extends Actor {
    protected float dx;
    protected float dy;

    private Controls controls;

    public ControlledActor(float width, float height) {
        this(width, height, 0, 0, 0, 0, null);
    }

    public ControlledActor(float width, float height, Controls controls) {
        this(width, height, 0, 0, 0, 0, controls);
    }

    public ControlledActor(float width, float height, float x, float y, Controls controls) {
        this(width, height, x, y, 0, 0, controls);
    }

    /*
     * Speed should be provided if the entity is movable
     */
    public ControlledActor(float width, float height, float x, float y, float dx, float dy, Controls controls) {
        this.setWidth(width);
        this.setHeight(height);
        this.setX(x);
        this.setY(y);
        this.dx = dx;
        this.dy = dy;
        this.controls = controls;
    }

    // Move the actor according to speed in each direction
    public void update() {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    @Override
    public abstract void draw(Batch batch, float parentAlpha);

    public Controls getControls() {
        return controls;
    }

    public void setControls(Controls controls) {
        this.controls = controls;
    }
}
