package com.georgeneokq.lab1.manager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

/*
 * Global textureAtlas manager.
 * The purpose is to share TextureAtlas objects throughout the application,
 * which eliminates the need for passing around TextureAtlas objects through parameters
 * to save memory.
 */
public class TextureAtlasManager {
    private static TextureAtlasManager textureAtlasManager;

    private TextureAtlasManager() {}

    HashMap<String, TextureAtlas> textureAtlasMap = new HashMap<>();

    public static TextureAtlasManager getInstance() {
        if(textureAtlasManager == null)
            textureAtlasManager = new TextureAtlasManager();
        return textureAtlasManager;
    }

    private TextureAtlas _getTextureAtlas(String internalFileName) {
        // Check if the TextureAtlas for the specified file name already exists.
        // If it does, return that instance, if not create a new instance and return it
        TextureAtlas textureAtlas = textureAtlasMap.get(internalFileName);
        if(textureAtlas != null)
            return textureAtlas;

        textureAtlasMap.put(internalFileName, new TextureAtlas(internalFileName));
        return textureAtlasMap.get(internalFileName);
    }

    /*
     * This method should only be called when the application is in a state where
     * the specified TextureAtlas object is definitely not in use.
     * Disposing the object when it is in use will cause problems.
     */
    private void _disposeTextureAtlas(String internalFileName) {
        TextureAtlas textureAtlas = textureAtlasMap.get(internalFileName);
        if(textureAtlas != null)
            textureAtlas.dispose();
    }

    public static TextureAtlas getTextureAtlas(String internalFileName) {
        return TextureAtlasManager.getInstance()._getTextureAtlas(internalFileName);
    }

    public static void disposeTextureAtlas(String internalFileName) {
        TextureAtlasManager.getInstance()._disposeTextureAtlas(internalFileName);
    }

}
