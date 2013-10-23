package com.cs2340.anonymule.Screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.cs2340.anonymule.Anonymule;

//import com.badlogic.gdx.

public class MainMenuScreen implements Screen {

    //reference to the game
    private Anonymule anonymule;


    private String [] difficulty = {"Easy", "Hard"};
    private String [] mapType = {"1", "2", "3"};
    private SelectBox selectBox;
    private TextButton continueButton;

    //rendering
    private SpriteBatch batch;
    private Skin skin;
    private Texture background;
    private Stage stage;
    private OrthographicCamera camera;

    public MainMenuScreen(Anonymule anonymule){
        this.anonymule = anonymule;
        batch = new SpriteBatch();
        stage = new Stage(480, 800, false);
        skin = new Skin(Gdx.files.internal("Anonymule/assets/skins/uiskin.json"));
        continueButton = new TextButton("Continue", skin);
        background = new Texture(Gdx.files.internal("Anonymule/assets/textures/Concrete_splashbg.jpg"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);

        Gdx.input.setInputProcessor(stage);

//        continueButton.setPosition(50, 50);
//        continueButton.setVisible(true);
//        continueButton.addListener(new InputListener());

        continueButton.addListener(new InputListener() {

            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("down");
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                updateScreen();
            }

        });
        stage.addActor(continueButton);

    }

    public void updateScreen(){
        anonymule.setScreen(new GameScreen(anonymule));
    }

    @Override
    public void render(float delta) {
//        System.out.println("hi");
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //To change body of implemented methods use File | Settings | File Templates.

        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(background, 0, 0);

        batch.end();

        batch.begin();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
