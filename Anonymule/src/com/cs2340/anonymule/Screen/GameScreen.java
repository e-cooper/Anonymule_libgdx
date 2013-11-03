package com.cs2340.anonymule.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cs2340.anonymule.Anonymule;
import com.cs2340.anonymule.Map;

public class GameScreen implements Screen {

    private Anonymule anonymule;
    private SpriteBatch batch;
    private Texture background;
    private Map map;

    /**
     * Initializes the main game screen
     * @param anonymule The main game
     */

    public GameScreen(Anonymule anonymule){
        this.anonymule = anonymule;
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("Anonymule/assets/textures/Concrete_mapbg.jpg"));
    }

    /**
     * Renders the background and game objects on the screen
     * @param delta Time in seconds since the last render
     */

    @Override
    public void render(float delta) {
//        System.out.print("hi");
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
    }

    /**
     * Resizes the game everytime the game is not in paused state
     * @param width The new width of the game
     * @param height The new height of the game
     */

    @Override
    public void resize(int width, int height) {
    }

    /**
     * Called when the app makes this screen active
     */

    @Override
    public void show() {
    }

    /**
     * Called when the app makes another screen active
     */

    @Override
    public void hide() {
    }

    /**
     * Called before dispose. Also called on android when user presses the home button
     */

    @Override
    public void pause() {
    }

    /**
     * Only called on android when an app resumes from a paused state
     */

    @Override
    public void resume() {
    }

    /**
     * Cleans up any loaded assets
     */

    @Override
    public void dispose() {
    }
}
