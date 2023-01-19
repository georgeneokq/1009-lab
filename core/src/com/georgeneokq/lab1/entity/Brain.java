package com.georgeneokq.lab1.entity;

import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Brain extends Actor {
    public abstract void moveUp();
    public abstract void moveLeft();
    public abstract void moveDown();
    public abstract void moveRight();
    public abstract void idle();
}
