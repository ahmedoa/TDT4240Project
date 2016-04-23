package com.g4.progark.battleships.draw_classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.g4.progark.battleships.controllers.AndroidMenu;
import com.g4.progark.battleships.controllers.menus.FirepowerActivity;
import com.g4.progark.battleships.controllers.menus.GameViewActivity;
import com.g4.progark.battleships.controllers.menus.IntermediateActivity;
//import com.g4.progark.battleships.controllers.menus.Lobby;
import com.g4.progark.battleships.controllers.menus.MainActivity;
import com.g4.progark.battleships.controllers.menus.WinningActivity;
import com.g4.progark.battleships.models.AirStrikeFirepower;
import com.g4.progark.battleships.models.ClusterBombFirepower;
import com.g4.progark.battleships.models.Drawable;
import com.g4.progark.battleships.models.EmptyStruckTile;
import com.g4.progark.battleships.models.EmptyTile;
import com.g4.progark.battleships.models.Firepower;
import com.g4.progark.battleships.models.FirepowerFactory;
import com.g4.progark.battleships.models.GameMap;
import com.g4.progark.battleships.models.GameMode;
import com.g4.progark.battleships.models.GameTile;
import com.g4.progark.battleships.models.NormalFirepower;
import com.g4.progark.battleships.models.OneStrikeDownMode;
import com.g4.progark.battleships.models.Ship;
import com.g4.progark.battleships.models.ShipStruckTile;
import com.g4.progark.battleships.models.ShipSunkenTile;
import com.g4.progark.battleships.models.StandardGameMode;
import com.g4.progark.battleships.models.TileState;
import com.g4.progark.battleships.utility.Constants;
import com.g4.progark.battleships.utility.Coordinate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ahmed on 31.03.2016.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private GameMap map;
    private SurfaceHolder holder;
    private GameMode chosenGameMode;

    private ArrayList<Coordinate> shipCoordinates;
    private Firepower currentFirepower;
    private FirepowerFactory firePowerFactory = new FirepowerFactory();
    private ArrayList<Coordinate> coordinates;
    private ArrayList<Coordinate> tilesToStrike;
    private boolean gotWinner = false;

    //the grid which displays the ships of the active player and where the enemy has struck
    private GridView current_ship_grid;

    //the Grid which displaying the active player's strikes
    private GridView current_strike_grid;
    private GridView opponent_ship_grid;

    private Thread gameThread;

    public GameView(Context context, GridView current_ship_grid, GridView current_strike_grid,
                    GridView opponent_ship_grid, int currentFirepowerInt) {
        super(context);
        this.holder = getHolder();
        this.holder.addCallback(this);
        this.current_ship_grid = current_ship_grid;
        this.current_strike_grid = current_strike_grid;
        this.opponent_ship_grid = opponent_ship_grid;
        this.map = new GameMap(context, Constants.chosenGameMap);
        Constants.currentFirePower = currentFirepowerInt;
        currentFirepower = firePowerFactory.setFirePower(0);
        //chosenGameMode = setGameMode(chosenGameModeInt);

    }
    public GameMode setGameMode() {

        switch (Constants.chosenGameMode) {
            case 0:
                return new StandardGameMode();
            case 1:
                return new OneStrikeDownMode();
            case 2:
                return new StandardGameMode();
            default:
                return null;
        }
    }

    public void strikeEnemyShipGrid(Drawable newState, float x, float y){

        Coordinate c = GridView.getCoordinateKeyForStrike(current_strike_grid.getTop_left(),x,y);
        opponent_ship_grid.getTiles().get(c).setGameTileState(newState);

    }

    public void getFirepower() {

        Intent intent = new Intent(getContext(), FirepowerActivity.class);
        getContext().startActivity(intent);

    }

    public void switchTurns(){

        if(Constants.CURRENT_PLAYER == 1){

            Constants.SHIP_TILES1 = current_ship_grid.getTiles();
            Constants.STRIKE_TILES1 = current_strike_grid.getTiles();
            Constants.SHIP_TILES2 = opponent_ship_grid.getTiles();
            Constants.CURRENT_PLAYER = 2;

        } else {

            Constants.SHIP_TILES2 = current_ship_grid.getTiles();
            Constants.STRIKE_TILES2 = current_strike_grid.getTiles();
            Constants.SHIP_TILES1 = opponent_ship_grid.getTiles();
            Constants.CURRENT_PLAYER = 1;

        }
    }

    public void setCurrent_ship_grid(GridView current_ship_grid) {
        this.current_ship_grid = current_ship_grid;
    }

    public void setCurrent_strike_grid(GridView current_strike_grid) {
        this.current_strike_grid = current_strike_grid;
    }

    public boolean isWithinPositionInStrikeGrid(float x, float y){

        float tl_x = current_strike_grid.getTop_left().getX();
        float tl_y = current_strike_grid.getTop_left().getY();

        return x >= tl_x && x <= tl_x + Constants.STRIKE_GRID_WIDTH && y >= tl_y && y <= tl_y + Constants.STRIKE_GRID_HEIGHT;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //drawGameView();
        currentFirepower = firePowerFactory.setFirePower(Constants.currentFirePower);
        if((Constants.chosenGameMode == 2) && !isWithinPositionInStrikeGrid(event.getX(), event.getY())){
            if((Constants.currentFirePower == 0))
                getFirepower();
            return false;
        }

        chosenGameMode = setGameMode();
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

            float tileWidth = current_strike_grid.getWidth();
            float tileHeight = current_strike_grid.getHeight();
            float xStartGrid = current_strike_grid.getTop_left().getX();
            float yStartGrid = current_strike_grid.getTop_left().getY();

            coordinates = currentFirepower.fire(event.getX(), event.getY(), tileWidth, tileHeight,
                    xStartGrid, yStartGrid);
            if(checkIfSelectedTilesAreEmpty(coordinates) == false)
                return false;
            for(Coordinate coordinate : coordinates) {
                GameTile tile = current_strike_grid.getTile(coordinate.getX(), coordinate.getY());
                if(tile != null) {
                    if(tile.getGameTileState() instanceof EmptyTile){

                        if(Constants.CURRENT_PLAYER == 1) {
                            List<Ship> currentShips = Constants.PLAYER2_SHIPS;
                            for (Ship ship : currentShips) {

                                shipCoordinates = ship.getShip_coordinates();
                                for (Coordinate coordinates : shipCoordinates) {
                                    Coordinate c = current_strike_grid.convertToTileCoordinate(coordinate.getX(), coordinate.getY());
                                    if ((c.getX() == coordinates.getX()) && (c.getY() == coordinates.getY())) {
                                        tile.setGameTileState(new ShipStruckTile());
                                        strikeEnemyShipGrid(new ShipStruckTile(), coordinate.getX(),
                                                coordinate.getY());
                                        tilesToStrike = chosenGameMode.sinkShip(c, currentShips);

                                        for (Coordinate strikeCoordinate : tilesToStrike) {
                                            Coordinate coordinateConvertedBack = current_strike_grid.convertBack(strikeCoordinate.getX(),
                                                    strikeCoordinate.getY());
                                            GameTile tile2 = current_strike_grid.getTile(coordinateConvertedBack.getX(), coordinateConvertedBack.getY());

                                            tile2.setGameTileState(new ShipStruckTile());
                                            strikeEnemyShipGrid(new ShipStruckTile(), coordinateConvertedBack.getX(),
                                                    coordinateConvertedBack.getY());
                                        }
                                    }
                                }
                            }
                        }

                        else if(Constants.CURRENT_PLAYER == 2) {
                            List<Ship> currentShips = Constants.PLAYER1_SHIPS;
                            for (Ship ship : currentShips) {

                                shipCoordinates = ship.getShip_coordinates();
                                for (Coordinate coordinates : shipCoordinates) {
                                    Coordinate c = current_strike_grid.convertToTileCoordinate(coordinate.getX(), coordinate.getY());
                                    if ((c.getX() == coordinates.getX()) && (c.getY() == coordinates.getY())) {
                                        tile.setGameTileState(new ShipStruckTile());
                                        strikeEnemyShipGrid(new ShipStruckTile(), coordinate.getX(),
                                                coordinate.getY());
                                        tilesToStrike = chosenGameMode.sinkShip(c, currentShips);

                                        for (Coordinate strikeCoordinate : tilesToStrike) {
                                            Coordinate coordinateConvertedBack = current_strike_grid.convertBack(strikeCoordinate.getX(),
                                                    strikeCoordinate.getY());
                                            GameTile tile2 = current_strike_grid.getTile(coordinateConvertedBack.getX(), coordinateConvertedBack.getY());

                                            tile2.setGameTileState(new ShipStruckTile());
                                            strikeEnemyShipGrid(new ShipStruckTile(), coordinateConvertedBack.getX(),
                                                    coordinateConvertedBack.getY());
                                        }
                                    }
                                }
                            }
                        }
                        if(tile.getGameTileState() instanceof EmptyTile){
                            Log.d("will this run", "ok");
                            tile.setGameTileState(new EmptyStruckTile());
                            strikeEnemyShipGrid(new EmptyStruckTile(), coordinate.getX(), coordinate.getY());
                        } else {
                            Log.d("will this run===", "ok");
                            checkForSunkenShips();
                        }
                    }
                }
            }
            drawGameView();
            switchTurns();
            if(gotWinner == false) {
                Intent intent = new Intent(getContext(), IntermediateActivity.class);
                getContext().startActivity(intent);
            }

            return true;
        } else {
            return false;
        }
    }
    public boolean checkIfSelectedTilesAreEmpty(ArrayList<Coordinate> coordinates) {

        for (Coordinate coordinate : coordinates) {
            GameTile tile = current_strike_grid.getTile(coordinate.getX(), coordinate.getY());
            if (tile != null) {
                if (tile.getGameTileState() instanceof EmptyTile)
                    return true;
            }

        } return false;
    }

    public void startWinningScreen() {
        gotWinner = true;
        Intent winningIntent = new Intent(getContext(), WinningActivity.class);
        getContext().startActivity(winningIntent);
    }
    public void sinkShip(ArrayList<Coordinate> coordinates)
    {
        for (Coordinate coordinatesToStruck : coordinates) {

            Coordinate coordinateConvertedBack = current_strike_grid.convertBack(coordinatesToStruck.getX(),
                    coordinatesToStruck.getY());
            GameTile tile3 = current_strike_grid.getTile(coordinateConvertedBack.getX(), coordinateConvertedBack.getY());

            tile3.setGameTileState(new ShipSunkenTile());
            strikeEnemyShipGrid(new ShipSunkenTile(), coordinateConvertedBack.getX(),
                    coordinateConvertedBack.getY());


        }


    }
    public void checkForSunkenShips() {
        boolean isSunken;
        boolean hasWon = true;
        if(Constants.CURRENT_PLAYER == 1) {

            for(Ship ship : Constants.PLAYER2_SHIPS) {
                shipCoordinates = ship.getShip_coordinates();
                isSunken = true;
                for (Coordinate coordinates : shipCoordinates) {

                    Coordinate coordinateConvertedBack = current_strike_grid.convertBack(coordinates.getX(),
                            coordinates.getY());

                    if(!(current_strike_grid.getTile(coordinateConvertedBack.getX(), coordinateConvertedBack.getY()).getGameTileState()
                            instanceof ShipStruckTile)) {
                        isSunken = false;
                        if(!(current_strike_grid.getTile(coordinateConvertedBack.getX(), coordinateConvertedBack.getY()).getGameTileState()
                                instanceof ShipSunkenTile))
                            hasWon = false;
                        break;
                    }
                }
                if(isSunken == true)
                    sinkShip(shipCoordinates);
            }
            if(hasWon == true)
                startWinningScreen();
        }
        else if(Constants.CURRENT_PLAYER == 2) {

            for(Ship ship : Constants.PLAYER1_SHIPS) {
                shipCoordinates = ship.getShip_coordinates();
                isSunken = true;
                for (Coordinate coordinates : shipCoordinates) {

                    Coordinate coordinateConvertedBack = current_strike_grid.convertBack(coordinates.getX(),
                            coordinates.getY());

                    if(!(current_strike_grid.getTile(coordinateConvertedBack.getX(), coordinateConvertedBack.getY()).getGameTileState()
                            instanceof ShipStruckTile)) {
                        isSunken = false;
                        if(!(current_strike_grid.getTile(coordinateConvertedBack.getX(), coordinateConvertedBack.getY()).getGameTileState()
                                instanceof ShipSunkenTile))
                            hasWon = false;
                        break;
                    }
                }
                if(isSunken == true)
                    sinkShip(shipCoordinates);
            }
            if(hasWon == true)
                startWinningScreen();
        }
    }

    public void drawGameView(){

        Canvas canvas = holder.lockCanvas();

        if(canvas != null){

            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(map.getArea(), 0, 0, null);

            current_ship_grid.draw(canvas);
            current_strike_grid.draw(canvas);

            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        gameThread = new Thread(){
            @Override
            public void run() {

                drawGameView();
            }
        };

        gameThread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) { }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        if(gameThread != null) {
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}