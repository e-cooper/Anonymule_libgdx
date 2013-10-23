package com.cs2340.anonymule.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Timer;
import com.cs2340.anonymule.Anonymule;

public class SplashScreen implements Screen {

    private Anonymule anonymule;
    private Timer timer;

    public SplashScreen(Anonymule anonymule){
        this.anonymule = anonymule;
        timer = new Timer();
//        timer.start();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                updateScreen();
                //To change body of implemented methods use File | Settings | File Templates.
            }
        }, 2);
//        anonymule.setScreen(new MainMenuScreen(anonymule));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0f, 0f, 0.2f, 1f );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT );

//        if(Gdx.input.isTouched())
//            updateScreen();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void updateScreen(){
        anonymule.setScreen(new MainMenuScreen(anonymule));
    }

    @Override
    public void resize(int width, int height) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void show() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void hide() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dispose() {

        //To change body of implemented methods use File | Settings | File Templates.
    }
}
