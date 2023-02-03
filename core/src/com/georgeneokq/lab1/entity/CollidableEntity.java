package com.georgeneokq.lab1.entity;

import com.badlogic.gdx.math.Rectangle;

public abstract class CollidableEntity<T extends ICollidable>
        extends Entity<T> implements ICollidable<T> {

    public CollidableEntity(float width, float height, float x, float y, float speed,
                            Controls controls) {
        super(width, height, x, y, speed, controls);
    }

    @Override
    public boolean collidesWith(T collidable) {
        // Objects of the same type (clones) do not collide
        if(this.getClass() == collidable.getClass())
            return false;
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
