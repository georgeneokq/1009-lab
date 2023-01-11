package com.georgeneokq.lab1.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public abstract class Entity extends Image {
    protected Drawable drawable;
    private float dx;
    private float dy;

    private EntityControls controls;

    public Entity(Drawable drawable, float width, float height) {
        this(drawable, width, height, 0, 0, 0, 0, null);
    }

    public Entity(Drawable drawable, float width, float height, EntityControls controls) {
        this(drawable, width, height, 0, 0, 0, 0, controls);
    }

    public Entity(Drawable drawable, float width, float height, float x, float y, EntityControls controls) {
        this(drawable, width, height, x, y, 0, 0, controls);
    }

    /*
     * Speed should be provided if the entity is movable
     */
    public Entity(Drawable drawable, float width, float height, float x, float y, float dx, float dy, EntityControls controls) {
        super(drawable);
        this.drawable = drawable;
        this.setWidth(width);
        this.setHeight(height);
        this.setX(x);
        this.setY(y);
        this.dx = dx;
        this.dy = dy;
        this.controls = controls;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawable.draw(batch, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void moveUp() {
        this.setY(this.getY() + this.dy);
    }

    public void moveDown() {
        this.setY(this.getY() - this.dy);
    }

    public void moveLeft() {
        this.setX(this.getX() - this.dx);
    }

    public void moveRight() {
        this.setX(this.getX() + this.dx);
    }

    /*
     * Read the keys currently pressed.
     * If they are any of the specified controls, update coordinates accordingly
     */
    public void handleKeyPress() {
        if(controls == null)
            return;

        if(Gdx.input.isKeyPressed(controls.getUpKey())) {
            this.moveUp();
        }
        if(Gdx.input.isKeyPressed(controls.getDownKey())) {
            this.moveDown();
        }
        if(Gdx.input.isKeyPressed(controls.getLeftKey())) {
            this.moveLeft();
        }
        if(Gdx.input.isKeyPressed(controls.getRightKey())) {
            this.moveRight();
        }
    }

    public void setDrawable(Drawable drawable) {
        setDrawable(drawable, this.getX(), this.getY());
    }

    public void setDrawable(Drawable drawable, float x, float y) {
        this.drawable = drawable;
        this.setX(x);
        this.setY(y);
    }

    public EntityControls getControls() {
        return controls;
    }

    public void setControls(EntityControls controls) {
        this.controls = controls;
    }
}
