package com.cs2340.anonymule.Tile;

import com.badlogic.gdx.graphics.Color;
import com.cs2340.anonymule.Player;

//import java.awt.Color;
//import java.awt.Graphics;

public class Tile {
    private static final int TILE_SIZE = 85;
    private int row, col;
    private boolean canBuy = true;
    private String type;
    private int cost = 300;
    private Player owner = null;
    private Color color;

    public Tile(int row, int col, String type) {
        this.row = row;
        this.col = col;
        this.type = type;

        if(type.equals("P"))
            color = new Color(245f/255f, 245f/255f, 220f/250f, 1f); //beige
        else if(type.equals("M1"))
            color = new Color(255f/255f, 239f/255f, 219f/255f, 1f); //antiquewhite1
        else if(type.equals("M2"))
            color = new Color(238f/255f, 223f/255f, 204f/255f, 1f); //antiquewhite2
        else if(type.equals("M3"))
            color = new Color(205f/255f, 192f/255f, 176f/255f, 1f); //antiquewhite3
        else if(type.equals("R"))
            color = new Color(95f/255f, 158f/255f, 160f/255f, 1f); //cadetblue
        else if(type.equals("T"))
            color = Color.DARK_GRAY;
    }


        /*
        g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        g.setColorName(Color.black);
        g.drawRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);


        if (owner != null) {
            g.setColorName(owner.getColorName());
            g.drawRect(col * TILE_SIZE + 1, row * TILE_SIZE + 1, TILE_SIZE - 2, TILE_SIZE - 2);
        }
        */
    //}

    public boolean buyTile(Player p) {
        if (this.canBuy == true && p.getMoney() >= cost) {
            p.setMoney(p.getMoney() - cost);
            this.setOwner(p);
            this.setCanBuy(false);
            return true;
        }
        return false;
    }

    public boolean getCanBuy() {
        return canBuy;
    }

    public void setCanBuy(boolean canBuy) {
        this.canBuy = canBuy;
    }

    public String getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public static int getTileSize() {
        return TILE_SIZE;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
