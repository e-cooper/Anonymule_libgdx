package com.cs2340.anonymule;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.lwjgl.input.Keyboard;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Anonymule";
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 600;
		new LwjglApplication(new Anonymule(), cfg);
        Keyboard.enableRepeatEvents(true);
	}
}
