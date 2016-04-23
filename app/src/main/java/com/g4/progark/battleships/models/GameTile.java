package com.g4.progark.battleships.models;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.g4.progark.battleships.draw_classes.TileDrawer;
import com.g4.progark.battleships.utility.Constants;
import com.g4.progark.battleships.utility.Coordinate;

import java.io.Serializable;

/**
 * Created by ahmed on 04.04.2016.
 */
public class GameTile{



    private Drawable gameTileState;

    //private Coordinate top_left;

    //


    //private TileState tileState;

    //Coordinates of the top left corner of the game tile, avoided use of a Coordinate instance as primitive field states are easier to parse across activity transitions
    private float r;
    private float c;


    public GameTile(Drawable gameTileState, float c, float r) {
        //this.tileState = tileState;
        this.gameTileState = gameTileState;
        this.r = r;
        this.c = c;
    }

    public void drawBorders(Canvas canvas, float x, float y, float width, float height){

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(Constants.TILE_BORDER_WIDTH);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawRect(x, y, x + width, y + height, paint);

    }

    public void drawTile(Canvas canvas, float x, float y , float width, float height){

        drawBorders(canvas, x, y, width, height);

        //TileDrawer.drawTile(tileState, canvas, c, r, width, height);

        gameTileState.draw(canvas, x, y, width, height);


    }

    public Drawable getGameTileState() {
        return gameTileState;
    }

    public void setGameTileState(Drawable gameTileState) {
        this.gameTileState = gameTileState;
    }


    /*
    public void setTileState(TileState tileState) {
        this.tileState = tileState;
    }


    public TileState getTileState() {
        return tileState;
    }
    */

    public float getR() {
        return r;
    }

    public float getC() {
        return c;
    }

    public void setR(float r) {
        this.r = r;
    }

    public void setC(float c) {
        this.c = c;
    }
}
