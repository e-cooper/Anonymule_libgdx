package com.cs2340.anonymule.Test;
 
import java.util.ArrayList;
 
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
 
import com.cs2340.anonymule.Map;
import com.cs2340.anonymule.Player;
 
public class Tests {
       
        private Map map;
       
        @Before
        public void before() {
                ArrayList<Player> arr = new ArrayList<Player>();
                for (int i = 0; i < 3; i++) {
                        arr.add(new Player());
                }
                map = new Map(new ArrayList<Player>(), 1, 1, null);
                for (Player p: arr) {
                        map.addPlayer(p);
                }
        }
 
        @Test
        public void testMap() {
                map.getCurrentPlayer().addEnergy(10);
                map.getCurrentPlayer().addFoodPlant();
                map.startGame();
                map.nextTurn();
                map.nextTurn();
               
                Assert.assertEquals(7, map.getCurrentPlayer().getEnergy());            
                Assert.assertEquals(3, map.getCurrentPlayer().getFood());
        }
 
	    @Test
	    public void testBuyTile() {
	        map.getCurrentTile().buyTile(map.getCurrentPlayer());
	        map.nextTurn();
	        Assert.assertFalse(map.getCurrentTile().buyTile(map.getCurrentPlayer()));
	    }
	 
	    @Test
	    public void testTurns() {
	 
	        for(int i = 0; i < 97; i++){
	            map.addPlayer(new Player());
	        }
	 
	        //100 players
	        for(int i = 0; i < 100; i++){
	            map.nextTurn();
	        }
	 
	        Assert.assertEquals(map.getCurrentPlayer(), map.getPlayerList().get(0));
	    }
	 
	    @Test
	    public void testDetermineLowest() {
	        ArrayList<Player> players = map.getPlayerList();
	        players.get(0).setMoney(200);
	        players.get(1).setMoney(199);
	        players.get(2).setMoney(201);
	        map.determineLowest();
	        Assert.assertTrue(players.get(1).isLowest());
	    }
 
}