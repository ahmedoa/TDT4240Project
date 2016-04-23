package com.g4.progark.battleships.controllers.menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.g4.progark.battleships.DB.DBTools;
import com.g4.progark.battleships.R;
import com.g4.progark.battleships.utility.Constants;

/**
 * Created by Aleksander on 23.04.2016.
 */
public class WinningActivity extends AppCompatActivity {

    DBTools dbTools = new DBTools(this);

    Button endGame;
    Button playAgainGame;
    TextView winnerTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_winning);

        winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        winnerTextView.setText("Player: " + Constants.CURRENT_PLAYER + " won!");
        endGame = (Button) findViewById(R.id.endGame);
        playAgainGame = (Button) findViewById(R.id.playAgainButton);

        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
                resetSettings();
                resetDB();
                startActivity(new Intent(WinningActivity.this, MainActivity.class));
            }
        });

        /*playAgainGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
                setBackToSelectedAmmo();
                startActivity(new Intent(WinningActivity.this, ShipSelectionActivity.class));
            }
        });*/
    }
    public void resetSettings() {

        Constants.chosenGameMode = 0;
        Constants.chosenGameMap = null;

    }
    public void setBackToSelectedAmmo() {

        dbTools.restAmmoToSelected(0, Constants.normalAmmo);
        dbTools.restAmmoToSelected(1, Constants.clusterBombAmmo );
        dbTools.restAmmoToSelected(2, Constants.airStrikeAmmo);

    }
    public void resetDB() {

        dbTools.clearFirepowerTable();

    }
    public void resetGame() {

        Constants.PLAYER1_SHIPS.clear();
        Constants.PLAYER2_SHIPS.clear();

        Constants.CURRENT_PLAYER = 1;

        Constants.NUMBER_SHIP_TILES_PLAYER1 =  10;
        Constants.NUMBER_SHIP_TILES_PLAYER2 =  10;

        Constants.SHIP_TILES1 = null;
        Constants.STRIKE_TILES1 = null;
        Constants.SHIP_TILES2 = null;
        Constants.STRIKE_TILES2 = null;
        Constants.CURRENT_SHIP_SELECTED = null;
        Constants.PLAYER1_SHIPS.clear();
        Constants.PLAYER2_SHIPS.clear();

        Constants.currentFirePower = 0;

    }
}
