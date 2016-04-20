package com.g4.progark.battleships.controllers.menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.g4.progark.battleships.R;

/**
 * Created by Eier on 16.04.2016.
 */
public class GameModeActivity extends AppCompatActivity {

    public final static String GAME_MODE = "com.example.myfirstapp.MODE";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
        Log.d("myTag", "This is my message");
        //dbTools = new DBTools(this);


    }
    public void startNormalGame(View v) {

        Intent intent = new Intent(this, FirepowerActivity.class);
        intent.putExtra(GAME_MODE, "Normal");
        startActivity(intent);

    }

    public void startAdvancedGame(View v) {
        //prepareDatabase();
        Intent intent = new Intent(this, AdvancedGameSetupActivity.class);
        intent.putExtra(GAME_MODE, "Advanced");
        startActivity(intent);

    }
    /*public void prepareDatabase() {

        dbTools.clearFirepowerTable();
        firepowerArrayList = setUpFirepower();
        for(HashMap firepowerMap : firepowerArrayList) {
            dbTools.instertFirepower(firepowerMap);
        }
    }
    public ArrayList<HashMap<String, String>> setUpFirepower() {

        ArrayList<HashMap<String, String>> firepowerArrayList = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> normalFirepowerMap = new HashMap<String, String>();
        normalFirepowerMap.put("firepowerName", "Normal");
        normalFirepowerMap.put("ammo", "999");
        normalFirepowerMap.put("damage", "1");

        HashMap<String, String> clusterBombFirepowerMap = new HashMap<String, String>();
        clusterBombFirepowerMap.put("firepowerName", "Cluster Bomb");
        clusterBombFirepowerMap.put("ammo", "1");
        clusterBombFirepowerMap.put("damage", "1");

        HashMap<String, String> AirStrikeFirepowerMap = new HashMap<String, String>();
        AirStrikeFirepowerMap.put("firepowerName", "Air Strike");
        AirStrikeFirepowerMap.put("ammo", "1");
        AirStrikeFirepowerMap.put("damage", "1");

        firepowerArrayList.add(normalFirepowerMap);
        firepowerArrayList.add(clusterBombFirepowerMap);
        firepowerArrayList.add(AirStrikeFirepowerMap);

        return firepowerArrayList;

    }
    */
}
