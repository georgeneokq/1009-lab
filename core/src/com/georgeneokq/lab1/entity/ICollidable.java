package com.georgeneokq.lab1.entity;

import com.badlogic.gdx.math.Rectangle;

public interface ICollidable {
    boolean collidesWith(ICollidable collidable);
    void handleCollision();
    Rectangle getBounds();
}