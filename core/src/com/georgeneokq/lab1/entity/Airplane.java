package com.georgeneokq.lab1.entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.georgeneokq.lab1.factory.DrawableFactory;
import com.georgeneokq.lab1.manager.TextureAtlasManager;

// Movement functions currently assume that the plane is facing left...
public class Airplane extends CollidableEntity<Airplane> {

    private float verticalAcceleration = 2;
    private float horizontalAcceleration = 2;
    private float forwardSpeedLimit = 200;
    private float reverseSpeedLimit = 2;
    private float upwardSpeedLimit = 10;
    private float downwardSpeedLimit = 2;

    // An airplane will be drawn using a car image
    private Drawable drawable;

    private Brain brain = new Brain(this) {
        @Override
        public void moveUp() {
            verticalAccelerate();
        }

        @Override
        public void moveRight() {
            forcedHorizontalDecelerate();
        }

        @Override
        public void moveDown() {
            forcedVerticalDecelerate();
            enforceBounds();
        }

        @Override
        public void moveLeft() {
            horizontalAccelerate();
        }

        @Override
        public void idle() {
            naturalHorizontalDecelerate();
            naturalVerticalDecelerate();
            enforceBounds();
        }
    };

    public Airplane(float width, float height, float x, float y, Controls controls) {
        super(width, height, x, y, 0, controls);
        this.setBrain(brain);

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
        this.dx += (-horizontalAcceleration * Gdx.graphics.getDeltaTime());

        if(this.dx < -forwardSpeedLimit)
            dx = -forwardSpeedLimit;
    }

    private void forcedHorizontalDecelerate() {
        this.dx += (horizontalAcceleration * Gdx.graphics.getDeltaTime());

        if(this.dx > reverseSpeedLimit)
            dx = reverseSpeedLimit;
    }

    private void naturalHorizontalDecelerate() {
        // Forward movement natural deceleration
        if(dx > 0) {
            float newDx = this.dx + -(0.5f * horizontalAcceleration) * Gdx.graphics.getDeltaTime();
            if(newDx < 0)
                this.dx = 0;
            else
                this.dx = newDx;
        }

        // Reverse movement natural deceleration
        if(dx < 0) {
            float newDx = this.dx + (0.5f * horizontalAcceleration) * Gdx.graphics.getDeltaTime();
            if(newDx > 0)
                this.dx = 0;
            else
                this.dx = newDx;
        }
    }

    private void verticalAccelerate() {
        this.dy += (verticalAcceleration * Gdx.graphics.getDeltaTime());

        if(this.dy > upwardSpeedLimit)
            dy = upwardSpeedLimit;
    }

    private void forcedVerticalDecelerate() {
        this.dy += (-verticalAcceleration * Gdx.graphics.getDeltaTime());

        if(this.dy < -downwardSpeedLimit)
            dy = -downwardSpeedLimit;
    }

    private void naturalVerticalDecelerate() {
        // Downward movement natural deceleration
        if(dy < 0) {
            float newDy = this.dy + (0.5f * horizontalAcceleration) * Gdx.graphics.getDeltaTime();
            if(newDy > 0)
                this.dy = 0;
            else
                this.dy = newDy;
        }
    }

    private void enforceBounds() {
        // Set dy to 0 if plane will continue to go underground
        if(y + dy <= 0)
            dy = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawable.draw(batch, x, y, width, height);
    }

    @Override
    public void handleCollision() {
        dx = dx > 0 ? -2 : 2;
        if(y > 0)
            dy = dy > 0 ? -2 : 2;
    }
}