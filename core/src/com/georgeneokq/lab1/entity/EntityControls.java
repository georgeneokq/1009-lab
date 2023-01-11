package com.georgeneokq.lab1.entity;

import com.badlogic.gdx.Input;

public class EntityControls {
    private int upKey;
    private int downKey;
    private int leftKey;
    private int rightKey;

    public EntityControls(int upKey, int downKey, int leftKey, int rightKey) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }

    public final static class PredefinedControls {
        public final static EntityControls PLAYER_1 = new EntityControls(
                Input.Keys.W,
                Input.Keys.S,
                Input.Keys.A,
                Input.Keys.D
        );
        public final static EntityControls PLAYER_2 = new EntityControls(
                Input.Keys.I,
                Input.Keys.K,
                Input.Keys.J,
                Input.Keys.L
        );
        public final static EntityControls PLAYER_3 = new EntityControls(
                Input.Keys.UP,
                Input.Keys.DOWN,
                Input.Keys.LEFT,
                Input.Keys.RIGHT
        );
        public final static EntityControls PLAYER_4 = new EntityControls(
                Input.Keys.NUMPAD_8,
                Input.Keys.NUMPAD_5,
                Input.Keys.NUMPAD_4,
                Input.Keys.NUMPAD_6
        );
    }

    public int getUpKey() {
        return upKey;
    }

    public void setUpKey(int upKey) {
        this.upKey = upKey;
    }

    public int getDownKey() {
        return downKey;
    }

    public void setDownKey(int downKey) {
        this.downKey = downKey;
    }

    public int getLeftKey() {
        return leftKey;
    }

    public void setLeftKey(int leftKey) {
        this.leftKey = leftKey;
    }

    public int getRightKey() {
        return rightKey;
    }

    public void setRightKey(int rightKey) {
        this.rightKey = rightKey;
    }
}
