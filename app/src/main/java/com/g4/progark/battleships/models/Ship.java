package com.g4.progark.battleships.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.g4.progark.battleships.utility.Constants;
import com.g4.progark.battleships.utility.Coordinate;

import java.util.ArrayList;

/**
 * Created by Kristian on 11/03/2016.
 */
public class Ship {


    ArrayList<Coordinate> ship_coordinates;

    Coordinate start_position;

    //Number of tiles the ship will spann horizontally
    private int tile_length;



    private boolean active;

    private  Orientation orientation;

    public Ship() {
        active = true;
    }


    public void setShip_coordinates(ArrayList<Coordinate> ship_coordinates) {
        this.ship_coordinates = ship_coordinates;
    }

    public void setStart_position(Coordinate start_position) {
        this.start_position = start_position;
    }

    public void setTile_length(int tile_length) {
        this.tile_length = tile_length;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public ArrayList<Coordinate> getShip_coordinates() {
        return ship_coordinates;
    }

    public Coordinate getStart_position() {
        return start_position;
    }

    public int getTile_length() {
        return tile_length;
    }

    public boolean isActive() {
        return active;
    }

    public Orientation getOrientation() {
        return orientation;
    }


    //public void sink()
}

