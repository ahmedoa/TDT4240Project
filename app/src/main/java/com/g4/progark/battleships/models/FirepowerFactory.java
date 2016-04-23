package com.g4.progark.battleships.models;

/**
 * Created by Eier on 13.04.2016.
 */
public class FirepowerFactory {

    public Firepower setFirePower(int  firePowerType) {

        Firepower currentFirePower = null;

        switch (firePowerType) {
            case 0:
                return new NormalFirepower();
            case 1:
                return new ClusterBombFirepower();
            case 2:
                return new AirStrikeFirepower();
            default:
                return null;
        }
    }
}
