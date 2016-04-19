package com.g4.progark.battleships.controllers.menus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.g4.progark.battleships.draw_classes.GridView;
import com.g4.progark.battleships.models.GameMap;
import com.g4.progark.battleships.models.GameTile;
import com.g4.progark.battleships.models.Orientation;
import com.g4.progark.battleships.models.Ship;
import com.g4.progark.battleships.models.ShipTile;
import com.g4.progark.battleships.utility.Constants;
import com.g4.progark.battleships.utility.Coordinate;

/**
 * Created by ahmed on 15.04.2016.
 */
public class ShipPlacementView extends SurfaceView implements SurfaceHolder.Callback {


    private GridView currentShipGrid;

    private GameMap map;

    private Thread gridThread;

    private Ship currentShip;

    SurfaceHolder holder;


    public ShipPlacementView(Context context,String map_name, GridView current_ship_grid){
        super(context);
        this.currentShipGrid = current_ship_grid;
        map = new GameMap(context, map_name);
        currentShip = Constants.CURRENT_SHIP_SELECTED;
        this.holder = getHolder();
        this.holder.addCallback(this);

    }

    public boolean isPlaceable(float x, float y ){

        Coordinate c = currentShipGrid.convertToTileCoordinate(x,y);



        for (int i = 0; i < currentShip.getTile_length() ; i++) {


            if (currentShip.getOrientation() == Orientation.LEFT) {

               GameTile gt = currentShipGrid.getTile2(c.getX() - i, c.getY());

                if(gt == null || gt.getGameTileState() instanceof ShipTile){
                    return false;
                }

            } else if (currentShip.getOrientation() == Orientation.RIGHT) {

                GameTile gt = currentShipGrid.getTile2(c.getX() + i, c.getY());

                if(gt == null || gt.getGameTileState() instanceof ShipTile){
                    return false;
                }

            } else if (currentShip.getOrientation() == Orientation.UP) {

                GameTile gt = currentShipGrid.getTile2(c.getX(), c.getY() - i);

                if(gt == null || gt.getGameTileState() instanceof ShipTile){
                    return false;
                }

            } else if (currentShip.getOrientation() == Orientation.DOWN) {

               GameTile gt = currentShipGrid.getTile2(c.getX(), c.getY() + i);

                if(gt == null || gt.getGameTileState() instanceof ShipTile){
                    return false;
                }

            }

        }

        return true;



    }

    public void placeShip(float x, float y){

        Coordinate c = currentShipGrid.convertToTileCoordinate(x,y);



        for (int i = 0; i < currentShip.getTile_length() ; i++) {


            if (currentShip.getOrientation() == Orientation.LEFT) {

                currentShipGrid.getTile2(c.getX() - i, c.getY()).setGameTileState(new ShipTile());



            } else if (currentShip.getOrientation() == Orientation.RIGHT) {

                currentShipGrid.getTile2(c.getX() + i, c.getY()).setGameTileState(new ShipTile());


            } else if (currentShip.getOrientation() == Orientation.UP) {

                int newx = (int)c.getX();
                int newy = (int)c.getY()-i;

                //currentShipGrid.getTile(c.getX(), c.getY() - i).setGameTileState(new ShipTile());

                GameTile gt = currentShipGrid.getTile2(c.getX(), c.getY() - i);
                gt.setGameTileState(new ShipTile());


            } else if (currentShip.getOrientation() == Orientation.DOWN) {

                currentShipGrid.getTile2(c.getX(), c.getY() + i).setGameTileState(new ShipTile());


            }

        }

        if(Constants.CURRENT_PLAYER == 1){
            Constants.SHIP_TILES1 = currentShipGrid.getTiles();
        } else {
            Constants.SHIP_TILES2 = currentShipGrid.getTiles();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN) {


            if (isPlaceable(event.getX(), event.getY())) {


                placeShip(event.getX(), event.getY());
                drawShipGrid();

                Intent intent = new Intent(getContext(), ShipSelectionActivity.class);
                getContext().startActivity(intent);
                return true;

            } else {

                Toast.makeText(getContext(), "Cannot start drawing ship from there", Toast.LENGTH_SHORT).show();
                return false;
            }

        } else {
            return false;
        }


    }

    public void drawShipGrid(){


        Canvas canvas = holder.lockCanvas();

        if(canvas != null){

            canvas.drawColor(Color.WHITE);
            //canvas.drawCircle(50, 50, 10, null);

            canvas.drawBitmap(map.getArea(), 0, 0, null);

            currentShipGrid.draw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        gridThread = new Thread(){
            @Override
            public void run() {

                drawShipGrid();
            }
        };

        gridThread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        if(gridThread != null) {
            try {
                gridThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
