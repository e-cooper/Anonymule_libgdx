package com.cs2340.anonymule.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;
import com.cs2340.anonymule.Anonymule;

public class SplashScreen implements Screen {

    private Anonymule anonymule;
    private Timer timer;
    private Label gameName;
    private SpriteBatch batch;
    private Stage stage;
    private Skin skin;

    /**
     * Initializes the splash screen for the game
     * @param anonymule The main game
     */

    public SplashScreen(Anonymule anonymule){
        this.anonymule = anonymule;
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        gameName = new Label("Anonymule", skin);        //Name of the game
        gameName.setPosition(160, 400);
        gameName.setFontScale(2);
        batch = new SpriteBatch();          //required to draw on the screen
        stage = new Stage(480, 800, false); //Stage holds all the "actors" that are bound together
        timer = new Timer();

        //Switches to the main menu screen after showing the splash screen for one second
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                updateScreen();
            }
        }, 1);
        stage.addActor(gameName);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0f, 0f, 0.2f, 1f );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT );


        batch.begin();  //start drawing
        stage.draw();   //show the name of the game
        batch.end();    //end drawing

    }

    /**
     * Updates the screen to the main menu screen
     */

    public void updateScreen(){
        anonymule.setScreen(new MainMenuScreen(anonymule));
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
