package com.georgeneokq.lab1.factory;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class DrawableFactory {
    public static Drawable fromTextureAtlas(TextureAtlas textureAtlas, String spriteName) {
        return new TextureRegionDrawable(textureAtlas.findRegion(spriteName));
    }
}
