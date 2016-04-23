package com.g4.progark.battleships.models;

/**
 * Created by Kristian on 17/03/2016.
 */
public class GameEntities {

    private Player[] players;
    private GameMap map;
    private Ship[] ships;


    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Ship[] getShips() {
        return ships;
    }

    public void setShips(Ship[] ships) {
        this.ships = ships;
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }
}
