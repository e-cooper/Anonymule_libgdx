package com.cs2340.anonymule.Tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.cs2340.anonymule.Player;

//import java.awt.Color;
//import java.awt.Graphics;

public class Tile {
    private static final int TILE_SIZE = 85;
    private int x, y;
    private boolean canBuy = true;
    private String type;
    private int cost = 300;
    private Color color;
    boolean mule;
    int muleType;
    private Color muleColor;
    
    /**
     * Blank Constructor
     */
    
    public Tile() {
    	this(0, 0);
    }
    
    /**
     * Constructor which sets up a tile object
     * @param x The x-coordinate of the tile
     * @param y The y-coordinate of the tile
     */

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        mule = false;

    }

    /**
     *
     * @param p player who bought property
     * @return  true if bought
     */
    public boolean buyTile(Player p) {
        if (this.canBuy == true && p.getMoney() >= cost) {
            p.setMoney(p.getMoney() - cost);
            this.setCanBuy(false);
            return true;
        }
        return false;
    }
    
    /**
     * Checks if the tile can be bought
     * @return false if cannot buy, true if can buy
     */

    public boolean getCanBuy() {
        return canBuy;
    }

    /**
     *
     * @param canBuy  can player buy
     */
    public void setCanBuy(boolean canBuy) {
        this.canBuy = canBuy;
    }

    public int getCost() {
        return cost;
    }

    /**
     * Sets the cost of the tile
     * @param cost cost to player
     */
    public void setCost(int cost) {
        this.cost = cost;
    }
    
    /**
     * Get the x-coordinate
     * @return The x-coordinate of the tile
     */

    public int getX() {
        return x;
    }
    
    /**
     * Get the y-coordinate
     * @return The y-coordinate of the tile
     */

    public int getY() {
        return y;
    }

    /**
     * Sets the mule on the tile
     * @param num type of mule bought
     */
    public void setMule(int num){
        System.out.println("!!!");
        switch(num){
            case 1: muleType = 1;
                muleColor = Color.YELLOW;
                break;
            case 2: muleType = 2;
                muleColor = Color.PINK;
                break;
            case 3: muleType = 3;
                muleColor = Color.CYAN;
                break;

        }
        mule = true;
    }
    
    /**
     * Checks to see if there is a mule on the tile
     * @return true if yes, false if no
     */

    public boolean isMule(){
        return mule;
    }
    
    /**
     * Getter for muleType on the tile
     * @return The type of mule on the tile
     */

    public int muleType(){
        return muleType;
    }
    
    /**
     * Getter for the muleColor on the tile
     * @return The color of the mule on the tile
     */

    public Color getMuleColor(){
        return muleColor;
    }
}
