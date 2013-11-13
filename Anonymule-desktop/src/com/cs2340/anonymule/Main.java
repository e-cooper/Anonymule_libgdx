package com.cs2340.anonymule;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
<<<<<<< HEAD
<<<<<<< HEAD
import org.lwjgl.input.Keyboard;
=======
>>>>>>> 59eaac163cf654d68b8f9b6308a47f1d67451d90
=======
import org.lwjgl.input.Keyboard;
>>>>>>> eli

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Anonymule";
<<<<<<< HEAD
<<<<<<< HEAD
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 600;
		new LwjglApplication(new Anonymule(), cfg);
        Keyboard.enableRepeatEvents(true);
=======
		cfg.useGL20 = false;
		cfg.width = 1024;
		cfg.height = 768;
		
		new LwjglApplication(new Anonymule(), cfg);
>>>>>>> 59eaac163cf654d68b8f9b6308a47f1d67451d90
=======
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 600;
		new LwjglApplication(new Anonymule(), cfg);
        Keyboard.enableRepeatEvents(true);
>>>>>>> eli
	}
}
