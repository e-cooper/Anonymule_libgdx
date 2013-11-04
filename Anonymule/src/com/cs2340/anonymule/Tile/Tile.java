package com.cs2340.anonymule.Tile;

import com.badlogic.gdx.graphics.Color;
import com.cs2340.anonymule.Player;

//import java.awt.Color;
//import java.awt.Graphics;

public class Tile {
    private static final int TILE_SIZE = 85;
    private int x, y;
    private boolean canBuy = true;
    private String type;
    private int cost = 300;
    private Player owner = null;
    private Color color;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;

    }

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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

}
