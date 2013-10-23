package com.cs2340.anonymule;

public class Player {
    private String color, race, name;
    private static int player_count = 0;

    /**
     * Creates a place with color "red", race "race1" and default name "player1", "player2" etc
     */

    public Player(){
        this("red", "race1", "player" + (player_count+1));
        player_count++;
    }

    /**
     * Creates a player with the specified color, race and name
     * @param color Color of the player
     * @param race Race of the player
     * @param name Name of the player
     */

    public Player(String color, String race, String name) {
        this.color = color;
        this.race = race;
        this.name = name;
    }

    /**
     * Returns the color of the player
     * @return The color of the player
     */

    public String getColor() {
        return color;
    }

    /**
     * Sets the color of the player
     * @param color The new color of the player
     */

    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Returns the race of the player
     * @return The race of the player
     */

    public String getRace() {
        return race;
    }

    /**
     * Sets the race of the player
     * @param race  The new race of the player
     */

    public void setRace(String race) {
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
     * @param color The color of the player
     * @param race The race of the player
     * @param name The name of the player
     */

    public void setAttributes(String color, String race, String name){
        setColor(color);
        setRace(race);
        setName(name);
    }


    public void printBro(){
        System.out.println("Color: " + color);
        System.out.println("Race: " + race);
        System.out.println("Name: " + name);
    }
}
