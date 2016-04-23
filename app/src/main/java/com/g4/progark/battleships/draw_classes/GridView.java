package com.g4.progark.battleships.draw_classes;

import android.graphics.Canvas;

import com.g4.progark.battleships.models.Drawable;
import com.g4.progark.battleships.models.EmptyTile;
import com.g4.progark.battleships.models.GameTile;
import com.g4.progark.battleships.models.TileState;
import com.g4.progark.battleships.utility.Constants;
import com.g4.progark.battleships.utility.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ahmed on 04.04.2016.
 */
public class GridView{


    private final int num_cols;
    private final int num_rows;

    //dimensions of the tile including borders
    private final float tile_width;
    private final float tile_height;

    //the dimensions of the rectangle inside the tile when excluding the borders

    private final float tile_rect_width;
    private final float tile_rect_height;


    //top left position of the grid
    private Coordinate top_left;

    private HashMap<Coordinate, GameTile> tiles;
    //private HashMap<Coordinate, String> tiles;

    //private ArrayList<GameTile> tiles;

    public GridView(Coordinate top_left, float border_width,  float grid_width, float grid_height, int num_cols, int num_rows) throws Exception {

        this.top_left = top_left;



        if(top_left.getX() - border_width < 0 || top_left.getX() + grid_width > Constants.SCREEN_WIDTH || top_left.getY() - border_width < 0 || top_left.getY() + grid_height > Constants.SCREEN_HEIGHT){
            throw new Exception("Dimensions of grid are exceeding screen width");
        }


        this.num_cols = num_cols;
        this.num_rows = num_rows;




        tile_width = grid_width/(float)num_cols;
        tile_height = grid_height/(float)num_rows;


        tile_rect_width = tile_width-2*Constants.TILE_BORDER_WIDTH;
        tile_rect_height = tile_height-2*Constants.TILE_BORDER_WIDTH;

        if(tile_rect_width <= 0 || tile_rect_height <= 0 || tile_height <= tile_rect_height || tile_width <= tile_rect_width){
            throw new Exception("Dimensions of tile wrong");
        }


        tiles = new HashMap<Coordinate, GameTile>();
        //tiles = new HashMap<Coordinate, GameTile>();

        init();

    }

    public float getWidth() { return tile_width; }
    public float getHeight() { return tile_height; }


    public void reAdjustCoordinates(){



            float xcoordinate = top_left.getX();
            float ycoordinate = top_left.getY();

            for (int i = 0; i < num_cols; i++) {
                //xcoordinate = xcoordinate+i*tile_width;
                for (int j = 0; j < num_rows; j++) {


                    //tiles.put(new Coordinate((float)i,(float)j), new GameTile(new EmptyTile(), xcoordinate+i*tile_width, ycoordinate+j*tile_height));
                    tiles.get(new Coordinate((float)i, (float)j)).setC(xcoordinate+i*tile_width);
                    tiles.get(new Coordinate((float)i, (float)j)).setR(ycoordinate + j * tile_height);
                }
            }



    }


    public void init(){

        //float ycoordinate = Constants.TILE_BORDER_WIDTH;
        //float xcoordinate = Constants.TILE_BORDER_WIDTH;

        float xcoordinate = top_left.getX();
        float ycoordinate = top_left.getY();

        for (int i = 0; i < num_cols; i++) {
            //xcoordinate = xcoordinate+i*tile_width;
            for (int j = 0; j < num_rows; j++) {

                //Coordinate tile_coordinate = new Coordinate(xcoordinate+i*tile_width, ycoordinate+j*tile_height);


                //ycoordinate = ycoordinate+j*tile_height;

                //tiles.add(new GameTile(TileState.EMPTY_TILE,(float)i,(float)j ));
                //tiles.put(new Coordinate((float)i,(float)j), new GameTile(TileState.EMPTY_TILE, xcoordinate+i*tile_width, ycoordinate+j*tile_height));
                tiles.put(new Coordinate((float)i,(float)j), new GameTile(new EmptyTile(), xcoordinate+i*tile_width, ycoordinate+j*tile_height));
            }
        }

    }


    //Second overloaded constructor
    /*
    public GridView(HashMap<Coordinate,GameTile> intiles, int num_cols, int num_rows) throws Exception {
        this.num_cols = num_cols;
        this.num_rows = num_rows;


        tile_width = Constants.SHIP_GRID_WIDTH / (float) num_cols;
        tile_height = Constants.SHIP_GRID_HEIGHT / (float) num_rows;


        tile_rect_width = tile_width - 2 * Constants.TILE_BORDER_WIDTH;
        tile_rect_height = tile_height - 2 * Constants.TILE_BORDER_WIDTH;

        if (tile_rect_width <= 0 || tile_rect_height <= 0 || tile_height <= tile_rect_height || tile_width <= tile_rect_width) {
            throw new Exception("Dimensions of tile wrong");
        }

        this.tiles = intiles;
    }
    */

    public void draw(Canvas canvas) {

        /*
        for(Coordinate cor: tiles.keySet()){
            tiles.get(cor).drawTile(canvas, cor.getX(), cor.getY());
        }
        */

       for(GameTile tile: tiles.values()){
           tile.drawTile(canvas, tile.getC(), tile.getR(), tile_rect_width, tile_rect_height);
       }


        /*
        for (GameTile tile : tiles) {
            tile.drawTile(canvas,tile_rect_width, tile_rect_height);
        }
        */
    }

    public GameTile getTile2(float col, float row){
        GameTile gt = tiles.get(new Coordinate(col, row));

        if(gt == null){
            return null;
        } else {
            return gt;
        }
    }

    public GameTile getTile(float x, float y){

        //int column = (int)(x/tile_width);
        //int row = (int)(y/tile_height);

        int column = (int)((x-top_left.getX())/tile_width);
        int row = (int)((y-top_left.getY())/tile_height);

        Coordinate c = new Coordinate(column,row);


        GameTile tile = tiles.get(c);

       // Set<Coordinate> cs = tiles.keySet();

        if(tile != null) {

            return tile;
        } else {
            return null;
        }
        //return tiles.get(new Coordinate(x/tile_width, y/tile_height));

    }

    public Coordinate convertToTileCoordinate(float x, float y){

        int column = (int)((x-top_left.getX())/tile_width);
        int row = (int)((y-top_left.getY())/tile_height);

        Coordinate c = new Coordinate(column,row);

        return c;

    }
    public Coordinate convertBack(float column, float row){

        float x = column * tile_width + top_left.getX();
        float y = row * tile_height + top_left.getY();

        Coordinate c = new Coordinate(x,y);

        return c;


    }

    public static Coordinate getCoordinateKeyForStrike(Coordinate top_left_grid, float x, float y){

        int column = (int)((x-top_left_grid.getX())/(Constants.STRIKE_GRID_WIDTH/Constants.NUMBER_COLUMN_TILES));
        int row = (int)((y-top_left_grid.getY())/(Constants.STRIKE_GRID_HEIGHT/Constants.NUMBER_ROW_TILES));

        Coordinate c = new Coordinate(column,row);

        return c;

    }

    public Coordinate getTop_left() {
        return top_left;
    }

    public HashMap<Coordinate, GameTile> getTiles() {
        return tiles;
    }

    public void setTiles(HashMap<Coordinate, GameTile> tiles) {

        this.tiles = tiles;
        reAdjustCoordinates();
    }
}
