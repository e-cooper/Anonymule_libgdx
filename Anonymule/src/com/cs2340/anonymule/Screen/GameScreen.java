package com.cs2340.anonymule.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cs2340.anonymule.Anonymule;

public class GameScreen implements Screen {

    private Anonymule anonymule;
    private SpriteBatch spriteBatch;

    public GameScreen(Anonymule anonymule){
        this.anonymule = anonymule;
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255,255,255,1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        //To change body of implemented methods use File | Settings | File Templates.
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
