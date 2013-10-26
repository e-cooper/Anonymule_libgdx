package com.cs2340.anonymule;

import com.cs2340.anonymule.Tile.*;

import java.util.ArrayList;

public class Map {
    private ArrayList<Player> playerList;
    private int difficulty, map_type;
    private Tile[][] tiles = new Tile[5][9];
    private String[][] tileTypes = {
            {"P","P","M1","P","R","P","M3","P","P"},
            {"P","M1","P","P","R","P","P","P","M3"},
            {"M3","P","P","P","T","P","P","P","M1"},
            {"P","M2","P","P","R","P","M2","P","P"},
            {"P","P","M2","P","R","P","P","P","M2"}
    };
    public static int player_count;
    private Player currentPlayer;
    private int turn, player;

    /**
     * Constructs a map with no players, difficulty 1(Easy) and Map type 1
     */

    public Map(){
        this(new ArrayList<Player>(), 1, 1);
        if(map_type == 1)
            buildStdMap();
        else
            buildRdmMap();
    }

    private void buildStdMap() {
        for (int rows = 0; rows < tiles.length; rows++) {
            for (int cols = 0; cols < tiles[rows].length; cols++) {
                Tile t = null;
                String temp = tileTypes[rows][cols];

                if(temp.equals("P"))
                    t = new PlainsTile(rows, cols, "P");
                else if(temp.equals("R"))
                    t = new RiverTile(rows, cols, "R");
                else if(temp.equals("M1"))
                    t = new OneMtnTile(rows, cols, "M1");
                else if(temp.equals("M2"))
                    t = new TwoMtnTile(rows, cols, "M2");
                else if(temp.equals("M3"))
                    t = new ThreeMtnTile(rows, cols, "M3");
                else if(temp.equals("T"))
                    t = new TownTile(rows, cols, "T");

                tiles[rows][cols] = t;
            }
        }
    }

    private void buildRdmMap() {
        //TODO: Build a random map here
    }

    public void nextTurn() {
        turn++;
        if (player + 1 == getPlayerList().size()) {
            player = 0;
        }
        else {
            player++;
        }
        currentPlayer = getPlayerList().get(player);
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
//        addPlayer(new Player());
//        currentPlayer = getPlayerList().get(player);
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

    public Tile[][] getTiles() {
        return tiles;
    }
}
