package com.g4.progark.battleships.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.g4.progark.battleships.R;
import com.g4.progark.battleships.utility.Constants;

/**
 * Created by Kristian on 11/03/2016.
 */
public class GameMap {

    private final Bitmap area;


    // Audio classes should be discussed before further work (using own interfaces should be done)

    public GameMap(Context context, String map_name){

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Bitmap temp = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier(map_name, "drawable", context.getPackageName()));
        area = Bitmap.createScaledBitmap(temp, width, height,true);
    }

    public Bitmap getArea() {
        return area;
    }
}
