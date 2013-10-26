package com.cs2340.anonymule.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.cs2340.anonymule.Anonymule;
import com.cs2340.anonymule.Map;
import com.cs2340.anonymule.Player;
import com.cs2340.anonymule.Tile.Tile;

public class GameScreen implements Screen {

    private Anonymule anonymule;
    private SpriteBatch batch;
    private Texture background;
    private Map map;
    private Texture whiteTexture;
    private Tile[][] tiles;
    private Skin skin;
    private Stage stage;
    private Label playerName, color, race, money;
    private Label nameValue, colorValue, raceValue, moneyValue;

    /**
     * Initializes the main game screen
     * @param anonymule The main game
     */

    public GameScreen(Anonymule anonymule){
        this.anonymule = anonymule;
        map = anonymule.getMap();
        batch = new SpriteBatch();
        tiles = map.getTiles();
        whiteTexture = new Texture(Gdx.files.internal("Anonymule/assets/textures/whiteTexture.jpg"));
        background = new Texture(Gdx.files.internal("Anonymule/assets/textures/Concrete_mapbg.jpg"));
        skin = new Skin(Gdx.files.internal("Anonymule/assets/skins/uiskin.json"));
        stage = new Stage();
        Player current_player = map.getCurrentPlayer();

        playerName = new Label("Player name: ", skin, "black");
        playerName.setPosition(100, 280);
        color = new Label("Color: ", skin, "black");
        color.setPosition(600, 280);
        race = new Label("Race: ", skin, "black");
        race.setPosition(100, 220);
        money = new Label("Money: ", skin, "black");
        money.setPosition(600, 220);

//        System.out.println(current_player.getColor().toString());

        nameValue = new Label(current_player.getName(), skin);
        nameValue.setColor(current_player.getColor());
        nameValue.setPosition(220, 280);
        colorValue = new Label(current_player.getColorName(), skin);
        colorValue.setColor(current_player.getColor());
        colorValue.setPosition(720, 280);
        raceValue = new Label("" + current_player.getRace(), skin);
        raceValue.setColor(current_player.getColor());
        raceValue.setPosition(220, 220);
        moneyValue = new Label("" + current_player.getMoney(), skin);
        moneyValue.setColor(current_player.getColor());
        moneyValue.setPosition(720, 220);

        stage.addActor(playerName);
        stage.addActor(color);
        stage.addActor(race);
        stage.addActor(money);
        stage.addActor(nameValue);
        stage.addActor(colorValue);
        stage.addActor(raceValue);
        stage.addActor(moneyValue);
    }

    /**
     * Renders the background and game objects on the screen
     * @param delta Time in seconds since the last render
     */

    @Override
    public void render(float delta) {
//        System.out.print("hi");
        Gdx.gl.glClearColor(205f/255f, 201f/255f, 201f/255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        batch.begin();
//        batch.draw(background, 0, 0);
//        batch.end();

        batch.begin();

        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                batch.setColor(tiles[i][j].getColor());
                batch.draw(whiteTexture, 90f * j + 100, 90f * i + 320, 90f, 90f);
            }
        }

        batch.end();

        batch.begin();
        stage.draw();
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
