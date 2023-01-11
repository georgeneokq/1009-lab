package com.georgeneokq.lab1.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.georgeneokq.lab1.atlas.TextureAtlasManager;
import com.georgeneokq.lab1.entity.Car;
import com.georgeneokq.lab1.entity.Entity;
import com.georgeneokq.lab1.entity.EntityControls;
import com.georgeneokq.lab1.entity.Hitori;
import com.georgeneokq.lab1.entity.Stickman;
import com.georgeneokq.lab1.factory.DrawableFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class GameScreen implements Screen {
    private TextureAtlas textureAtlas;
    private SpriteBatch spriteBatch;
    private Stage stage;
    OrthographicCamera camera;

    ArrayList<Entity> entities = new ArrayList<>();

    public GameScreen() {
        camera = new OrthographicCamera();
        textureAtlas = TextureAtlasManager.getTextureAtlas("lab1.atlas");

        // Initialize stage
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        this.stage = new Stage(new StretchViewport(screenWidth, screenHeight, camera));
        this.spriteBatch = new SpriteBatch();

        buildStage();
    }

    private void buildStage() {
        // Create entities
        Car car = new Car(
            DrawableFactory.fromTextureAtlas(textureAtlas, "car"),
                667,
                170,
                0,
                0,
                5,
                0,
                EntityControls.PredefinedControls.PLAYER_1
        );

        Hitori hitori = new Hitori(
                DrawableFactory.fromTextureAtlas(textureAtlas, "hitori"),
                400,
                400,
                0,
                0,
                5,
                5,
                EntityControls.PredefinedControls.PLAYER_2
        );

        Stickman stickman = new Stickman(
                DrawableFactory.fromTextureAtlas(textureAtlas, "stickman"),
                718,
                800,
                0,
                0,
                5,
                0,
                EntityControls.PredefinedControls.PLAYER_3
        );


        entities.addAll(Arrays.asList(car, hitori, stickman));

        for(Entity entity: entities) {
            // Scale down dimensions. TODO: Dynamic width/height scaling using getRegionWidth and getRegionHeight
            entity.setWidth(entity.getWidth() / 4);
            entity.setHeight(entity.getHeight() / 4);

            // Attach entities to stage
            stage.addActor(entity);
        }

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        // Black background
        ScreenUtils.clear(0.57f, 0.77f, 0.85f, 1);

        // Update
        for(Entity entity: entities) {
            entity.handleKeyPress();
        }

        // Draw
        stage.draw();
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
