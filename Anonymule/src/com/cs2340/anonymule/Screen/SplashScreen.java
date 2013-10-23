package com.cs2340.anonymule.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.cs2340.anonymule.Anonymule;

public class SplashScreen implements Screen {

    private Anonymule anonymule;
    private Timer timer;
    private BitmapFont gameName;
    private SpriteBatch batch;
    private Stage stage;

    public SplashScreen(Anonymule anonymule){
        this.anonymule = anonymule;
        gameName = new BitmapFont();
        batch = new SpriteBatch();
        stage = new Stage(480, 800, false);
        timer = new Timer();
//        timer.start();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                updateScreen();
                //To change body of implemented methods use File | Settings | File Templates.
            }
        }, 1);
//        stage.addActor();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0f, 0f, 0.2f, 1f );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT );

        batch.begin();
        gameName.draw(batch, "AnonyMule", 0, 50);
        batch.end();

//        if(Gdx.input.isTouched())
//            updateScreen();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void updateScreen(){
        anonymule.setScreen(new MainMenuScreen(anonymule));
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
