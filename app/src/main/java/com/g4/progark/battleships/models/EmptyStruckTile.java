package com.g4.progark.battleships.models;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.g4.progark.battleships.utility.Constants;

/**
 * Created by ahmed on 04.04.2016.
 */
public class EmptyStruckTile implements Drawable{


    @Override
    public void draw(Canvas canvas, float x, float y, float width, float height) {
        /*
        Paint paint = new Paint();
        paint.setColor(Color.rgb(0, 0, 0));
        paint.setStrokeWidth(Constants.TILE_BORDER_WIDTH);
        paint.setStyle(Paint.Style.STROKE);


        Rect rect = new Rect();
        rect.set((int)x,(int)(y+height),(int)(x+width),(int)y);

        float radius = width/2;

        paint.setColor(Color.RED);
        canvas.drawCircle((x+width)/2,(y+height)/2,radius,paint);

        canvas.drawRect(rect, paint);


        float smallerDimension = width <= height ? width : height;

        Paint p = new Paint();
        p.setColor(Color.RED);

        canvas.drawCircle(x + width/2,y+height/2,smallerDimension/2,p);

        */


        Paint p = new Paint();
        p.setColor(Color.YELLOW);

        canvas.drawLine(x,y+height,x+width,y,p);
        canvas.drawLine(x,y,x+width,y+height,p);


    }
}
