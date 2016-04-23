package com.g4.progark.battleships.models;

import com.g4.progark.battleships.utility.Constants;
import com.g4.progark.battleships.utility.Coordinate;

import java.util.ArrayList;

/**
 * Created by Eier on 13.04.2016.
 */
public class AirStrikeFirepower extends Firepower {

    public AirStrikeFirepower() {

        setName("AirStrike");
        setDamage(1);
    }

    @Override
    public ArrayList<Coordinate> fire(float x, float y, float tileWidth, float tileHeight,
                                      float xStartGrid, float yStartGrid) {

        for(float i = xStartGrid; i< xStartGrid + (Constants.NUMBER_COLUMN_TILES*tileWidth); i += tileWidth) {
            coordinates.add(new Coordinate(i, y));
        }
        return coordinates;
    }
}
