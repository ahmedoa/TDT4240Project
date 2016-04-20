package com.g4.progark.battleships.utility;

import android.content.res.Resources;
import android.graphics.Canvas;

import com.g4.progark.battleships.draw_classes.GameView;
import com.g4.progark.battleships.models.GameTile;
import com.g4.progark.battleships.models.Ship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ahmed on 03.04.2016.
 */
public class Constants {


    //The dimensions of the entire game windows
    public static final float SCREEN_WIDTH = 500;
    public static final float SCREEN_HEIGHT = 800;

    public static final float DISTANCE_BETWEEN_GRIDS = 10;

    //public static final int SHIP_GRID_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    // public static final int SHIP_GRID_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;

    //the dimensions of the grid where the player chooses the position to strike
    public static final float SHIP_GRID_WIDTH = 300;
    public static final float SHIP_GRID_HEIGHT = 300;


    //the dimensions of the grid where the player sees which of his ships have been struck
    public static final float STRIKE_GRID_WIDTH = 400;
    public static final float STRIKE_GRID_HEIGHT = 400;


    public static final float SHIP_GRID_BORDER = 0;


    public static final float STRIKE_GRID_BORDER = 0;




    //public static String CURRENT_MAP_NAME;

    public static final float TILE_BORDER_WIDTH = 1;

    public static int CURRENT_PLAYER = 1;





    public static int NUMBER_ROW_TILES = 10;
    public static int NUMBER_COLUMN_TILES = 10;


    //Roughly 60% of the tiles can be used for ship placement.
    public static int NUMBER_SHIP_TILES_PLAYER1 =  10;
    public static int NUMBER_SHIP_TILES_PLAYER2 =  10;

    public static HashMap<Coordinate, GameTile> SHIP_TILES1 = null;
    public static HashMap<Coordinate, GameTile> STRIKE_TILES1 = null;


    public static HashMap<Coordinate, GameTile> SHIP_TILES2 = null;
    public static HashMap<Coordinate, GameTile> STRIKE_TILES2 = null;


    public static Ship CURRENT_SHIP_SELECTED = null;

    public static List<Ship> PLAYER1_SHIPS = new ArrayList<Ship>();
    public static List<Ship> PLAYER2_SHIPS = new ArrayList<Ship>();


}
