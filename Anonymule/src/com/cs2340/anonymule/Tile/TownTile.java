package com.cs2340.anonymule.Tile;

public class TownTile extends Tile {
	
	/**
	 * Constructor which sets up the TownTile object
	 * @param x The x-coordinate of the tile
	 * @param y The y-coordinate of the tile
	 */

    public TownTile(int x, int y) {
        super(x, y);
        super.setCanBuy(false);
    }

}

