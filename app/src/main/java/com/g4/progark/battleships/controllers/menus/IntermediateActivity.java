package com.g4.progark.battleships.controllers.menus;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.g4.progark.battleships.R;

import java.io.Serializable;

public class IntermediateActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);


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
