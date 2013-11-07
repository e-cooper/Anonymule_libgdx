package com.cs2340.anonymule.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.cs2340.anonymule.Anonymule;
import com.cs2340.anonymule.Input.GameInputProcessor;
import com.cs2340.anonymule.Map;


/**
 * Created with IntelliJ IDEA.
 * User: Montek
 * Date: 11/5/13
 * Time: 2:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class StoreScreen implements Screen {
    private Anonymule anonymule;
    private Map map;
    private Label player;
    private Label money;
    private Label color;
    private Label race;
    private Label energyLabel;
    private Label foodLabel;
    private Label smithoreLabel;

    private Label playerName;
    private Label moneyVal;
    private Label colorVal;
    private Label raceVal;
    private Label energyVal;
    private Label foodVal;
    private Label smithoreVal;
    private Label energy;
    private Label food;
    private Label smithore;

    private TextField energyField;
    private TextField foodField;
    private TextField smithoreField;

    private TextButton buy;
    private TextButton sell;
    private TextButton done;


    private Skin skin;
    private Stage stage;

    private GameScreen gameScreen;

    private SpriteBatch batch;
    
    /**
     * Constructor which sets up the store screen
     * @param anonymule The game instance
     * @param screen The current gamescreen
     */

    public StoreScreen(Anonymule anonymule, GameScreen screen){
        gameScreen = screen;
        this.anonymule = anonymule;
        map = anonymule.getMap();
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        stage = new Stage(480, 800, false);
        batch = new SpriteBatch();

        player = new Label("Name", skin, "black");
        player.setPosition(100, 300);
        color = new Label("color", skin, "black");
        color.setPosition(100, 280);
        race = new Label("Race", skin, "black");
        race.setPosition(100, 260);
        money = new Label("Money", skin, "black");
        money.setPosition(100, 240);
        energy = new Label("Energy", skin, "black");
        energy.setPosition(100,220);
        food = new Label("Food", skin, "black");
        food.setPosition(100,200);
        smithore = new Label("Smithore", skin, "black");
        smithore.setPosition(100, 180);

        energyLabel = new Label("Energy", skin, "black");
        energyLabel.setPosition(100, 400);
        foodLabel = new Label("Food", skin, "black");
        foodLabel.setPosition(100, 430);
        smithoreLabel = new Label("Smithore",skin, "black");
        smithoreLabel.setPosition(100, 460);

        energyField = new TextField("0", skin);
        energyField.setPosition(150, 400);
        foodField = new TextField("0", skin);
        foodField.setPosition(150, 430);
        smithoreField = new TextField("0", skin);
        smithoreField.setPosition(150, 460);

        buy = new TextButton("Buy", skin);
        buy.setPosition(400, 400);
        sell = new TextButton("Sell", skin);
        sell.setPosition(400, 430);
        done = new TextButton("Done", skin);
        done.setPosition(400, 460);

        playerName = new Label("", skin, "black");
        playerName.setPosition(150,300);
        colorVal = new Label("", skin, "black");
        colorVal.setPosition(150, 280);
        raceVal = new Label("", skin, "black");
        raceVal.setPosition(150, 260);
        moneyVal = new Label("", skin, "black");
        moneyVal.setPosition(150, 240);
        energyVal = new Label("", skin, "black");
        energyVal.setPosition(150, 220);
        foodVal = new Label("", skin, "black");
        foodVal.setPosition(150, 200);
        smithoreVal = new Label("", skin, "black");
        smithoreVal.setPosition(150, 180);

        energyVal = new Label("", skin, "black");
        smithoreVal = new Label("", skin, "black");
        colorVal = new Label("", skin, "black");



        Gdx.input.setInputProcessor(stage);

        buy.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                buy();
            }
        });

        sell.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                sell();
            }
        });

        done.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                done();
            }
        });

        stage.addActor(player);
        stage.addActor(money);
        stage.addActor(color);
        stage.addActor(race);
        stage.addActor(energyLabel);
        stage.addActor(foodLabel);
        stage.addActor(smithoreLabel);

        stage.addActor(playerName);
        stage.addActor(moneyVal);
        stage.addActor(colorVal);
        stage.addActor(raceVal);
        stage.addActor(energyVal);
        stage.addActor(foodVal);
        stage.addActor(smithoreVal);
        stage.addActor(food);
        stage.addActor(energy);
        stage.addActor(smithore);

        stage.addActor(foodField);
        stage.addActor(energyField);
        stage.addActor(smithoreField);
        stage.addActor(buy);
        stage.addActor(sell);
        stage.addActor(done);


    }
    
    /**
     * Allows player to buy from stores in town
     */

    public void buy(){
        int money = 0;
        if(map.getCurrentPlayer().getMoney() > Integer.parseInt(foodField.getText())){
            System.out.println(foodField.getMessageText());
            map.getCurrentPlayer().addFood(Integer.parseInt(foodField.getText()));
            money = Integer.parseInt(foodField.getText());
            map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()-money);

        }

        if(map.getCurrentPlayer().getMoney()>Integer.parseInt(foodField.getText())){
            map.getCurrentPlayer().addEnergy(Integer.parseInt(energyField.getText()));
            money = Integer.parseInt(energyField.getText());
            map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()-money);


        }
        if(map.getCurrentPlayer().getMoney()>Integer.parseInt(smithoreField.getText())){
            map.getCurrentPlayer().addSmithore(Integer.parseInt(smithoreField.getText()));
            money += Integer.parseInt(smithoreField.getText());
            map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()-money);

        }


    }
    
    /**
     * Allows player to sell to store
     */

    public void sell(){
        int money = 0;
        if(map.getCurrentPlayer().getFood()>Integer.parseInt(foodField.getText())){
            map.getCurrentPlayer().subFood(Integer.parseInt(foodField.getText()));
            money = Integer.parseInt(foodField.getText());
            map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()+money);

        }
        if(map.getCurrentPlayer().getEnergy()>Integer.parseInt(energyField.getText())){
            map.getCurrentPlayer().subEnergy(Integer.parseInt(energyField.getText()));
            money = Integer.parseInt(energyField.getText());
            map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()+money);

        }
        if(map.getCurrentPlayer().getSmithore()>Integer.parseInt(smithoreField.getText())){

            map.getCurrentPlayer().subSmithore(Integer.parseInt(smithoreField.getText()));
            money = Integer.parseInt(smithoreField.getText());
            map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()+money);

        }

    }
    
    /**
     * Changes screen to the appropriate other screen after exiting town
     */

    public void done(){
        anonymule.setScreen(gameScreen);
        map.currentMode = Map.GameMode.MuleLand;
        map.townToMap();
        Gdx.input.setInputProcessor(new GameInputProcessor(anonymule));
    }
    
    /**
     * HUD for inventory values
     */

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor( 0f, 0f, 0.2f, 1f );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT );

        batch.begin();
        batch.draw(map.getMap(), 0,0);
        batch.end();

        playerName.setText("" + map.getCurrentPlayer().getName());
        moneyVal.setText(""+map.getCurrentPlayer().getMoney());
        raceVal.setText(""+map.getCurrentPlayer().getRace());
        colorVal.setText(""+map.getCurrentPlayer().getColorName());
        foodVal.setText(""+map.getCurrentPlayer().getFood());
        energyVal.setText(""+map.getCurrentPlayer().getEnergy());
        smithoreVal.setText(""+map.getCurrentPlayer().getSmithore());


        batch.begin();
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
