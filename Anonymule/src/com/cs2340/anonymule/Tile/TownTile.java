<<<<<<< HEAD
package com.cs2340.anonymule.Tile;

public class TownTile extends Tile {

    public TownTile(int x, int y) {
        super(x, y);
        super.setCanBuy(false);
    }

}

=======
package com.cs2340.anonymule.Tile;

public class TownTile extends Tile {

    public TownTile(int row, int col, String type) {
        super(row, col, type);
        super.setCanBuy(false);
    }

}

>>>>>>> 59eaac163cf654d68b8f9b6308a47f1d67451d90
