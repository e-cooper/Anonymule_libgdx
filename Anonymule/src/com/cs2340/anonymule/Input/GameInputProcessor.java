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

    public GameInputProcessor(Anonymule anonymule){
        map = anonymule.getMap();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.ESCAPE){
            Gdx.app.exit();
        }
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
