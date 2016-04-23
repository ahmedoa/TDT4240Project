package com.g4.progark.battleships.draw_classes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.g4.progark.battleships.controllers.menus.IntermediateActivity;
import com.g4.progark.battleships.models.Drawable;
import com.g4.progark.battleships.models.EmptyStruckTile;
import com.g4.progark.battleships.models.GameMap;
import com.g4.progark.battleships.models.GameTile;
import com.g4.progark.battleships.utility.Constants;
import com.g4.progark.battleships.utility.Coordinate;

/**
 * Created by ahmed on 31.03.2016.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback{


    private GameMap map;
    private SurfaceHolder holder;





    //the grid which displays the ships of the active player and where the enemy has struck
    private GridView current_ship_grid;


    //the Grid which displaying the active player's strikes
    private GridView current_strike_grid;


    private GridView opponent_ship_grid;


    private Thread gameThread;

    public GameView(Context context,String map_name, GridView current_ship_grid,  GridView current_strike_grid, GridView opponent_ship_grid) {
        super(context);
        this.holder = getHolder();
        this.holder.addCallback(this);
        this.current_ship_grid = current_ship_grid;
        this.current_strike_grid = current_strike_grid;
        this.opponent_ship_grid = opponent_ship_grid;
        this.map = new GameMap(context, map_name);

    }

    

    public void strikeEnemyShipGrid(Drawable newState, float x, float y){

        Coordinate c = GridView.getCoordinateKeyForStrike(current_strike_grid.getTop_left(),x,y);

        opponent_ship_grid.getTiles().get(c).setGameTileState(newState);


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


    /*
    public void drawNewState(float x, float y){
        GameTile tile = currentGridView.getTile(x, y);

        //tile.setTileState(TileState.EMPTY_STRUCK_TILE);
        tile.setGameTileState(new EmptyStruckTile());

        drawGameView();

    }
    */

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

        if(!isWithinPositionInStrikeGrid(event.getX(), event.getY())){
            return false;
        }

        if(event.getAction() == MotionEvent.ACTION_DOWN) {

            GameTile tile = current_strike_grid.getTile(event.getX(), event.getY());

            //tile.setTileState(TileState.EMPTY_STRUCK_TILE);
            tile.setGameTileState(new EmptyStruckTile());

            strikeEnemyShipGrid(new EmptyStruckTile(), event.getX(), event.getY());





            drawGameView();

            switchTurns();


            Intent intent = new Intent(getContext(), IntermediateActivity.class);

            getContext().startActivity(intent);

            return true;
        } else {
            return false;
        }
    }




    public void drawGameView(){



        Canvas canvas = holder.lockCanvas();
        //canvas.draw



        if(canvas != null){

            canvas.drawColor(Color.WHITE);
            //canvas.drawCircle(50, 50, 10, null);


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
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

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
