package ru.geekbrains.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.geekbrains.StarGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		System.setProperty("user.name","Public");
		float aspect = 3f/4f;
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 400;
		config.height = (int) (config.width/aspect);
		config.resizable = false;
		new LwjglApplication(new StarGame(), config);
	}
}
