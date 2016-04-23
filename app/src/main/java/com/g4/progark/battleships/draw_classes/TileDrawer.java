package com.g4.progark.battleships.draw_classes;

import android.graphics.Canvas;

import com.g4.progark.battleships.models.EmptyStruckTile;
import com.g4.progark.battleships.models.EmptyTile;
import com.g4.progark.battleships.models.TileState;

/**
 * Created by ahmed on 10.04.2016.
 */
public class TileDrawer {

    public static void drawTile(TileState tileState,Canvas canvas, float x, float y, float width, float height){

        if(tileState == TileState.EMPTY_TILE){
            EmptyTile tile = new EmptyTile();
            tile.draw(canvas,x,y,width,height);

        } else if(tileState == TileState.EMPTY_STRUCK_TILE){
            EmptyStruckTile emptyStruckTile = new EmptyStruckTile();
            emptyStruckTile.draw(canvas, x, y, width, height);
        }

    }
}
