package com.cs2340.anonymule.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.cs2340.anonymule.Anonymule;
import com.cs2340.anonymule.Input.GameInputProcessor;
import com.cs2340.anonymule.Map;
import com.cs2340.anonymule.Player;
import com.cs2340.anonymule.Tile.Tile;

public class GameScreen implements Screen {

    private Anonymule anonymule;
    private SpriteBatch batch;
    private Texture background;
    private Map map;
    private Texture whiteTexture;
    private Texture currentGameScreen;
    private Skin skin;
    private Stage stage;
    private Label playerName, color, race, money;
    private Label nameValue, colorValue, raceValue, moneyValue;
    ShapeRenderer shapeRenderer;
    Player current_player;



    /**
     * Initializes the main game screen
     * @param anonymule The main game
     */

    public GameScreen(Anonymule anonymule){
        this.anonymule = anonymule;
        map = anonymule.getMap();
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        currentGameScreen = map.getMap();
        whiteTexture = new Texture(Gdx.files.internal("textures/whiteTexture.jpg"));
        background = new Texture(Gdx.files.internal("textures/Concrete_mapbg.jpg"));
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        stage = new Stage();

        playerName = new Label("Player name: ", skin, "black");
        playerName.setPosition(100, 280);
        color = new Label("Color: ", skin, "black");
        color.setPosition(600, 280);
        race = new Label("Race: ", skin, "black");
        race.setPosition(100, 220);
        money = new Label("Money: ", skin, "black");
        money.setPosition(600, 220);



//      System.out.println(current_player.getColor().toString());

        nameValue = new Label(map.getCurrentPlayer().getName(), skin);
        nameValue.setColor(map.getCurrentPlayer().getColor());
        nameValue.setPosition(220, 280);

        colorValue = new Label(map.getCurrentPlayer().getColorName(), skin);
        colorValue.setColor(map.getCurrentPlayer().getColor());
        colorValue.setPosition(720, 280);

        raceValue = new Label("" + map.getCurrentPlayer().getRace(), skin);
        raceValue.setColor(map.getCurrentPlayer().getColor());
        raceValue.setPosition(220, 220);

        moneyValue = new Label("" + map.getCurrentPlayer().getMoney(), skin);
        moneyValue.setColor(map.getCurrentPlayer().getColor());
        moneyValue.setPosition(720, 220);

        //game input processor handles all the inputs
        Gdx.input.setInputProcessor(new GameInputProcessor(anonymule));

        stage.addActor(playerName);
        stage.addActor(color);
        stage.addActor(race);
        stage.addActor(money);
        stage.addActor(nameValue);
        stage.addActor(colorValue);
        stage.addActor(raceValue);
        stage.addActor(moneyValue);
        map.startGame();
    }

    /**
     * Renders the background and game objects on the screen
     * @param delta Time in seconds since the last render
     */

    @Override
    public void render(float delta) {
        nameValue.setText(""+map.getCurrentPlayer().getName());
        moneyValue.setText(""+map.getCurrentPlayer().getMoney());
        raceValue.setText(""+map.getCurrentPlayer().getRace());


        Gdx.gl.glClearColor(205f/255f, 201f/255f, 201f/255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        currentGameScreen = map.getMap();
        batch.begin();
        batch.draw(currentGameScreen, 120, 300);
        batch.end();

        /**
         * depending on what mode its in, render different things
         */
        switch (map.currentMode){
            case InitialLandGrab:
                shapeRenderer.begin(ShapeRenderer.ShapeType.Rectangle);
                shapeRenderer.setColor(map.rectColor);
                shapeRenderer.rect(map.rectX, map.rectY, map.rectWidth, map.rectHeight);
                for(Player p : map.getPlayerList() ){
                    shapeRenderer.setColor(p.getColor());
                    for (Tile t: p.getPropertyList()){
                        shapeRenderer.rect(t.getX(), t.getY(), map.rectWidth, map.rectHeight);

                    }
                }

                shapeRenderer.end();
                break;
            case Auction:
                break;
            case Store:
                anonymule.setScreen(new StoreScreen(anonymule, this));
                break;
            case Pub:
                break;
            case MuleLand:
                shapeRenderer.begin(ShapeRenderer.ShapeType.Rectangle);
                shapeRenderer.setColor(map.rectColor);
                for(Player p : map.getPlayerList() ){
                    shapeRenderer.setColor(p.getColor());

                    for (Tile t: p.getPropertyList()){
                        shapeRenderer.rect(t.getX(), t.getY(), map.rectWidth, map.rectHeight);
                        if(t.isMule()){
                            shapeRenderer.setColor(t.getMuleColor());
                            System.out.println(t.getMuleColor());
                            shapeRenderer.rect(t.getX(),t.getY(), 10, -10);
                        }
                    }
                }
                shapeRenderer.end();
                shapeRenderer.begin(ShapeRenderer.ShapeType.FilledRectangle);
                shapeRenderer.setColor(map.getCurrentPlayer().getColor());

                shapeRenderer.filledRect(map.getCurrentPlayer().getX(), map.getCurrentPlayer().getY(), 10, 10);
                shapeRenderer.end();
                break;
            case Town:
                shapeRenderer.begin(ShapeRenderer.ShapeType.FilledRectangle);
                shapeRenderer.setColor(map.getCurrentPlayer().getColor());
                shapeRenderer.filledRect(map.getCurrentPlayer().getX(), map.getCurrentPlayer().getY(), 10, 10);
                shapeRenderer.end();
                break;
        }



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
