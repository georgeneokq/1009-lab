package com.georgeneokq.lab1.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.georgeneokq.lab1.entity.Car;
import com.georgeneokq.lab1.entity.Entity;
import com.georgeneokq.lab1.entity.Controls;

import java.util.ArrayList;
import java.util.Arrays;

public class GameScreen implements Screen {
    private Stage stage;
    OrthographicCamera camera;

    ArrayList<Entity> entities = new ArrayList<>();

    public GameScreen() {
        camera = new OrthographicCamera();

        // Initialize stage
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        this.stage = new Stage(new StretchViewport(screenWidth, screenHeight, camera));

        buildStage();
    }

    private void buildStage() {
        // Create entities
        Car car = new Car(
                667,
                170,
                0,
                0,
                Controls.PredefinedControls.PLAYER_1
        );
        car.setAcceleration(3);
        car.setForwardSpeedLimit(60);
        car.setReverseSpeedLimit(2);

        entities.addAll(Arrays.asList(car));

        for(Entity controlledActor : entities) {
            // Scale down dimensions. TODO: Dynamic width/height scaling using getRegionWidth and getRegionHeight
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
        for(Entity controlledActor : entities) {
            controlledActor.update();
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