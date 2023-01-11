package com.georgeneokq.lab1.entity;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.georgeneokq.lab1.manager.TextureAtlasManager;
import com.georgeneokq.lab1.factory.DrawableFactory;

public class Car extends ControlledActor {

    public Car(Drawable drawable, float width, float height, float x, float y, float dx, float dy) {
        super(drawable, width, height, x, y, dx, dy, null);
    }

    public Car(Drawable drawable, float width, float height, float x, float y, float dx, float dy, Controls controls) {
        super(drawable, width, height, x, y, dx, dy, controls);
    }

    @Override
    public void moveLeft() {
        super.moveLeft();
        this.setDrawable(DrawableFactory.fromTextureAtlas(TextureAtlasManager.getTextureAtlas("lab1.atlas"), "car-reverse"));
    }

    @Override
    public void moveRight() {
        super.moveRight();
        this.setDrawable(DrawableFactory.fromTextureAtlas(TextureAtlasManager.getTextureAtlas("lab1.atlas"), "car"));
    }

}
