package com.cs2340.anonymule;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.cs2340.anonymule.Tile.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

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
    private Texture store;
    private int randomNumber;
    private String randomEventsStatus = "";
    Random randomGenerator = new Random();

    
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
            store = new Texture(Gdx.files.internal("Anonymule/assets/textures/Concrete_splashbg.jpg"));
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

    /**
     * calculates who will be the next turn and what to do for said turn
     */
    public void nextTurn() {
    	System.out.println("DEBUG: Next turn method from the map class called!");
    	calculateProduction();
    	
        if(turn <= (player_count)){  //if its the first few turns, initiate the land grab
            initLandGrab();
        }else{
            currentMode = GameMode.MuleLand;
            map = mainMap;
            startRound();
        }

       // Put code in to raise resource
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
     * calculates the units of production for the next round
     */
    private void calculateProduction() {
    	//Case if player has a food plant
		if (currentPlayer.getFoodPlant() > 0 && currentPlayer.getEnergy() > 3){
			int plants = currentPlayer.getFoodPlant();
			currentPlayer.addFood(plants*3);
			currentPlayer.addEnergy(-(plants*3));
		}
		
		
		//Case if player has an energy plant
		if (currentPlayer.getEnergyPlant() > 0 && currentPlayer.getEnergy() > 3){
			int plants = currentPlayer.getEnergyPlant();
			currentPlayer.addEnergy(plants*3*2);
			currentPlayer.addEnergy(-(plants*3));
		}

		//Case if player has a smithore plant
		if (currentPlayer.getSmithorePlant() > 0 && currentPlayer.getEnergy() > 3){
			int plants = currentPlayer.getSmithorePlant();
			currentPlayer.addSmithore(plants*3);
			currentPlayer.addEnergy(-(plants*3));
		}

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
    
    /**
     * Determines who has the least money so bad random events don't affect that player
     */
    public void determineLowest(){
    		if(playerList.size()  == 2){	//2 players
    			if(playerList.get(0).getMoney() < playerList.get(1).getMoney()){
    				playerList.get(0).setLowest(true);
    				playerList.get(1).setLowest(false);
    			}
    		}	//ends 2 player case
    		
    		else if(playerList.size() == 3){
    			if(playerList.get(0).getMoney() < playerList.get(1).getMoney() && playerList.get(0).getMoney() < playerList.get(2).getMoney()){
    				playerList.get(0).setLowest(true);
    				playerList.get(1).setLowest(false);
    				playerList.get(2).setLowest(false);
    			}
    			else if(playerList.get(1).getMoney() < playerList.get(0).getMoney() && playerList.get(1).getMoney() < playerList.get(2).getMoney()){
    				playerList.get(0).setLowest(false);
    				playerList.get(1).setLowest(true);
    				playerList.get(2).setLowest(false);
    			}
    			else if(playerList.get(2).getMoney() < playerList.get(0).getMoney() && playerList.get(2).getMoney() < playerList.get(1).getMoney()){
    				playerList.get(0).setLowest(false);
    				playerList.get(1).setLowest(false);
    				playerList.get(2).setLowest(true);
    			}
    		} // ends 3 player case
    		
    		else if(playerList.size() == 4){
    			if(playerList.get(0).getMoney() < playerList.get(1).getMoney() && playerList.get(0).getMoney() < playerList.get(2).getMoney()
    				&& playerList.get(0).getMoney() < playerList.get(3).getMoney()){
    				playerList.get(0).setLowest(true);
    				playerList.get(1).setLowest(false);
    				playerList.get(2).setLowest(false);
    				playerList.get(3).setLowest(false);
    			}
    			else if(playerList.get(1).getMoney() < playerList.get(0).getMoney() && playerList.get(1).getMoney() < playerList.get(2).getMoney()
        				&& playerList.get(1).getMoney() < playerList.get(3).getMoney()){
        				playerList.get(0).setLowest(false);
        				playerList.get(1).setLowest(true);
        				playerList.get(2).setLowest(false);
        				playerList.get(3).setLowest(false);
        			}
    			else if(playerList.get(2).getMoney() < playerList.get(1).getMoney() && playerList.get(2).getMoney() < playerList.get(0).getMoney()
        				&& playerList.get(2).getMoney() < playerList.get(3).getMoney()){
        				playerList.get(0).setLowest(false);
        				playerList.get(1).setLowest(false);
        				playerList.get(2).setLowest(true);
        				playerList.get(3).setLowest(false);
        			}
    			else if(playerList.get(3).getMoney() < playerList.get(1).getMoney() && playerList.get(3).getMoney() < playerList.get(2).getMoney()
        				&& playerList.get(3).getMoney() < playerList.get(0).getMoney()){
        				playerList.get(0).setLowest(false);
        				playerList.get(1).setLowest(false);
        				playerList.get(2).setLowest(false);
        				playerList.get(3).setLowest(true);
        			}
    		} //ends 4 player case
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
    public void mapToTown(){
        map = town;
    }
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
     * simple compartor to see which player has more money
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

	public String getRandomEventsStatus() {
		return randomEventsStatus;
	}

	public void setRandomEventsStatus(String randomEventsStatus) {
		this.randomEventsStatus = randomEventsStatus;
	}


}
