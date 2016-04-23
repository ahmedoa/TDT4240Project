package com.g4.progark.battleships.models;

import com.g4.progark.battleships.draw_classes.GridView;
import com.g4.progark.battleships.utility.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eier on 22.04.2016.
 */
public class GameMode {

    public SinkShip sinkShipType;
    private String modeName;

    public ArrayList<Coordinate> sinkShip(Coordinate c, List<Ship> currentShips) {

        return sinkShipType.sink(c, currentShips);

    }
    public void setName(String newName) {modeName = newName; }

    public void setSinkShipType(SinkShip newSinkType) {

        sinkShipType = newSinkType;

    }

}
