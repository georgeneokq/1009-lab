package com.georgeneokq.lab1.entity;

import com.badlogic.gdx.Gdx;

public class Brain implements Cloneable {
    private Entity entity;

    // Takes in Entity as a parameter, but basically requires an object
    // with setDx and setDy methods
    public Brain(Entity entity) {
        this.entity = entity;
    }

    public void moveUp() {
        entity.setDy(entity.getDy() + entity.getSpeed());
    }
    public void moveLeft() {
        entity.setDx(entity.getDx() - entity.getSpeed());
    }
    public void moveDown() {
        entity.setDy(entity.getDy() - entity.getSpeed());
    }
    public void moveRight() {
        entity.setDx(entity.getDx() + entity.getSpeed());
    }
    public void idle() {
        entity.changeDxTowardsZero(entity.getSpeed() * Gdx.graphics.getDeltaTime());
        entity.changeDyTowardsZero(entity.getSpeed() * Gdx.graphics.getDeltaTime());
    }

    @Override
    public Brain clone() throws CloneNotSupportedException {
        return (Brain) super.clone();
    }
}
