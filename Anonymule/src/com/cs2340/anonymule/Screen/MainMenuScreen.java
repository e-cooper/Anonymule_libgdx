package com.cs2340.anonymule.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.cs2340.anonymule.Anonymule;
import com.cs2340.anonymule.Map;

//import com.badlogic.gdx.

public class MainMenuScreen implements Screen {

    //reference to the game
    private Anonymule anonymule;


    private String[] difficulty;
    private Integer[] mapType;
    private SelectBox difficultySelectBox;
    private SelectBox mapSelectBox;
    private TextButton continueButton;

    //rendering
    private SpriteBatch batch;
    private Skin skin;
    private Texture background;
    private Stage stage;
    private OrthographicCamera camera;
    private Label mapTypeLabel;
    private Label difficultyLabel;


    public MainMenuScreen(Anonymule anonymule){
        difficulty = new String[]{"easy", "hard"};
        mapType = new Integer[]{1, 2, 3};
        this.anonymule = anonymule;
        batch = new SpriteBatch();
        stage = new Stage(480, 800, false);
//        skin = new Skin(Gdx.files.internal("Anonymule/assets/skins/uiskin.json"), new TextureAtlas(Gdx.files.internal("Anonymule/assets/skins/uiskin.atlas")));
        skin = new Skin(Gdx.files.internal("Anonymule/assets/skins/uiskin.json"), new TextureAtlas(Gdx.files.internal("Anonymule/assets/skins/uiskin.atlas")));

        continueButton = new TextButton("Continue", skin);
        background = new Texture(Gdx.files.internal("Anonymule/assets/textures/Concrete_splashbg.jpg"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        continueButton.setPosition(480 / 2 - 70, 800 / 2);
        mapTypeLabel = new Label("Map Type", skin);
        mapTypeLabel.setPosition(120, 605);
        difficultyLabel = new Label("Difficulty", skin);
        difficultyLabel.setPosition(120, 550);

//        System.out.print(camera.position);
        difficultySelectBox = new SelectBox(difficulty, skin);
        difficultySelectBox.setPosition(200, 545);

        mapSelectBox = new SelectBox(mapType, skin);
        mapSelectBox.setPosition(200, 600);
//        difficultySelectBox.setSelection(0);
//        difficultySelectBox.addListener(new InputListener(){
//
//        });
//        stage.addActor(difficultySelectBox);

        Gdx.input.setInputProcessor(stage);

//        continueButton.setPosition(50, 50);
//        continueButton.setVisible(true);
//        continueButton.addListener(new InputListener());

        continueButton.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                System.out.println("down");
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                updateScreen();
            }

        });
        stage.addActor(continueButton);
        stage.addActor(difficultySelectBox);
        stage.addActor(mapSelectBox);
        stage.addActor(mapTypeLabel);
        stage.addActor(difficultyLabel);

    }

    public void updateScreen(){
        Map map = anonymule.getMap();
        String difficulty = difficultySelectBox.getSelection();
        String mapType = mapSelectBox.getSelection();

        if(difficulty.equals("easy"))
            map.setDifficulty(1);
        else
            map.setDifficulty(2);

        if(mapType.equals("1"))
            map.setMap_type(1);
        else
            map.setMap_type(2);

//        System.out.println(map.getMap_type());
//        anonymule.getMap().setDifficulty(difficultySelectBox.getSelection());
//        System.out.println(difficultySelectBox.getSelection())
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
