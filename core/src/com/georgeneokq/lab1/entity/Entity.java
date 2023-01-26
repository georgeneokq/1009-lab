package com.georgeneokq.lab1.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Entity extends Brain {
    protected float width;
    protected float height;
    protected float x;
    protected float y;
    protected float dx;
    protected float dy;

    private Controls controls;

    public Entity(float width, float height) {
        this(width, height, 0, 0, null);
    }

    public Entity(float width, float height, Controls controls) {
        this(width, height, 0, 0, controls);
    }

    public Entity(float width, float height, float x, float y, Controls controls) {
        this.setWidth(width);
        this.setHeight(height);
        this.setX(x);
        this.setY(y);
        this.dx = 0;
        this.dy = 0;
        this.controls = controls;
    }

    // Move the entity
    public void handleKeyPress() {
        // If the entity is idle, perform idle actions
        if(isIdle()) {
            idle();
        } else {
            // Perform action specified by subclass.
            if(Gdx.input.isKeyPressed(controls.getUpKey()))
                moveUp();
            if(Gdx.input.isKeyPressed(controls.getLeftKey()))
                moveLeft();
            if(Gdx.input.isKeyPressed(controls.getDownKey()))
                moveDown();
            if(Gdx.input.isKeyPressed(controls.getRightKey()))
                moveRight();
        }

        // Update the coordinates
        x += dx;
        y += dy;
    }

    // No controls of this entity being pressed
    public final boolean isIdle() {
        return (!Gdx.input.isKeyPressed(controls.getUpKey()) &&
                !Gdx.input.isKeyPressed(controls.getLeftKey()) &&
                !Gdx.input.isKeyPressed(controls.getDownKey()) &&
                !Gdx.input.isKeyPressed(controls.getRightKey()));
    }

    @Override
    public abstract void draw(Batch batch, float parentAlpha);

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Controls getControls() {
        return controls;
    }

    public void setControls(Controls controls) {
        this.controls = controls;
    }
}
