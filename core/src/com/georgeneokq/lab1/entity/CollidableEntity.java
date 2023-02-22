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
        ICollidable thisClone = null;
        ICollidable otherClone = null;

        try {
            thisClone = this.clone();
            otherClone = (T) collidable.clone();
        } catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Rectangle collidableBounds = thisClone.getForecastedBounds();
        Rectangle otherCollidableBounds = otherClone.getForecastedBounds();
        return collidableBounds.overlaps(otherCollidableBounds);
    }

    @Override
    public Rectangle getForecastedBounds() {
        return new Rectangle(x + dx,
                y + dy, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public T clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
