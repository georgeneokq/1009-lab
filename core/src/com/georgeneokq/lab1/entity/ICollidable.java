package com.georgeneokq.lab1.entity;

import com.badlogic.gdx.math.Rectangle;

public interface ICollidable<T> {
    boolean collidesWith(T collidable);
    void handleCollision();
    Rectangle getBounds();
}