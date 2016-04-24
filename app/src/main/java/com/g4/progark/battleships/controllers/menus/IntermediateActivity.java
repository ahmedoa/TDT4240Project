package com.g4.progark.battleships.controllers.menus;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.g4.progark.battleships.R;
import com.g4.progark.battleships.utility.Constants;

import java.io.Serializable;

public class IntermediateActivity extends FragmentActivity {

    TextView currentPlayerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);

        currentPlayerTextView = (TextView) findViewById(R.id.txt_inter);
        currentPlayerTextView.setText("Player " + Constants.CURRENT_PLAYER + ", your turn!");


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), GameViewActivity.class);
        startActivity(i);

    }

    public void startTurn(View v){



        Intent i = new Intent(getApplicationContext(), GameViewActivity.class);
        startActivity(i);

    }

}
