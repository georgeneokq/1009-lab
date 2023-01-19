package com.georgeneokq.lab1.entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.georgeneokq.lab1.factory.DrawableFactory;
import com.georgeneokq.lab1.manager.TextureAtlasManager;

public class Airplane extends Entity {

    private float verticalAcceleration = 2;
    private float horizontalAcceleration = 2;
    private float forwardSpeedLimit = 200;
    private float reverseSpeedLimit = 2;
    private float upwardSpeedLimit = 10;
    private float downwardSpeedLimit = 2;

    // A car will be drawn using a car image
    private Drawable drawable;

    public Airplane(float width, float height, float x, float y, Controls controls) {
        super(width, height, x, y, controls);

        this.drawable = DrawableFactory.fromTextureAtlas(TextureAtlasManager.getTextureAtlas("lab2.atlas"), "jal-airplane");
        this.drawable.setMinWidth(getWidth());
        this.drawable.setMinHeight(getHeight());
    }

    public void setHorizontalAcceleration(float horizontalAcceleration) {
        this.horizontalAcceleration = horizontalAcceleration;
    }

    public void setVerticalAcceleration(float verticalAcceleration) {
        this.verticalAcceleration = verticalAcceleration;
    }

    public void setForwardSpeedLimit(float forwardSpeedLimit) {
        this.forwardSpeedLimit = forwardSpeedLimit;
    }

    public void setReverseSpeedLimit(float reverseSpeedLimit) {
        this.reverseSpeedLimit = reverseSpeedLimit;
    }

    public void setUpwardSpeedLimit(float upwardSpeedLimit) {
        this.upwardSpeedLimit = upwardSpeedLimit;
    }

    public void setDownwardSpeedLimit(float downwardSpeedLimit) {
        this.downwardSpeedLimit = downwardSpeedLimit;
    }

    private void horizontalAccelerate() {
        this.speedX += (-horizontalAcceleration * Gdx.graphics.getDeltaTime());

        if(this.speedX < -forwardSpeedLimit)
            speedX = -forwardSpeedLimit;
    }

    private void forcedHorizontalDecelerate() {
        this.speedX += (horizontalAcceleration * Gdx.graphics.getDeltaTime());

        if(this.speedX > reverseSpeedLimit)
            speedX = reverseSpeedLimit;
    }

    private void naturalHorizontalDecelerate() {
        // Forward movement natural deceleration
        if(speedX > 0) {
            float newSpeedX = this.speedX + -(0.5f * horizontalAcceleration) * Gdx.graphics.getDeltaTime();
            if(newSpeedX < 0)
                this.speedX = 0;
            else
                this.speedX = newSpeedX;
        }

        // Reverse movement natural deceleration
        if(speedX < 0) {
            float newSpeedX = this.speedX + (0.5f * horizontalAcceleration) * Gdx.graphics.getDeltaTime();
            if(newSpeedX > 0)
                this.speedX = 0;
            else
                this.speedX = newSpeedX;
        }
    }

    private void verticalAccelerate() {
        this.speedY += (verticalAcceleration * Gdx.graphics.getDeltaTime());

        if(this.speedY > upwardSpeedLimit)
            speedY = upwardSpeedLimit;
    }

    private void forcedVerticalDecelerate() {
        this.speedY += (-verticalAcceleration * Gdx.graphics.getDeltaTime());

        if(this.speedY < -downwardSpeedLimit)
            speedY = -downwardSpeedLimit;
    }

    private void naturalVerticalDecelerate() {
        // Downward movement natural deceleration
        if(speedY < 0) {
            float newSpeedY = this.speedY + (0.5f * horizontalAcceleration) * Gdx.graphics.getDeltaTime();
            if(newSpeedY > 0)
                this.speedY = 0;
            else
                this.speedY = newSpeedY;
        }
    }

    private void enforceBounds() {
        // Set speedY to 0 if plane will go under the ground
        if(y + speedY <= 0)
            speedY = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawable.draw(batch, x, y, width, height);
    }

    @Override
    public void moveUp() {
        verticalAccelerate();
    }

    @Override
    public void moveLeft() {
        horizontalAccelerate();
    }

    @Override
    public void moveDown() {
        forcedVerticalDecelerate();
        enforceBounds();
    }

    @Override
    public void moveRight() {
        forcedHorizontalDecelerate();
    }

    @Override
    public void idle() {
        naturalHorizontalDecelerate();
        naturalVerticalDecelerate();
        enforceBounds();
    }
}