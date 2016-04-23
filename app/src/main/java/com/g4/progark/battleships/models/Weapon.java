package com.g4.progark.battleships.models;

/**
 * Created by Kristian on 17/03/2016.
 */
public class Weapon {

    public static final int LINE = 0;
    public static final int CIRCULAR = 1;
    public static final int CROSS = 2;
    public static final int DIAGONAL = 3;
    public static final int DIAGONAL_CROSS = 4;

    private int type;
    private int length;

    public Weapon(int type, int length) {
        this.type = type;

        if (length < 4 && length > 0) {
            this.type = type;
        }else{
            throw new IllegalArgumentException();
        }
    }

}
