package com.g4.progark.battleships.models;

/**
 * Created by Eier on 13.04.2016.
 */
public abstract class Firepower {

    private String name;
    private double damage;

    public String getName() {
        return name;
    }
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