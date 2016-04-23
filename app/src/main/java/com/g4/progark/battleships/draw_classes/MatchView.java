package com.g4.progark.battleships.draw_classes;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.g4.progark.battleships.controllers.AndroidMenu;

/**
 * Created by Kristian on 11/03/2016.
 */
public abstract class MatchView extends SurfaceView implements SurfaceHolder.Callback{

    protected AndroidMenu mainActivity;
    protected SurfaceHolder holder;

    public MatchView(AndroidMenu context) {
        super(context);
        this.mainActivity = context;
        this.holder = getHolder();
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


    public void update(){

    }

}
