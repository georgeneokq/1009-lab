package com.georgeneokq.lab1.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor {
    protected float width;
    protected float height;
    protected float x;
    protected float y;
    protected float dx;
    protected float dy;
    protected float speed;
    private Controls controls;
    private Brain brain;

    public Entity(float width, float height, float x, float y, float speed, Controls controls) {
        this.setWidth(width);
        this.setHeight(height);
        this.setX(x);
        this.setY(y);
        this.dx = 0;
        this.dy = 0;
        this.speed = speed;
        this.controls = controls;
        this.brain = new Brain(this);
    }

    // Move the entity
    public void handleKeyPress() {
        // If the entity is idle, perform idle actions
        if(isIdle()) {
            brain.idle();
        } else {
            // Perform action specified by subclass.
            if(Gdx.input.isKeyPressed(controls.getUpKey()))
                brain.moveUp();
            if(Gdx.input.isKeyPressed(controls.getLeftKey()))
                brain.moveLeft();
            if(Gdx.input.isKeyPressed(controls.getDownKey()))
                brain.moveDown();
            if(Gdx.input.isKeyPressed(controls.getRightKey()))
                brain.moveRight();
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

    public void changeDxTowardsZero(float rateOfChange) {
        if(dx > 0) {
            float forecastedDx = dx - rateOfChange * Gdx.graphics.getDeltaTime();
            if(forecastedDx < 0)
                dx = 0;
            else
                dx = forecastedDx;
        }

        if(dx < 0) {
            float forecastedDx = dx + rateOfChange * Gdx.graphics.getDeltaTime();
            if(forecastedDx > 0)
                dx = 0;
            else
                dx = forecastedDx;
        }
    }

    public void changeDyTowardsZero(float rateOfChange) {
        if(dy > 0) {
            float forecastedDy = dy - rateOfChange * Gdx.graphics.getDeltaTime();
            if(forecastedDy < 0)
                dy = 0;
            else
                dy = forecastedDy;
        }
        if(dy < 0) {
            float forecastedDy = dy + rateOfChange * Gdx.graphics.getDeltaTime();
            if(forecastedDy > 0)
                dy = 0;
            else
                dy = forecastedDy;
        }
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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public Controls getControls() {
        return controls;
    }

    public void setControls(Controls controls) {
        this.controls = controls;
    }

    public Brain getBrain() {
        return brain;
    }

    public void setBrain(Brain brain) {
        this.brain = brain;
    }
}
