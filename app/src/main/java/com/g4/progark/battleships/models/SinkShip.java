package com.g4.progark.battleships.models;

import android.util.Log;

import com.g4.progark.battleships.draw_classes.GridView;
import com.g4.progark.battleships.utility.Constants;
import com.g4.progark.battleships.utility.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eier on 22.04.2016.
 */
public interface SinkShip {

    ArrayList<Coordinate> tilesToStrike = new ArrayList<>();
    ArrayList<Coordinate> sink(Coordinate c, List<Ship> currentShips);
}

class StandardMode implements SinkShip {


    @Override
    public ArrayList<Coordinate> sink (Coordinate c, List<Ship> currentShips) {
        tilesToStrike.clear();
        tilesToStrike.add(c);
        return tilesToStrike;
    }
}

class OneStrikeDown implements SinkShip {


    @Override
    public ArrayList<Coordinate> sink (Coordinate c, List<Ship> currentShips) {
        tilesToStrike.clear();
        ArrayList<Coordinate> shipCoordinates;
        for(Ship ship : currentShips) {

            shipCoordinates = ship.getShip_coordinates();
            for(Coordinate coordinates : shipCoordinates) {

                if(c.getX() == coordinates.getX() && c.getY() == coordinates.getY()) {
                    for(Coordinate coordinatesAgain : shipCoordinates) {
                        tilesToStrike.add(coordinatesAgain);
                    } return tilesToStrike;
                }

            }
        }
        return tilesToStrike;
    }
}
