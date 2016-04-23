package com.g4.progark.battleships.models;

/**
 * Created by Eier on 22.04.2016.
 */
public class StandardGameMode extends GameMode{

    public StandardGameMode() {

        super();

        setName("Standard");
        sinkShipType = new StandardMode();

    }

}
