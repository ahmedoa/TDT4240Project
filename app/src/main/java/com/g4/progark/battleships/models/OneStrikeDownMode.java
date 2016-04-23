package com.g4.progark.battleships.models;

/**
 * Created by Eier on 22.04.2016.
 */
public class OneStrikeDownMode extends GameMode{

    public OneStrikeDownMode() {

        super();

        setName("OneStrikeDown");
        sinkShipType = new OneStrikeDown();

    }

}