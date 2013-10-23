package com.cs2340.anonymule;

import com.badlogic.gdx.*;
import com.cs2340.anonymule.Screen.MainMenuScreen;
import com.cs2340.anonymule.Screen.SplashScreen;

public class Anonymule extends Game implements ApplicationListener{

    @Override
    public void create() {
        setScreen(new SplashScreen(this));
    }

    public void dispose(){
        super.dispose();
    }
}
