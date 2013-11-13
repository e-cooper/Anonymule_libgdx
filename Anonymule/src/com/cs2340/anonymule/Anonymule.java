package com.cs2340.anonymule;

import com.badlogic.gdx.*;
import com.cs2340.anonymule.Screen.MainMenuScreen;
import com.cs2340.anonymule.Screen.SplashScreen;

public class Anonymule extends Game implements ApplicationListener{

    private Map map;

    /**
     * Creates the main game class that initializes the map and shows a splash screen
     */

    @Override
    public void create() {
        map = new Map();
        setScreen(new SplashScreen(this));
    }

    /**
     * Cleans up any loaded assets
     */

    public void dispose(){
        super.dispose();
    }

    /**
     * Returns the map of the game
     * @return the map
     */

    public Map getMap() {
        return map;
    }
    
    /**
     * Sets the map for the game
     * @param map The map of the game
     */
    
    public void setMap(Map map) {
    	this.map = map;
    }
}
