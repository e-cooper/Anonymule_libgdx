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
	
	/**
	 * Makes sure the calculate production method is working correctly on each new turn
	 */

	@Test
	public void testCalculateProduction() {
		//make sure the current player starts out with no food, energy, or food plants
		Assert.assertEquals(0, map.getCurrentPlayer().getEnergy());
		Assert.assertEquals(0, map.getCurrentPlayer().getFood());
		Assert.assertEquals(0, map.getCurrentPlayer().getFoodPlant());
		
		//give the first player some energy and a food plant so they can make food
		map.getCurrentPlayer().addEnergy(10);
		map.getCurrentPlayer().addFoodPlant();
		
		//make sure the values for that player changed
		Assert.assertEquals(10, map.getCurrentPlayer().getEnergy());
		Assert.assertEquals(0, map.getCurrentPlayer().getFood());
		Assert.assertEquals(1, map.getCurrentPlayer().getFoodPlant());
		
		//this won't calculate production, but it will increment the turn
		map.startGame();
		
		//make sure player 2 doesn't have anything either
		Assert.assertEquals(0, map.getCurrentPlayer().getEnergy());
		Assert.assertEquals(0, map.getCurrentPlayer().getFood());
		Assert.assertEquals(0, map.getCurrentPlayer().getFoodPlant());
		
		//give player 2 a way to create energy
		map.getCurrentPlayer().addEnergy(13);
		map.getCurrentPlayer().addEnergyPlant();
		
		//make sure they got the items
		Assert.assertEquals(13, map.getCurrentPlayer().getEnergy());
		Assert.assertEquals(1, map.getCurrentPlayer().getEnergyPlant());
		
		map.nextTurn();
		
		//make sure player 3 doesn't have anything
		Assert.assertEquals(0, map.getCurrentPlayer().getEnergy());
		Assert.assertEquals(0, map.getCurrentPlayer().getFood());
		Assert.assertEquals(0, map.getCurrentPlayer().getFoodPlant());
		Assert.assertEquals(0, map.getCurrentPlayer().getEnergyPlant());
		
		map.nextTurn();
		
		//see if player 1's production was calculated correctly
		Assert.assertEquals(7, map.getCurrentPlayer().getEnergy());            
		Assert.assertEquals(3, map.getCurrentPlayer().getFood());
		Assert.assertEquals(1, map.getCurrentPlayer().getFoodPlant());
		
		map.nextTurn();
		
		//see if player 2's production was calculated correctly
		Assert.assertEquals(16, map.getCurrentPlayer().getEnergy());
		Assert.assertEquals(1, map.getCurrentPlayer().getEnergyPlant());
	}
	
	/**
	 * Make sure buying tiles is working correctly
	 */

	@Test
	public void testBuyTile() {
		//make sure the tile can be bought by player 1
		Assert.assertTrue(map.getCurrentTile().buyTile(map.getCurrentPlayer()));
		
		//buy it and assign it to player 1
		map.getCurrentTile().buyTile(map.getCurrentPlayer());
		map.getCurrentPlayer().getPropertyList().add(map.getCurrentTile());
		
		//make sure they have it
		Assert.assertEquals(map.getCurrentTile(), map.getCurrentPlayer().getPropertyList().get(0));
		
		map.nextTurn();
		
		//make sure the tile cannot be bought by player 2
		Assert.assertFalse(map.getCurrentTile().buyTile(map.getCurrentPlayer()));
	}

	@Test
	public void testTurns() {

		for(int i = 0; i < 7; i++){
            map.addPlayer(new Player());
        }
		
        //check if game modes are changed correctly
        //10 players
        for(int i = 0; i <  map.getPlayerList().size(); i++){
            Assert.assertEquals(Map.GameMode.InitialLandGrab, map.currentMode);
            map.nextTurn();
        }
        
        //check to see if we looped back to first player
        Assert.assertEquals(map.getCurrentPlayer(), map.getPlayerList().get(0));

        //check to see if the game mode is changing to the new one once land grab is finished
        for(int i = 0; i <= map.getPlayerList().size(); i++){
            map.nextTurn();
        }
        
        Assert.assertEquals(Map.GameMode.MuleLand, map.currentMode);
	}
	
	/**
	 * Makes sure the correct player is found to have the lowest money amount
	 */

	@Test
	public void testDetermineLowest() {
		ArrayList<Player> players = map.getPlayerList();
        players.get(0).setMoney(200);
        players.get(1).setMoney(199);
        map.determineLowest();
        Assert.assertFalse(players.get(0).isLowest());
        Assert.assertTrue(players.get(1).isLowest());

        //3 players
        players.get(0).setMoney(199);
        players.get(1).setMoney(200);
        map.determineLowest();
        Assert.assertTrue(players.get(0).isLowest());
        Assert.assertFalse(players.get(1).isLowest());

        players.get(2).setMoney(201);
        map.determineLowest();
        Assert.assertTrue(players.get(0).isLowest());

        players.get(0).setMoney(250);
        players.get(1).setMoney(199);
        players.get(2).setMoney(201);
        map.determineLowest();
        Assert.assertFalse(players.get(0).isLowest());
        Assert.assertTrue(players.get(1).isLowest());
        Assert.assertFalse(players.get(2).isLowest());

        //4 players
        map.addPlayer(new Player());

        players.get(0).setMoney(199);
        players.get(1).setMoney(200);
        players.get(2).setMoney(201);
        players.get(3).setMoney(202);
        map.determineLowest();
        Assert.assertTrue(players.get(0).isLowest());
        Assert.assertFalse(players.get(1).isLowest());
        Assert.assertFalse(players.get(2).isLowest());
        Assert.assertFalse(players.get(3).isLowest());

        players.get(0).setMoney(199);
        players.get(1).setMoney(198);
        players.get(2).setMoney(201);
        players.get(3).setMoney(202);
        map.determineLowest();
        Assert.assertFalse(players.get(0).isLowest());
        Assert.assertTrue(players.get(1).isLowest());
        Assert.assertFalse(players.get(2).isLowest());
        Assert.assertFalse(players.get(3).isLowest());

        players.get(0).setMoney(199);
        players.get(1).setMoney(200);
        players.get(2).setMoney(198);
        players.get(3).setMoney(202);
        map.determineLowest();
        Assert.assertFalse(players.get(0).isLowest());
        Assert.assertFalse(players.get(1).isLowest());
        Assert.assertTrue(players.get(2).isLowest());
        Assert.assertFalse(players.get(3).isLowest());

        players.get(0).setMoney(199);
        players.get(1).setMoney(200);
        players.get(2).setMoney(201);
        players.get(3).setMoney(198);
        map.determineLowest();
        Assert.assertFalse(players.get(0).isLowest());
        Assert.assertFalse(players.get(1).isLowest());
        Assert.assertFalse(players.get(2).isLowest());
        Assert.assertTrue(players.get(3).isLowest());
	}
 
}