package com.georgeneokq.lab1.entity;

import com.badlogic.gdx.math.Rectangle;

public interface ICollidable<T> extends Cloneable {
    boolean collidesWith(T collidable);
    void handleCollision();
    Rectangle getBounds();
    Rectangle getForecastedBounds();
    T clone() throws CloneNotSupportedException;
}