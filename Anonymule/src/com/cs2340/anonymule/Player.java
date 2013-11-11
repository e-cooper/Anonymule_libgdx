package com.cs2340.anonymule;

import com.badlogic.gdx.graphics.Color;
import com.cs2340.anonymule.Tile.Tile;

import java.awt.Point;
import java.util.ArrayList;

public class Player {
    private String colorName;
    private String  name;
    private Point location;
    private Color color;
    private int food;
    private int energy;
    private int smithore;
    private int x, y, money;
    private int race;
    private static int player_count = 0;
    private ArrayList<Tile> playerProperties = new ArrayList<Tile>();

    /**
     * Creates a place with color "red", race "race1" and default name "player1", "player2" etc
     */

    public Player(){
        this("red", 1, "player" + (player_count+1));
        player_count++;
    }

    /**
     * Creates a player with the specified color, race and name
     * @param colorName Color of the player
     * @param race Race of the player
     * @param name Name of the player
     */

    public Player(String colorName, int race, String name) {
        this.colorName = colorName;
        this.race = race;
        this.name = name;
        this.x = 343;
        this.y = 454;
        food = 0;
        energy = 0;
        smithore = 0;
        money = 1000;

    }

    /**
     * Returns the race of the player
     * @return The race of the player
     */

    public int getRace() {
        return race;
    }

    /**
     * Sets the race of the player
     * @param race  The new race of the player
     */

    public void setRace(int race) {
        this.race = race;
    }

    /**
     * Returns the name of the player
     * @return The name of the player
     */

    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player
     * @param name The new name of the player
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the attributes of the player
     * @param colorName The color of the player
     * @param race The race of the player
     * @param name The name of the player
     */

    public void setAttributes(String colorName, int race, String name){
        setRace(race);
        setName(name);
        setColor(colorName);
    }

    /**
     * Sets the color of the player
     * @param color Color to be set to
     */
    public void setColor(String color) {
        if(color == "red")
            this.color = Color.RED;
        if(color == "green")
            this.color = Color.GREEN;
        if(color == "blue")
            this.color = Color.BLUE;
        if(color == "orange")
            this.color = Color.ORANGE;
    }
    
    /**
     * Returns the color of the player
     * @return The color of the player
     */

    public Color getColor() {
        return color;
    }
    
    /**
     * Returns the name of the color as a string
     * @return The string associated with the color
     */

    public String getColorName(){
        return colorName;
    }
    
    /**
     * Returns the location of the player as a point
     * @return The point of the player
     */

    public Point getLocation() {
        return location;
    }

    /**
     *
     * @param location  where player is right now
     */
    public void setLocation(Point location) {
        this.location = location;
    }
    
    /**
     * Getter for player's x-coordinate
     * @return The x-coordinate of the player
     */

    public int getX() {
        return x;
    }
    
    /**
     * Sets the x-coordinate of the player
     * @param x The x-coordinate to set the player to
     */

    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Getter for player's y-coordinate
     * @return The y-coordinate of the player
     */

    public int getY() {
        return y;
    }
    
    /**
     * Sets the y-coordinate of the player
     * @param y The y-coordinate to set the player to
     */

    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Get the amount of money the player has
     * @return The amount of money the player has
     */

    public int getMoney() {
        return money;
    }

    /**
     * Set the player's money count
     * @param money Amount to set money to
     */
    public void setMoney(int money) {
        this.money = money;
    }
    
    /**
     * Get the properties a player owns
     * @return The list of properties owned by the player
     */

    public ArrayList<Tile> getPropertyList(){
        return playerProperties;
    }
    
    /**
     * Add food to player
     * @param x The amount of food to add
     */

    public void addFood(int x){
        food += x;
    }
    
    /**
     * Subtract food from player
     * @param x Amount of food to subtract
     */

    public void subFood(int x){
        food -= x;
    }
    
    /**
     * Add energy to player
     * @param x Amount of energy to add
     */

    public void addEnergy(int x){
        energy += x;
    }
    
    /**
     * Subtract energy from player
     * @param x Amount of energy to subtract
     */

    public void subEnergy(int x){
        energy -= x;
    }
    
    /**
     * Add smithore to player
     * @param x Amount of smithore to add
     */
    
    public void addSmithore(int x){
        smithore += x;
    }
    
    /**
     * Subtract the smithore from player
     * @param x Amount of smithore to subtract
     */

    public void subSmithore(int x){
        smithore -= x;
    }
    
    /**
     * Getter for the food of player
     * @return Amount of food player has
     */

    public int getFood(){
        return food;
    }
    
    /**
     * Getter for the energy of the player
     * @return Amount of energy the player has
     */

    public int getEnergy(){
        return energy;
    }
    
    /**
     * Getter for the smithore of the player
     * @return Amount of smithore the player has
     */

    public int getSmithore(){
        return smithore;
    }



}
