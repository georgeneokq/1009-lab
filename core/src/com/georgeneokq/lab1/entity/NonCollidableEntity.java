package com.georgeneokq.lab1.entity;

public abstract class NonCollidableEntity<T> extends Entity<T> {

    public NonCollidableEntity(float width, float height, float x, float y, float speed,
                               Controls controls) {
        super(width, height, x, y, speed, controls);
    }
}
