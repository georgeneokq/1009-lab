package com.georgeneokq.lab1.stage;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.georgeneokq.lab1.entity.CollidableEntity;
import com.georgeneokq.lab1.entity.NonCollidableEntity;

/*
 * Use ExtendedStage instead of the libGDX in-built Stage to handle collisions
 */
public class ExtendedStage extends InputAdapter {
    private Stage stage;
    Array<CollidableEntity> collidableEntities = new Array<>();
    Array<NonCollidableEntity> nonCollidableEntities = new Array<>();

    public ExtendedStage(Viewport viewport) {
        this.stage = new Stage(viewport);
    }

    public void addCollidableEntity(CollidableEntity collidableEntity) {
        stage.addActor(collidableEntity);
        collidableEntities.add(collidableEntity);
    }

    public void addNonCollidableEntity(NonCollidableEntity nonCollidableEntity) {
        stage.addActor(nonCollidableEntity);
        nonCollidableEntities.add(nonCollidableEntity);
    }

    private void reportCollisions() {
        for(int i = 0; i < collidableEntities.size; i++) {
            CollidableEntity collidableEntity = collidableEntities.get(i);
            for(CollidableEntity otherCollidableEntity: collidableEntities) {
                if(otherCollidableEntity == collidableEntity)
                    continue;

                // Check collision. Currently assumes that every actor's hit box is a rectangle
                boolean colliding = collidableEntity.collidesWith(otherCollidableEntity);
                if(colliding)
                    collidableEntity.handleCollision();
            }
        }
    }

    public void updateAndDraw(float delta) {
        // Handle key press
        for(CollidableEntity collidableEntity : collidableEntities) {
            collidableEntity.handleKeyPress();
        }
        for(NonCollidableEntity nonCollidableEntity : nonCollidableEntities) {
            nonCollidableEntity.handleKeyPress();
        }

        // Handle collisions next. Collisions may overwrite the speed of an actor
        reportCollisions();

        // Draw updated actors
        act(delta);
        draw();
    }

    /* Wrapper function draw() */
    private void draw() {
        stage.draw();
    }

    /* Wrapper function act() */
    private void act(float delta) {
        stage.act(delta);
    }

    /* Wrapper function getWidth() */
    public float getWidth() {
        return stage.getWidth();
    }

    /* Wrapper function getHeight() */
    public float getHeight() {
        return stage.getHeight();
    }

    /* Wrapper function getViewport() */
    public Viewport getViewport() {
        return stage.getViewport();
    }

    /* Wrapper function getCamera */
    public Camera getCamera() {
        return stage.getCamera();
    }
}
