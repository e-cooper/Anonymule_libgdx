package com.cs2340.anonymule.Tile;

public class TownTile extends Tile {

    public TownTile(int row, int col, String type) {
        super(row, col, type);
        super.setCanBuy(false);
    }

}

