package com.g4.progark.battleships.models;

import com.g4.progark.battleships.utility.Constants;
import com.g4.progark.battleships.utility.Coordinate;

import java.util.ArrayList;

/**
 * Created by Eier on 13.04.2016.
 */
public class ClusterBombFirepower extends Firepower {

    public ClusterBombFirepower() {

        setName("ClusterBomb");
        setDamage(1);

    }

    @Override
    public ArrayList<Coordinate> fire(float x, float y, float tileWidth, float tileHeight,
                                      float xStartGrid, float yStartGrid) {
            coordinates.add(new Coordinate(x - tileWidth, y - tileHeight));
            coordinates.add(new Coordinate(x, y - tileHeight));
            coordinates.add(new Coordinate(x + tileWidth, y - tileHeight));
            coordinates.add(new Coordinate(x - tileWidth, y));
            coordinates.add(new Coordinate(x, y));
            coordinates.add(new Coordinate(x + tileWidth, y));
            coordinates.add(new Coordinate(x - tileWidth, y + tileHeight));
            coordinates.add(new Coordinate(x, y + tileHeight));
            coordinates.add(new Coordinate(x + tileWidth, y + tileHeight));

        return coordinates;

    }
}
