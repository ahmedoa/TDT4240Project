package com.g4.progark.battleships.controllers;

import android.content.Context;

import com.g4.progark.battleships.draw_classes.GameView;
import com.g4.progark.battleships.draw_classes.GridView;
import com.g4.progark.battleships.draw_classes.MatchView;
import com.g4.progark.battleships.models.GameEntities;
import com.g4.progark.battleships.models.Player;
import com.g4.progark.battleships.utility.Constants;
import com.g4.progark.battleships.utility.Coordinate;

/**
 * Created by Kristian on 17/03/2016.
 */
public class GameController {


    private GridView player1_ship_grid;
    private GridView player1_strike_grid;

    private GridView player2_ship_grid;
    private GridView player2_strike_grid;

    private GameView gameView;



    public GameController(Context context, String map_name){

        float x1 = Constants.SCREEN_WIDTH/(float)2 - (Constants.SHIP_GRID_WIDTH+Constants.SHIP_GRID_BORDER)/(float)2;
        float y1 = Constants.SHIP_GRID_BORDER;

        float x2 = Constants.SCREEN_WIDTH/(float)2 - (Constants.STRIKE_GRID_WIDTH+Constants.STRIKE_GRID_BORDER)/(float)2;
        float y2 = Constants.SCREEN_HEIGHT - Constants.STRIKE_GRID_BORDER  - Constants.STRIKE_GRID_HEIGHT;

        if(y2 < y1){
            try {
                throw new Exception("Strike grid is clashing with Ship grid");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            this.player1_ship_grid = new GridView(new Coordinate(x1,y1),
                    Constants.SHIP_GRID_BORDER, Constants.SHIP_GRID_WIDTH, Constants.SHIP_GRID_HEIGHT, Constants.NUMBER_COLUMN_TILES, Constants.NUMBER_ROW_TILES);

            this.player1_strike_grid = new GridView(new Coordinate(x2,y2), Constants.STRIKE_GRID_BORDER, Constants.STRIKE_GRID_WIDTH, Constants.STRIKE_GRID_HEIGHT, Constants.NUMBER_COLUMN_TILES, Constants.NUMBER_ROW_TILES);


            this.player2_ship_grid = new GridView(new Coordinate(x1,y1),
                    Constants.SHIP_GRID_BORDER, Constants.SHIP_GRID_WIDTH, Constants.SHIP_GRID_HEIGHT, Constants.NUMBER_COLUMN_TILES, Constants.NUMBER_ROW_TILES);

            this.player2_strike_grid = new GridView(new Coordinate(x2,y2),
                    Constants.STRIKE_GRID_BORDER, Constants.STRIKE_GRID_WIDTH, Constants.STRIKE_GRID_HEIGHT, Constants.NUMBER_COLUMN_TILES, Constants.NUMBER_ROW_TILES);

        } catch (Exception e) {
            e.printStackTrace();
        }

      // gameView = new GameView(context, map_name, null, null);
       // gameView = new GameView(context, map_name, null, null);
    }


    public void update(){

        if(Constants.CURRENT_PLAYER == 1){
            gameView.setCurrent_ship_grid(player2_ship_grid);
            gameView.setCurrent_strike_grid(player2_strike_grid);
        } else {
            gameView.setCurrent_ship_grid(player1_ship_grid);
            gameView.setCurrent_strike_grid(player1_strike_grid);
        }


    }


    public GameView getGameView() {
        update();
        return gameView;
    }
}
