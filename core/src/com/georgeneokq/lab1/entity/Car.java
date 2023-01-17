package com.georgeneokq.lab1.entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.georgeneokq.lab1.factory.DrawableFactory;
import com.georgeneokq.lab1.manager.TextureAtlasManager;

public class Car extends ControlledActor {

    private float acceleration = 2;
    private float speedLimit = 200;

    // A car will be drawn using a car image
    private Drawable drawable;

    public Car(float width, float height, float x, float y, Controls controls) {
        super(width, height, x, y, controls);

        this.drawable = DrawableFactory.fromTextureAtlas(TextureAtlasManager.getTextureAtlas("lab1.atlas"), "car");
        this.drawable.setMinWidth(getWidth());
        this.drawable.setMinHeight(getHeight());
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public void setSpeedLimit(float speedLimit) {
        this.speedLimit = speedLimit;
    }

    /*
     * Update based on keys pressed
     */
    @Override
    public void update() {
        // Acceleration
        if(Gdx.input.isKeyPressed(getControls().getRightKey())) {
            this.dx += (acceleration * Gdx.graphics.getDeltaTime());
        }
        // Forced deceleration
        else if(Gdx.input.isKeyPressed(getControls().getLeftKey())) {
            this.dx += (-acceleration * Gdx.graphics.getDeltaTime());
        } else {
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

        // Apply speed limit
        if(this.dx > speedLimit)
            this.dx = speedLimit;
        if(this.dx < -speedLimit)
            this.dx = -speedLimit;

        super.update();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.drawable.draw(batch, getX(), getY(), getWidth(), getHeight());
    }
}