package com.cs2340.anonymule.Screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.cs2340.anonymule.Anonymule;
//import com.badlogic.gdx.

public class MainMenuScreen implements Screen {

    private Anonymule anonymule;
    private String [] difficulty = {"Easy", "Hard"};
    private String [] mapType = {"1", "2", "3"};
    private SelectBox selectBox;
    private Button continueButton;
    private SpriteBatch batch;
    private Skin skin;
    private Texture background;

    public MainMenuScreen(Anonymule anonymule){
        this.anonymule = anonymule;
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("Anonymule/assets/textures/Concrete_splashbg.jpg").file().getAbsolutePath());
//        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
//        continueButton = new TextButton("Continue", skin);
//        selectBox = new SelectBox()

    }

    @Override
    public void render(float delta) {
//        System.out.println("hi");
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //To change body of implemented methods use File | Settings | File Templates.

        batch.begin();

        batch.draw(background, 0, 0);

        batch.end();
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
