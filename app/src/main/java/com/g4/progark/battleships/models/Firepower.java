package com.g4.progark.battleships.models;

import android.util.Pair;

import com.g4.progark.battleships.utility.Coordinate;

import java.util.ArrayList;

/**
 * Created by Eier on 13.04.2016.
 */
public abstract class Firepower {

    private String name;
    private double damage;
    ArrayList<Coordinate> coordinates = new ArrayList<>();

    public abstract ArrayList<Coordinate> fire(float x, float y, float tileWidth, float tileHeight,
    float xStartGrid, float yStartGrid);

    public String getName() { return name; }
    public void setName(String newName) {
        name = newName;
    }
    public double getDamage() {
        return damage;
    }
    public void setDamage(double newDamage) {
        damage = newDamage;
    }
}