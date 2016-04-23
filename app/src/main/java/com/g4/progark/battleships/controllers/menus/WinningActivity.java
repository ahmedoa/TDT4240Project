package com.g4.progark.battleships.controllers.menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.g4.progark.battleships.R;

/**
 * Created by Aleksander on 23.04.2016.
 */
public class WinningActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_winning);

        Button endGame = (Button) findViewById(R.id.endGame);

        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WinningActivity.this, MainActivity.class));
            }
        });
    }
}
