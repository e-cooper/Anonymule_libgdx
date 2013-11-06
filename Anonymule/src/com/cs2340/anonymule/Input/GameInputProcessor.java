package com.cs2340.anonymule.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Timer;
import com.cs2340.anonymule.Anonymule;
import com.cs2340.anonymule.Map;
import com.cs2340.anonymule.Tile.Tile;


public class GameInputProcessor implements InputProcessor {


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
        if(keycode == Input.Keys.ESCAPE){
            Gdx.app.exit();
        }
        //if space, then let player get the property
        if(keycode == Input.Keys.SPACE){
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
            map.getCurrentPlayer().setY(map.getCurrentPlayer().getY()+10);
        }
        if(keycode == Input.Keys.DPAD_DOWN){
            map.getCurrentPlayer().setY(map.getCurrentPlayer().getY()-10);
        }
        if(keycode == Input.Keys.DPAD_LEFT){
            map.getCurrentPlayer().setX(map.getCurrentPlayer().getX()-10);
        }
        if(keycode == Input.Keys.DPAD_RIGHT){
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
                    if(!t.isMule())
                        t.setMule(1);

                }

            }
            map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()-5);
        }
        if(keycode == Input.Keys.E){
            int x = map.getCurrentPlayer().getX();
            int y = map.getCurrentPlayer().getY();

            for (Tile t: map.getCurrentPlayer().getPropertyList()){
                if(t.getX()+x < t.getX()+56 && t.getY()-y > t.getY()-51){

                    if(!t.isMule())
                        t.setMule(2);

                }

            }
            map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()-5);
        }
        /**
         *  allow player to buy and install mule on the land
         */
        if(keycode == Input.Keys.S){
            int x = map.getCurrentPlayer().getX();
            int y = map.getCurrentPlayer().getY();

            for (Tile t: map.getCurrentPlayer().getPropertyList()){
                if(t.getX()+x < t.getX()+56 && t.getY()-y > t.getY()-51){

                    if(!t.isMule())
                        t.setMule(3);

                }

            }
            map.getCurrentPlayer().setMoney(map.getCurrentPlayer().getMoney()-5);
        }

        return false;  //To change body of implemented methods use File | Settings | File Templates.
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
