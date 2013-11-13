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
    private int foodPlant = 0, energyPlant = 0, smithorePlant = 0;
    private boolean isLowest = false;
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
     * Returns the color of the player
     * @return The color of the player
     */



    /**
     * Sets the color of the player
     * @param color The new color of the player
     */



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
     *
     * @param color   color to be set to
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

    public Color getColor() {
        return color;
    }

    public String getColorName(){
        return colorName;
    }

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMoney() {
        return money;
    }

    /**
     *
     * @param money    amount to set money to
     */
    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<Tile> getPropertyList(){
        return playerProperties;
    }

    public void printBro(){
        System.out.println("Color: " + color);
        System.out.println("Race: " + race);
        System.out.println("Name: " + name);
    }

    public void addFood(int x){
        food += x;
    }

    public void subFood(int x){
        food -= x;
    }

    public void addEnergy(int x){
        energy += x;
    }

    public void subEnergy(int x){
        energy -= x;
    }
    public void addSmithore(int x){
        smithore += x;
    }

    public void subSmithore(int x){
        smithore -= x;
    }

    public int getFood(){
        return food;
    }

    public int getEnergy(){
        return energy;
    }

    public int getSmithore(){
        return smithore;
    }

	public boolean isLowest() {
		return isLowest;
	}

	public void setLowest(boolean isLowest) {
		this.isLowest = isLowest;
	}

	public int getSmithorePlant() {
		return smithorePlant;
	}

	public void addSmithorePlant() {
		smithorePlant++;
	}

	public int getEnergyPlant() {
		return energyPlant;
	}

	public void addEnergyPlant() {
		energyPlant++;
	}

	public int getFoodPlant() {
		return foodPlant;
	}

	public void addFoodPlant() {
		foodPlant++;
	}

	



}
