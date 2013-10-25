package com.cs2340.anonymule;

import java.util.ArrayList;

public class Map {
    private ArrayList<Player> playerList;
    private int difficulty, map_type;
    private int[][] mapGrid;
    public static int player_count;

    /**
     * Constructs a map with no players, difficulty 1(Easy) and Map type 1
     */

    public Map(){
        this(new ArrayList<Player>(), 1, 1);
        mapGrid = new int[5][5];

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
}
