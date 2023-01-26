package com.georgeneokq.lab1.entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.georgeneokq.lab1.factory.DrawableFactory;
import com.georgeneokq.lab1.manager.TextureAtlasManager;

public class Car extends CollidableEntity {

    private float acceleration = 2;
    private float forwardSpeedLimit = 200;
    private float reverseSpeedLimit = 2;

    // A car will be drawn using a car image
    private Drawable drawable;

    public Car(float width, float height, float x, float y, Controls controls) {
        super(width, height, x, y, controls);

        this.drawable = DrawableFactory.fromTextureAtlas(TextureAtlasManager.getTextureAtlas("lab2.atlas"), "car");
        this.drawable.setMinWidth(getWidth());
        this.drawable.setMinHeight(getHeight());
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public void setForwardSpeedLimit(float forwardSpeedLimit) {
        this.forwardSpeedLimit = forwardSpeedLimit;
    }

    public void setReverseSpeedLimit(float reverseSpeedLimit) {
        this.reverseSpeedLimit = reverseSpeedLimit;
    }

    private void accelerate() {
        this.dx += (acceleration * Gdx.graphics.getDeltaTime());

        if(this.dx > forwardSpeedLimit)
            dx = forwardSpeedLimit;
    }

    private void forcedDecelerate() {
        this.dx += (-acceleration * Gdx.graphics.getDeltaTime());

        if(this.dx < -reverseSpeedLimit)
            dx = -reverseSpeedLimit;
    }

    private void naturalDecelerate() {
        // Forward movement natural deceleration
        if(dx > 0) {
            float newDx = this.dx + -(0.5f * acceleration) * Gdx.graphics.getDeltaTime();
            if(newDx < 0)
                this.dx = 0;
            else
                this.dx = newDx;
        }

        // Reverse movement natural deceleration
        if(dx < 0) {
            float newDx = this.dx + (0.5f * acceleration) * Gdx.graphics.getDeltaTime();
            if(newDx > 0)
                this.dx = 0;
            else
                this.dx = newDx;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawable.draw(batch, x, y, width, height);
    }

    @Override
    public void moveUp() {}

    @Override
    public void moveLeft() {
        forcedDecelerate();
    }

    @Override
    public void moveDown() {}

    @Override
    public void moveRight() {
        accelerate();
    }

    @Override
    public void idle() {
        naturalDecelerate();
    }

    @Override
    public void handleCollision() {
        dx = dx > 0 ? -2 : 2;
    }
}