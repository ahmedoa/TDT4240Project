package com.g4.progark.battleships.controllers.menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.g4.progark.battleships.DB.DBTools;
import com.g4.progark.battleships.R;
import com.g4.progark.battleships.utility.Constants;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eier on 16.04.2016.
 */
public class AdvancedGameSetupActivity extends AppCompatActivity {

    DBTools dbTools;
    ArrayList<HashMap<String, String>> firepowerArrayList;

    EditText normalEditText;
    EditText clusterBombEditText;
    EditText airStrikeEditText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_setup);
        Intent intent = getIntent();
        dbTools = new DBTools(this);

        //normalEditText = (EditText) findViewById(R.id.normalEditText);
        clusterBombEditText = (EditText) findViewById(R.id.clusterBombEditText);
        airStrikeEditText = (EditText) findViewById(R.id.airStrikeEditText);
    }

    public void startGame(View v) {

        prepareDatabase();
        Intent intent = new Intent(this, GameMapSelectionListActivity.class);
        startActivity(intent);

    }
    public void prepareDatabase() {

        dbTools.clearFirepowerTable();
        firepowerArrayList = setUpFirepower();
        for(HashMap firepowerMap : firepowerArrayList) {
            dbTools.insertFirepower(firepowerMap);
        }
    }
    public ArrayList<HashMap<String, String>> setUpFirepower() {

        ArrayList<HashMap<String, String>> firepowerArrayList = new ArrayList<HashMap<String, String>>();

        /*
        Constants.normalAmmo = normalEditText.getText().length() != 0 ?
                normalEditText.getText().toString() : "999";
        Constants.clusterBombAmmo = clusterBombEditText.getText().length() != 0 ?
                clusterBombEditText.getText().toString() : "0";
        Constants.airStrikeAmmo = airStrikeEditText.getText().length() != 0 ?
                airStrikeEditText.getText().toString() : "0"; */

        HashMap<String, String> normalFirepowerMap = new HashMap<>();
        normalFirepowerMap.put("firepowerName", "Normal");
        normalFirepowerMap.put("ammo", "∞");

        HashMap<String, String> clusterBombFirepowerMap = new HashMap<>();
        clusterBombFirepowerMap.put("firepowerName", "Cluster Bomb");
        clusterBombFirepowerMap.put("ammo", clusterBombEditText.getText().length() != 0 ?
                clusterBombEditText.getText().toString() : "0");
        clusterBombFirepowerMap.put("damage", "1");

        HashMap<String, String> airStrikeFirepowerMap = new HashMap<>();
        airStrikeFirepowerMap.put("firepowerName", "Air Strike");
        airStrikeFirepowerMap.put("ammo", airStrikeEditText.getText().length() != 0 ?
                airStrikeEditText.getText().toString() : "0");
        airStrikeFirepowerMap.put("damage", "1");

        firepowerArrayList.add(normalFirepowerMap);
        firepowerArrayList.add(clusterBombFirepowerMap);
        firepowerArrayList.add(airStrikeFirepowerMap);

        return firepowerArrayList;

    }
}
