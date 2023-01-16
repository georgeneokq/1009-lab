package com.georgeneokq.lab1.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.georgeneokq.lab1.manager.TextureAtlasManager;
import com.georgeneokq.lab1.entity.Car;
import com.georgeneokq.lab1.entity.ControlledActor;
import com.georgeneokq.lab1.entity.Controls;
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

    ArrayList<ControlledActor> entities = new ArrayList<>();

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
        // TODO: Use builder pattern for instantiation. Too many parameters
        Car car = new Car(
            DrawableFactory.fromTextureAtlas(textureAtlas, "car"),
                667,
                170,
                0,
                0,
                10,
                0,
                Controls.PredefinedControls.PLAYER_1
        );

        Hitori hitori = new Hitori(
                DrawableFactory.fromTextureAtlas(textureAtlas, "hitori"),
                400,
                400,
                0,
                0,
                5,
                8,
                Controls.PredefinedControls.PLAYER_2
        );

        Stickman stickman = new Stickman(
                DrawableFactory.fromTextureAtlas(textureAtlas, "stickman"),
                718,
                800,
                0,
                0,
                5,
                0,
                Controls.PredefinedControls.PLAYER_3
        );


        entities.addAll(Arrays.asList(car, hitori, stickman));

        for(ControlledActor controlledActor : entities) {
            // Scale down dimensions.
            // TODO: Dynamic width/height scaling using getRegionWidth and getRegionHeight
            controlledActor.setWidth(controlledActor.getWidth() / 4);
            controlledActor.setHeight(controlledActor.getHeight() / 4);

            // Attach entities to stage
            stage.addActor(controlledActor);
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
        for(ControlledActor controlledActor : entities) {
            controlledActor.handleKeyPress();
        }

        // Draw
        stage.draw();
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
        stage.getCamera().update();
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
