package com.georgeneokq.lab1.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball extends NonCollidableEntity {

    private final static float GRAVITATIONAL_FORCE = 10;

    private ShapeRenderer shape;
    private float radius;
    private float horizontalSpeed;

    public Ball(float radius, float horizontalSpeed, float x, float y, Controls controls) {
        super(radius * 2, radius * 2, x, y, controls);

        this.radius = radius;
        this.horizontalSpeed = horizontalSpeed;
        shape = new ShapeRenderer();
    }

    private void kickUpwards() {
        if(y - height > 0)
            return;
        dy += 50 * Gdx.graphics.getDeltaTime();
    }

    private void gravity() {
        dy -= (GRAVITATIONAL_FORCE  * Gdx.graphics.getDeltaTime());
    }

    private void enforceBounds() {
        if(y - radius + dy <= 0) {
            dy = 0;
        }
    }

    @Override
    public void moveUp() {
        kickUpwards();
    }

    @Override
    public void moveLeft() {
        dx = -horizontalSpeed;
    }

    @Override
    public void moveDown() {
    }

    @Override
    public void moveRight() {
        dx = horizontalSpeed;
    }

    @Override
    public void idle() {
        dx = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        gravity();
        enforceBounds();

        batch.end();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.BLUE);
        shape.circle(x, y, radius);
        shape.end();
        batch.begin();
    }
}
