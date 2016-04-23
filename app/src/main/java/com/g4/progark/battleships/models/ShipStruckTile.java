package com.g4.progark.battleships.models;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.g4.progark.battleships.utility.Constants;

/**
 * Created by ahmed on 07.04.2016.
 */
public class ShipStruckTile implements Drawable{


    @Override
    public void draw(Canvas canvas, float x, float y, float width, float height) {

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(Constants.TILE_BORDER_WIDTH);
        paint.setStyle(Paint.Style.FILL);



        canvas.drawRect(x,y,x+width, y+height, paint);

        float smallerDimension = width <= height ? width : height;

        Paint p = new Paint();
        p.setColor(Color.RED);

        canvas.drawCircle(x + width/2,y+height/2,smallerDimension/2,p);
    }
}
