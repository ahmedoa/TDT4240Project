package com.g4.progark.battleships.models;

import com.g4.progark.battleships.utility.Coordinate;

import java.util.ArrayList;

/**
 * Created by Eier on 13.04.2016.
 */
public class NormalFirepower extends Firepower {

    public NormalFirepower() {

        setName("Normal");
        setDamage(1);

    }

    @Override
    public ArrayList<Coordinate> fire(float x, float y, float tileWidth, float tileHeight,
                                      float xStartGrid, float yStartGrid) {

        coordinates.add(new Coordinate(x, y));
        return coordinates;

    }
}
