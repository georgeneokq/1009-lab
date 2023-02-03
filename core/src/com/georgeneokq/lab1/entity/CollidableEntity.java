package com.georgeneokq.lab1.entity;

import com.badlogic.gdx.math.Rectangle;

public abstract class CollidableEntity extends Entity implements ICollidable {

    public CollidableEntity(float width, float height, float x, float y, float speed, Controls controls) {
        super(width, height, x, y, speed, controls);
    }

    @Override
    public boolean collidesWith(ICollidable collidable) {
        Rectangle collidableBounds = this.getBounds();
        Rectangle otherCollidableBounds = collidable.getBounds();
        return collidableBounds.overlaps(otherCollidableBounds);
    }


    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + dx,
                y + dy, width, height);
    }
}
