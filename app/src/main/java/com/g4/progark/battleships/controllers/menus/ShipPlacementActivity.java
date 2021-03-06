package com.g4.progark.battleships.controllers.menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.g4.progark.battleships.draw_classes.GridView;
import com.g4.progark.battleships.utility.Constants;
import com.g4.progark.battleships.utility.Coordinate;

/**
 * Created by ahmed on 17.04.2016.
 */
public class ShipPlacementActivity extends AppCompatActivity {

    private ShipPlacementView spv;
    private GridView player_1_ship_grid;
    private GridView player_2_ship_grid;
    private int selectedTiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        selectedTiles = intent.getIntExtra("selectedTiles", 0);
        Log.d("selectedTiles", selectedTiles + "");

        try {
            if (Constants.CURRENT_PLAYER == 1) {
                player_1_ship_grid = new GridView(new Coordinate(0, 0),
                        Constants.SHIP_GRID_BORDER, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.NUMBER_COLUMN_TILES, Constants.NUMBER_ROW_TILES);


                if(Constants.SHIP_TILES1 != null){
                    player_1_ship_grid.setTiles(Constants.SHIP_TILES1);
                }

                spv = new ShipPlacementView(this, Constants.chosenGameMap, player_1_ship_grid);
            } else {
                player_2_ship_grid = new GridView(new Coordinate(0, 0),
                        Constants.SHIP_GRID_BORDER, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.NUMBER_COLUMN_TILES, Constants.NUMBER_ROW_TILES);


                if(Constants.SHIP_TILES2 != null){
                    player_2_ship_grid.setTiles(Constants.SHIP_TILES2);
                }

                spv = new ShipPlacementView(this, Constants.chosenGameMap, player_2_ship_grid);
            }

        } catch (Exception e){

        }

        setContentView(spv);
        Toast.makeText(this, "Player " + Constants.CURRENT_PLAYER + " place your selected ship", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(Constants.CURRENT_PLAYER == 1)
            Constants.NUMBER_SHIP_TILES_PLAYER1 += selectedTiles;
        else {
            Constants.NUMBER_SHIP_TILES_PLAYER2 += selectedTiles;
        }
        Intent i = new Intent(getApplicationContext(), ShipSelectionActivity.class);
        startActivity(i);

    }


}
