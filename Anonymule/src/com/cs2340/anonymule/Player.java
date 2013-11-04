package com.cs2340.anonymule;

import com.badlogic.gdx.graphics.Color;
import com.cs2340.anonymule.Tile.Tile;

import java.awt.Point;
import java.util.ArrayList;

public class Player {
    private String colorName, name;
    private Point location;
    private Color color;
    private int x, y, money;
    private int race;
    private static int player_count = 0;
    private ArrayList<Tile> playerProperties = new ArrayList<Tile>();

    /**
     * Creates a place with color "red", race "race1" and default name "player1", "player2" etc
     */

    public Player(){
        this("red", 1, "player" + (player_count+1), Color.RED);
        player_count++;
    }

    /**
     * Creates a player with the specified color, race and name
     * @param colorName Color of the player
     * @param race Race of the player
     * @param name Name of the player
     */

    public Player(String colorName, int race, String name, Color color) {
        this.colorName = colorName;
        this.race = race;
        this.name = name;
        this.color = color;
        this.x = 343;
        this.y = 454;
    }

    /**
     * Returns the color of the player
     * @return The color of the player
     */

    public String getColorName() {
        return colorName;
    }

    /**
     * Sets the color of the player
     * @param color The new color of the player
     */

    public void setColorName(String color) {
        this.colorName = color;
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
        setColorName(colorName);
        setRace(race);
        setName(name);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Point getLocation() {
        return location;
    }

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

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<Tile> getPropertyList(){
        return playerProperties;
    }

    public void printBro(){
        System.out.println("Color: " + colorName);
        System.out.println("Race: " + race);
        System.out.println("Name: " + name);
    }
}
