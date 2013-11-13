package com.cs2340.anonymule.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Timer;
import com.cs2340.anonymule.Anonymule;
import com.cs2340.anonymule.Map;
import com.cs2340.anonymule.Tile.Tile;
import java.util.Random;


public class GameInputProcessor implements InputProcessor {

	Random random = new Random();
	int randomEvents = random.nextInt(50) + 1;
    Map map;
    

    /**
     *
     * @param anonymule base anonymule game
     */
    public GameInputProcessor(Anonymule anonymule){
        map = anonymule.getMap();
    }

    /**
     *
     * @param keycode  key that is pressed
     * @return   always false
     */
    @Override
    public boolean keyDown(int keycode) {
    	// Handle random Events after every keypress
    	map.determineLowest();
    	
    	System.out.println("Random number: " + randomEvents);
    	
    	//System.out.println("Player properties:" + (map.getCurrentPlayer().getPropertyList()).get(index));
    	printInventory();
        if(keycode == Input.Keys.ESCAPE){
            Gdx.app.exit();
        }
        //if space, then let player get the property
        if(keycode == Input.Keys.Q){
        	//map.nextTurn();
        }
        if(keycode == Input.Keys.SPACE){
        	handleRandomEvents();
            switch (map.currentMode){
                case InitialLandGrab:
                    if(map.getCurrentTile().getCanBuy()){
                        map.getCurrentTile().setOwner(map.getCurrentPlayer());
                        map.getCurrentTile().setCanBuy(false);
                        map.getCurrentPlayer().getPropertyList().add(map.getCurrentTile());
                    }else{
                        map.decrementTurn();
                    }
                    Timer.instance.clear();
                    map.nextTurn();
                    break;
                case Auction:
                    break;
                case Store:
                    break;
                case Pub:
                    break;
                case MuleLand:
                    break;
            }

        }
        //depending on what mode the game is in, the keys correlate to different acttoins
        switch (map.currentMode){
            case InitialLandGrab:
                break;
            case Auction:
                break;
            case Store:
                break;
            case Pub:
                break;
            case MuleLand:
                if(map.getCurrentPlayer().getX()>343 && map.getCurrentPlayer().getX()<399 && map.getCurrentPlayer().getY() > 403
                        &&map.getCurrentPlayer().getY()<453){

                    map.currentMode = Map.GameMode.Town;
                    map.mapToTown();

                }
                break;
            case Town:
                if(map.getCurrentPlayer().getX()>435 && map.getCurrentPlayer().getX()<475 && map.getCurrentPlayer().getY() > 353
                        &&map.getCurrentPlayer().getY()<391)
                    map.gamble();
                if(map.getCurrentPlayer().getX()>153 && map.getCurrentPlayer().getX()<203 && map.getCurrentPlayer().getY() > 344
                        &&map.getCurrentPlayer().getY()<384)
                    map.enterStore();

                break;
        }

        if(keycode == Input.Keys.DPAD_UP){
        	handleRandomEvents();
            map.getCurrentPlayer().setY(map.getCurrentPlayer().getY()+10);
        }
        if(keycode == Input.Keys.DPAD_DOWN){
        	handleRandomEvents();
            map.getCurrentPlayer().setY(map.getCurrentPlayer().getY()-10);
        }
        if(keycode == Input.Keys.DPAD_LEFT){
        	handleRandomEvents();
            map.getCurrentPlayer().setX(map.getCurrentPlayer().getX()-10);
        }
        if(keycode == Input.Keys.DPAD_RIGHT){
        	handleRandomEvents();
            map.getCurrentPlayer().setX(map.getCurrentPlayer().getX()+10);
        }

        /**
         *  allow player to buy and install mule on the land
         */

        if(keycode == Input.Keys.F){
            int x = map.getCurrentPlayer().getX();
            int y = map.getCurrentPlayer().getY();

            for (Tile t: map.getCurrentPlayer().getPropertyList()){
                if(x-t.getX() < 56 && t.getY()-y < 51){
                    if(!t.isMule()){
                        t.setMule(1);
                        map.getCurrentPlayer().addFoodPlant();
                        map.setRandomEventsStatus("You just planted a food mule!");

                    }
                }

            }
            map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()-5);
        }
        if(keycode == Input.Keys.E){
            int x = map.getCurrentPlayer().getX();
            int y = map.getCurrentPlayer().getY();

            for (Tile t: map.getCurrentPlayer().getPropertyList()){
                if(x-t.getX() < 56 && t.getY()-y < 51){
                    if(!t.isMule()){
                        t.setMule(2);
                        map.getCurrentPlayer().addEnergyPlant();
                        map.setRandomEventsStatus("You just planted an energy mule");

                    }
                }

            }
            map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()-5);
        }
        if(keycode == Input.Keys.S){
            int x = map.getCurrentPlayer().getX();
            int y = map.getCurrentPlayer().getY();

            for (Tile t: map.getCurrentPlayer().getPropertyList()){
                if(x-t.getX() < 56 && t.getY()-y < 51){
                    if(!t.isMule()){
                        t.setMule(3);
                        map.getCurrentPlayer().addSmithorePlant();
                        map.setRandomEventsStatus("You just planted a smithore mule");

                    }
                }

            }
            map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()-5);
        }
        /*
        if(keycode == Input.Keys.E){
            int x = map.getCurrentPlayer().getX();
            int y = map.getCurrentPlayer().getY();

            for (Tile t: map.getCurrentPlayer().getPropertyList()){
                if(t.getX()+x < t.getX()+56 && t.getY()-y > t.getY()-51){

                    if(!t.isMule()){
                        t.setMule(2);
                        map.getCurrentPlayer().addEnergyPlant();
                    }

                }

            }
            map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()-5);
        }
        /**
         *  allow player to buy and install mule on the land
        */
        /*
        if(keycode == Input.Keys.S){
            int x = map.getCurrentPlayer().getX();
            int y = map.getCurrentPlayer().getY();

            for (Tile t: map.getCurrentPlayer().getPropertyList()){
                if(t.getX()+x < t.getX()+56 && t.getY()-y > t.getY()-51){

                    if(!t.isMule()){
                        t.setMule(3);
                        map.getCurrentPlayer().addSmithorePlant();
                    }
                }

            }
            map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()-5);
        }*/

        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Implemented for debugging purposes: prints the inventory onto the console
     */
    private void printInventory() {
		// TODO Auto-generated method stub
		System.out.println("Money: " + map.getCurrentPlayer().getMoney());
		System.out.println("Energy: " + map.getCurrentPlayer().getEnergy());
		System.out.println("Food: " + map.getCurrentPlayer().getFood());
		System.out.println("Smithore: " + map.getCurrentPlayer().getSmithore());

		System.out.println("Energy Plants: " + map.getCurrentPlayer().getEnergyPlant());
		System.out.println("Food Plants: " + map.getCurrentPlayer().getFoodPlant());
		System.out.println("Smithore Plants: " + map.getCurrentPlayer().getSmithorePlant());
		System.out.println();
	}

	/**
     *  Handles the random events that happen to a player
     */
    private void handleRandomEvents() {
    	randomEvents = random.nextInt(50) + 1;

		// TODO Auto-generated method stub
    	if(randomEvents >= 1 && randomEvents <=5){
			map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()+10);
            map.setRandomEventsStatus("Random Event: Player finds $10!");

		}
    	else if(randomEvents >= 6 && randomEvents <=10 && 
    			(!map.getCurrentPlayer().isLowest()) && 
    			map.getCurrentPlayer().getMoney() > 0){
			map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()-10);
            map.setRandomEventsStatus("Random Event: Player loses $10 :(");
		}
    	
    	else if(randomEvents >= 19 && randomEvents <=22 && 
    			(!map.getCurrentPlayer().isLowest()) && 
    			map.getCurrentPlayer().getFood() > 0){
			map.getCurrentPlayer().addFood(-1);
            map.setRandomEventsStatus("Random Event: Player loses a food resource :(");
    	}
    	else if(randomEvents >= 23 && randomEvents <=25 && 
    			(!map.getCurrentPlayer().isLowest()) && 
			map.getCurrentPlayer().getSmithore() > 0){
            map.setRandomEventsStatus("Random Event: Player loses a smithore resource :(");

    	}
    	else if(randomEvents >= 11 && randomEvents <=14){
			map.getCurrentPlayer().addEnergy(1);
            map.setRandomEventsStatus("Random Event: Player found an energy resource!!");

		}
    	else if(randomEvents >= 15 && randomEvents <=18){
			map.getCurrentPlayer().addFood(1);
			map.setRandomEventsStatus("Random Event: Player found a food resource!");
    }
    	else if(randomEvents >= 10 && randomEvents <=13){
			map.getCurrentPlayer().addSmithore(1);
			map.setRandomEventsStatus("Random Event: Player found a smithore resource!");

    	}
    }
	@Override
    public boolean keyUp(int keycode) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean keyTyped(char character) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean scrolled(int amount) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
