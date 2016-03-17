package com.g4.progark.battleships.models;

/**
 * Created by Kristian on 11/03/2016.
 */
public class Player {
    private String name;
    private GameShip[] ships;
    private Weapon[] weapons;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameShip[] getShips() {
        return ships;
    }

    public void setShips(GameShip[] ships) {
        this.ships = ships;
    }

    public Weapon[] getWeapons() {
        return weapons;
    }

    public void setWeapons(Weapon[] weapons) {
        this.weapons = weapons;
    }
}
