package com.cs2340.anonymule;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.cs2340.anonymule.Tile.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import com.badlogic.gdx.InputProcessor;

public class Map {
    private ArrayList<Player> playerList;
    private int difficulty, map_type;
    private Texture mainMap;
    private Texture map;
    public static int player_count;
    private Player currentPlayer;
    private int turn, player;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    public int rectWidth = 56;
    public int rectHeight = -51;
    public int rectX = 120;
    public int rectY = 556;
    public Color rectColor = Color.RED;
    public Tile[][] tileMap;
    private int i = 0;
    private int j = 0;
    public boolean exitTimer = false;
    private Texture town;



    public static enum GameMode {InitialLandGrab, Auction, Store, Pub, MuleLand, Town};

    public GameMode currentMode = GameMode.InitialLandGrab;

    /**
     * Constructs a map with no players, difficulty 1(Easy) and Map type 1
     */

    public Map(){
        this(new ArrayList<Player>(), 1, 1);

        if(map_type == 1){
            mainMap = new Texture(Gdx.files.internal("Anonymule/assets/textures/Map.jpg"));
            town = new Texture(Gdx.files.internal("Anonymule/assets/textures/town.jpg"));
            tileMap = new Tile[][]{
                    {new PlainsTile(120, 556), new PlainsTile(176, 556), new PlainsTile(231, 556), new MtnTile(287, 556), new RiverTile(343, 556), new MtnTile(399, 556), new PlainsTile(455, 556), new PlainsTile(511, 556), new MtnTile(567, 556)},
                    {new PlainsTile(120, 505), new MtnTile(170, 505), new PlainsTile(231, 505), new PlainsTile(287,505), new RiverTile(343, 505), new PlainsTile(399, 505), new PlainsTile(455, 505), new MtnTile(511, 505), new PlainsTile(567, 505)},
                    {new PlainsTile(120, 454), new PlainsTile(170, 454), new PlainsTile(231, 454), new MtnTile(287,454), new TownTile(343, 454), new MtnTile(399, 454), new PlainsTile(455, 454), new PlainsTile(511, 454), new PlainsTile(567, 454)},
                    {new MtnTile(120, 403), new PlainsTile(170, 403), new PlainsTile(231, 403), new PlainsTile(287,403), new RiverTile(343, 403), new PlainsTile(399, 403), new MtnTile(455, 403), new PlainsTile(511, 403), new PlainsTile(567, 403)},
                    {new PlainsTile(120, 352),  new PlainsTile(170, 352), new MtnTile(231, 352), new PlainsTile(287,352), new RiverTile(343, 352), new PlainsTile(399, 352), new PlainsTile(455, 352), new MtnTile(511, 352), new PlainsTile(567, 352)}
            };
        }
        map = mainMap;


    }

    public void startGame() {
        nextTurn();
    }

    public void nextTurn() {
        if(turn <= (player_count)){  //if its the first few turns, initiate the land grab
            initLandGrab();
        }else{
            currentMode = GameMode.MuleLand;
            startRound();
        }

        turn++;
        if (player + 1 == getPlayerList().size()) {
            player = 0;
        }
        else {
            player++;
        }
        currentPlayer = getPlayerList().get(player);

    }

    private void startRound(){
        player = 0;
        Collections.sort(playerList, new CustomComparator());
        Timer.schedule(new Timer.Task(){
            public void run(){
                currentPlayer = getPlayerList().get(player);
                player++;
            }

        },1, 10, player_count-1);




    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getTurn() {
        return turn;
    }


    /**
     * Constructs a map with the given player arraylist, difficulty and map type
     * @param playerList The player list
     * @param difficulty The difficulty of the map
     * @param map_type The map type
     */

    public Map(ArrayList<Player> playerList, int difficulty, int map_type) {
        this.playerList = playerList;
        this.difficulty = difficulty;
        this.map_type = map_type;
        player = 0;
        turn = 1;
        currentPlayer = null;

    }

    /**
     * Gets the list of the players
     * @return The list of players
     */

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Adds a player to the list of players
     * @param player The player to add to the list
     */

    public void addPlayer(Player player){
        playerList.add(player);
        currentPlayer = getPlayerList().get(0);
        player_count++;

    }

    /**
     * Sets the list of the players to this new list
     * @param playerList The new list of players
     */

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    /**
     * Gets the difficulty of the map
     * @return The difficulty of the map
     */

    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty of the map
     * @param difficulty The difficulty of the map
     */

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets the map type
     * @return The map type
     */

    public int getMap_type() {
        return map_type;
    }

    /**
     * Sets the map type
     * @param map_type The new map type
     */

    public void setMap_type(int map_type) {
        this.map_type = map_type;
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }

    public Texture getMap() {
        return map;
    }

    public Texture getTown(){
        return town;
    }

    public Tile getCurrentTile(){
        return tileMap[j][i];
    }

    public void decrementTurn(){
        turn--;
        player--;
    }

    private void initLandGrab(){
        i = 0;
        j = 0;
        Timer.schedule(new Timer.Task(){
            public void run(){
                rectX = tileMap[j][i].getX();
                rectY = tileMap[j][i].getY();

                if(j == 4 && i == 8)
                    j = 0;
                else
                if(i == 8)
                    j++;
                if(i == 8)
                    i = 0;
                else
                    i++;
            }

        },1, 1);
    }
    public void mapToTown(){
        map = town;
    }
    public void townToMap(){
        map = mainMap;
    }

    public void gamble(){
        currentPlayer.setMoney(currentPlayer.getMoney()+100);
    }

    private class CustomComparator implements Comparator<Player> {
        @Override
        public int compare(Player o1, Player o2) {
            return o1.getMoney()-(o2.getMoney());
        }
    }

	public void store() {
		//TODO Need to Implement-Shafiq
	}

	public void muleShop() {
		// TODO Need to Implement-Shafiq
		if (Gdx.input.isKeyPressed(Keys.B) && currentPlayer.getMoney() >= 90){
			System.out.println("Player bought a mule!");
	        currentPlayer.setMoney(currentPlayer.getMoney()-90);
		}
		if (Gdx.input.isKeyPressed(Keys.S)){
			System.out.println("Player Sold a mule!");
	        currentPlayer.setMoney(currentPlayer.getMoney()+90);
		}
	} //ends muleShop
}//Ends class
