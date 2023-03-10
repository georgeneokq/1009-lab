package com.georgeneokq.lab1;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.georgeneokq.lab1.MyGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setResizable(true);
		config.setDecorated(false);
		config.setWindowedMode(1920, 1080);
		config.setForegroundFPS(60);
		config.setTitle("Lab 1");
		new Lwjgl3Application(new MyGame(), config);
	}
}
