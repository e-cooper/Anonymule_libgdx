package com.cs2340.anonymule.Test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cs2340.anonymule.Map;
import com.cs2340.anonymule.Player;

public class Tests {
	
	private Player player;
	private Map map;
	
	@Before
	public void before() {
		player = new Player();
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
	public void testPlayer() {
		Assert.assertEquals(0, player.getEnergyPlant());
	}
	
	@Test
	public void testMap() {
		System.out.println(map.getCurrentPlayer().getFood());
		System.out.println(map.getCurrentPlayer().getEnergy());
		map.getCurrentPlayer().addEnergy(10);
		map.getCurrentPlayer().addFoodPlant();
		map.startGame();
		map.nextTurn();
		map.nextTurn();
		
		Assert.assertEquals(7, map.getCurrentPlayer().getEnergy());		
		Assert.assertEquals(3, map.getCurrentPlayer().getFood());
	}

}
