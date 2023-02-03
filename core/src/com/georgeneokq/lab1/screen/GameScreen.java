package com.georgeneokq.lab1.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.georgeneokq.lab1.entity.Airplane;
import com.georgeneokq.lab1.entity.Ball;
import com.georgeneokq.lab1.entity.Car;
import com.georgeneokq.lab1.entity.Controls;
import com.georgeneokq.lab1.stage.ExtendedStage;

public class GameScreen implements Screen {
    private ExtendedStage stage;
    private OrthographicCamera camera;

    private Airplane airplane;
    private Ball ball;
    private Car car;

    private long CLONE_SPAWN_INTERVAL = 4000;
    private long timeSinceLastClone;

    public GameScreen() {
        camera = new OrthographicCamera();

        // Initialize stage
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        this.stage = new ExtendedStage(new StretchViewport(screenWidth, screenHeight, camera));

        buildStage();
        timeSinceLastClone = System.currentTimeMillis();
    }

    private void buildStage() {
        // Create collidable entities
        car = new Car(
                667 / 4,
                170 / 4,
                0,
                0,
                Controls.PredefinedControls.PLAYER_1
        );
        car.setAcceleration(3);
        car.setForwardSpeedLimit(60);
        car.setReverseSpeedLimit(2);

        float airplaneWidth = 920 / 4;
        airplane = new Airplane(
                airplaneWidth,
                517 / 4,
                stage.getWidth() - airplaneWidth / 2,
                0,
                Controls.PredefinedControls.PLAYER_3
        );
        airplane.setVerticalAcceleration(1);

        // Create non-collidable entities
        float ballRadius = 30;
        ball = new Ball(
                ballRadius,
                2,
                stage.getWidth() / 2 - ballRadius,
                0 + ballRadius,
                Controls.PredefinedControls.PLAYER_2
        );

        stage.addCollidableEntity(car);
        stage.addCollidableEntity(airplane);
        stage.addNonCollidableEntity(ball);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // Blue background
        ScreenUtils.clear(0.57f, 0.77f, 0.85f, 1);

        // Create a clone of a car every few seconds
        if(car != null && System.currentTimeMillis() - timeSinceLastClone > CLONE_SPAWN_INTERVAL) {
            try {
                timeSinceLastClone = System.currentTimeMillis();
                Car carClone = car.clone();

                // Detach controls so that the cloned objects don't move together.
                // The cloned object will eventually stop and become vegetable
                carClone.setControls(null);
                stage.addCollidableEntity(carClone);

            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        stage.updateAndDraw(delta);
    }

    @Override
    public void show() {}
    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {}
}