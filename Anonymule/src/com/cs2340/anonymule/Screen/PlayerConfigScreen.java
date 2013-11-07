package com.cs2340.anonymule.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.cs2340.anonymule.Anonymule;
import com.cs2340.anonymule.Map;
import com.cs2340.anonymule.Player;

public class PlayerConfigScreen implements Screen {

    private Anonymule anonymule;
    private Label playerName;
    private Label playerRace;
    private Label playerColor;
    private Skin skin;
    private Stage stage;
    private SpriteBatch batch;
    private Texture background;
    public static int current_index = 0;
    private String[] playerColors;
    private Integer[] playerRaces;
    private TextButton continueButton;
    private SelectBox raceSelectBox;
    private SelectBox colorSelectBox;
    private TextField playerNameField;
    private Label player_number;
    private static int current_player = 0;

    /**
     * Initializes the Player configuration screen for the game
     * @param anonymule The main game
     */

    public PlayerConfigScreen(Anonymule anonymule){
        this.anonymule = anonymule;
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        stage = new Stage(480, 800, false);
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("textures/Concrete_playerbg.jpg"));

        playerColors = new String[]{"red", "green", "blue", "orange"};
        playerRaces = new Integer[]{1, 2, 3, 4};

        continueButton = new TextButton("Continue", skin);
        continueButton.setPosition(480 / 2 - 70, 800 / 2);

        playerName = new Label("Name", skin);
        playerName.setPosition(120, 660);
        playerRace = new Label("Race", skin);
        playerRace.setPosition(120, 605);
        playerColor = new Label("Color", skin);
        playerColor.setPosition(120, 550);
        player_number = new Label("Player " + (current_player + 1), skin);
        player_number.setPosition(220, 760);

        raceSelectBox = new SelectBox(playerRaces, skin);
        raceSelectBox.setPosition(200, 545);
        colorSelectBox = new SelectBox(playerColors, skin);
        colorSelectBox.setPosition(200, 600);

        playerNameField = new TextField("", skin);
        playerNameField.setPosition(200, 655);
        playerNameField.setMaxLength(10);

        // Stage handles the input
        Gdx.input.setInputProcessor(stage);

        // Listen for clicks on the continue button
        continueButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                updateScreen();
            }
        });

        // Add all the buttons and labels to the stage
        stage.addActor(playerName);
        stage.addActor(playerRace);
        stage.addActor(playerColor);
        stage.addActor(continueButton);
        stage.addActor(raceSelectBox);
        stage.addActor(colorSelectBox);
        stage.addActor(player_number);
        stage.addActor(playerNameField);

    }

    /**
     * Updates the screen to the player configuration screen for the next player(if there is one) or the main game screen
     */

    public void updateScreen(){
        Map map = anonymule.getMap();
        Player temp = map.getPlayerList().get(current_player);
        String name = playerNameField.getText();
        current_player++;

        // If no name is provided, use default name
        if(name.isEmpty()){
            name = "Player " + current_player;
            System.out.println(name);
        }
        temp.setAttributes(colorSelectBox.getSelection(),
                Integer.parseInt(raceSelectBox.getSelection()),
                playerNameField.getText());

        if(current_player < map.getPlayerList().size()){
            anonymule.setScreen(new PlayerConfigScreen(anonymule));
        }
        else
            anonymule.setScreen(new GameScreen(anonymule));
    }

    /**
     * Renders the background and game objects on the screen
     * @param delta Time in seconds since the last render
     */

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        batch.begin();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.end();
        //To change body of implemented methods use File | Settings | File Templates.
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
