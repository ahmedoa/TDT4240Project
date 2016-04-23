package com.g4.progark.battleships.models;

/**
 * Created by ahmed on 06.04.2016.
 */
public class GameThread extends Thread {

    private boolean running;

    @Override
    public void run() {

        while(running){



        }
    }


    public void stopThread(){
        running = false;
    }
}
