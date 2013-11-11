package com.cs2340.anonymule;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.Timer;
import com.cs2340.anonymule.Tile.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Map implements Serializable{
    private ArrayList<Player> playerList;
    private int difficulty, map_type;
    private Texture map, mainMap, town, store;
    public static int player_count;
    private Player currentPlayer;
    private int turn, player;
    public int rectWidth = 56;
    public int rectHeight = -51;
    public int rectX = 120;
    public int rectY = 556;
    public Color rectColor = Color.RED;
    public Tile[][] tileMap = new Tile[][]
    		{
            {new PlainsTile(120, 556), new PlainsTile(176, 556), new PlainsTile(231, 556), new MtnTile(287, 556), new RiverTile(343, 556), new MtnTile(399, 556), new PlainsTile(455, 556), new PlainsTile(511, 556), new MtnTile(567, 556)},
            {new PlainsTile(120, 505), new MtnTile(170, 505), new PlainsTile(231, 505), new PlainsTile(287,505), new RiverTile(343, 505), new PlainsTile(399, 505), new PlainsTile(455, 505), new MtnTile(511, 505), new PlainsTile(567, 505)},
            {new PlainsTile(120, 454), new PlainsTile(170, 454), new PlainsTile(231, 454), new MtnTile(287,454), new TownTile(343, 454), new MtnTile(399, 454), new PlainsTile(455, 454), new PlainsTile(511, 454), new PlainsTile(567, 454)},
            {new MtnTile(120, 403), new PlainsTile(170, 403), new PlainsTile(231, 403), new PlainsTile(287,403), new RiverTile(343, 403), new PlainsTile(399, 403), new MtnTile(455, 403), new PlainsTile(511, 403), new PlainsTile(567, 403)},
            {new PlainsTile(120, 352),  new PlainsTile(170, 352), new MtnTile(231, 352), new PlainsTile(287,352), new RiverTile(343, 352), new PlainsTile(399, 352), new PlainsTile(455, 352), new MtnTile(511, 352), new PlainsTile(567, 352)}
    		};
    private int i = 0;
    private int j = 0;
    public boolean exitTimer = false;
    public static enum GameMode {InitialLandGrab, Auction, Store, Pub, MuleLand, Town};
    public GameMode currentMode = GameMode.InitialLandGrab;

    /**
     * Constructs a map with no players, difficulty 1(Easy) and Map type 1
     */

    public Map() {
        this(new ArrayList<Player>(), 1, 1);
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
        
        if(map_type == 1){
            mainMap = new Texture(Gdx.files.internal("textures/Map.jpg"));
            town = new Texture(Gdx.files.internal("textures/town.jpg"));
            store = new Texture(Gdx.files.internal("textures/Concrete_splashbg.jpg"));
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
    
    /**
     * Begins the turns
     */

    public void startGame() {
        nextTurn();
    }

    /**
     * calculates who will be the next turn and what to do for said turn
     */
    public void nextTurn() {
        if(turn <= (player_count*2)){  //if its the first few turns, initiate the land grab
            initLandGrab();
        }else{
            currentMode = GameMode.MuleLand;
            map = mainMap;
            startRound();
        }


        if (player == getPlayerList().size()-1) {
            player = 0;
        }
        else {
            player++;
        }
        currentPlayer = getPlayerList().get(player);
        turn++;
    }

    /**
     * starts the main rounds with timers and stuff
     */
    private void startRound(){
        Collections.sort(playerList, new CustomComparator());
        Timer.schedule(new Timer.Task(){
            public void run(){
                nextTurn();
            }
        },10, 10, player_count-1);
    }
    
    /**
     * Get the player whose turn it is
     * @return The player whose turn it is
     */

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    /**
     * The turn the game is on
     * @return The number of the turn
     */

    public int getTurn() {
        return turn;
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
    
    /**
     * Get the tile holder
     * @return The 2d array which holds the tiles
     */

    public Tile[][] getTileMap() {
        return tileMap;
    }
    
    /**
     * Get the texture of the map?
     * @return The texture of the map?
     */

    public Texture getMap() {
        return map;
    }
    
    /**
     * Get the texture of the town?
     * @return The texture of the town?
     */

    public Texture getTown(){
        return town;
    }
    
    /**
     * Get the tile the player is currently on
     * @return The tile which the player is on
     */

    public Tile getCurrentTile(){
        return tileMap[j][i];
    }
    
    /**
     * decreases turn, changes player to last player
     */

    public void decrementTurn(){
        turn--;
        player--;
    }

    /**
     * does basic logic to repeat a land grab for the players twice
     */
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
    
    /**
     * I'm guessing this has to do with changing the screens, I dunno
     */
    
    public void mapToTown(){
        map = town;
    }
    
    /**
     * I'm guessing this has to do with changing screens, I dunno
     */
    
    public void townToMap(){
        map = mainMap;
    }

    /**
     * clear timer and allocate player money
     */
    public void gamble(){
        currentPlayer.setMoney(currentPlayer.getMoney()+100);
        Timer.instance.clear();
        nextTurn();
    }

    /**
     * simple comparator to see which player has more money
     */
    private class CustomComparator implements Comparator<Player> {
        @Override
        public int compare(Player o1, Player o2) {
            return o1.getMoney()-(o2.getMoney());
        }
    }

    /**
     * change game state so player can enter store
     */
    public void enterStore() {
        map = store;
        currentMode = GameMode.Store;
        Timer.instance.clear();
    }

	@Override
	public void write(Json json) {
		json.writeValue( "playerList", playerList );
        json.writeValue( "difficulty", difficulty );
        json.writeValue( "map_type", map_type );
        json.writeValue( "player_count", player_count );
        json.writeValue( "currentPlayer", currentPlayer );
        json.writeValue( "turn", turn );
        json.writeValue( "player", player );
        json.writeValue( "tileMap", tileMap );
        json.writeValue( "currentMode", currentMode );
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		playerList = json.readValue( "playerList", ArrayList.class, Player.class, jsonData );
        difficulty = json.readValue( "difficulty", Integer.class, jsonData );
        map_type = json.readValue( "map_type", Integer.class, jsonData );
        player_count = json.readValue( "player_count", Integer.class, jsonData );
        currentPlayer = json.readValue( "currentPlayer", Player.class, jsonData);
        turn = json.readValue( "turn", Integer.class, jsonData );
        player = json.readValue( "player", Integer.class, jsonData );
        tileMap = json.readValue( "tileMap", Tile[][].class, jsonData );
        currentMode = json.readValue( "credits", GameMode.class, jsonData );
	}


}
