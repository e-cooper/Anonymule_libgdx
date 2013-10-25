package com.cs2340.anonymule;

import com.badlogic.gdx.*;
import com.cs2340.anonymule.Screen.MainMenuScreen;
import com.cs2340.anonymule.Screen.SplashScreen;

public class Anonymule extends Game implements ApplicationListener{
    private Map map;

    @Override
    public void create() {
//        resize(1080, 1920);
        map = new Map();
        setScreen(new SplashScreen(this));
    }

    public void dispose(){
        super.dispose();
    }

    public Map getMap() {
        return map;
    }
}
