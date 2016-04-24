package com.g4.progark.battleships.controllers.menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.g4.progark.battleships.R;
import com.g4.progark.battleships.models.Orientation;
import com.g4.progark.battleships.models.Ship;
import com.g4.progark.battleships.utility.Constants;

public class ShipSelectionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TextWatcher {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    EditText length;
    int selected;
    TextView tile_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Constants.NUMBER_SHIP_TILES_PLAYER2 == 0){
            Constants.CURRENT_PLAYER = 1;
            Intent i = new Intent(this, IntermediateActivity.class);
            startActivity(i);

            Toast.makeText(this, "Game ready", Toast.LENGTH_SHORT).show();
            return;
        }

        setContentView(R.layout.activity_ship_selection);


        if(Constants.NUMBER_SHIP_TILES_PLAYER1 == 0){
            Toast.makeText(this, "Player 2, it is now your turn to place your ships :)", Toast.LENGTH_SHORT).show();
            Constants.CURRENT_PLAYER = 2;
        }



        tile_count = (TextView) findViewById(R.id.tile_count);

        String tcount = Constants.CURRENT_PLAYER == 1 ? "" + Constants.NUMBER_SHIP_TILES_PLAYER1 : ""+Constants.NUMBER_SHIP_TILES_PLAYER2;

        tile_count.setText("Tiles remaining for player " + Constants.CURRENT_PLAYER + " : " + tcount);

        spinner = (Spinner) findViewById(R.id.orientation_spinner);

        adapter = ArrayAdapter.createFromResource(this,R.array.orientations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        length = (EditText) findViewById(R.id.length);
        length.addTextChangedListener(this);

        Constants.CURRENT_SHIP_SELECTED = new Ship();


    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



        TextView v = (TextView) view;

        String text = v.getText().toString();

        if(text.equals("UP")){
             Constants.CURRENT_SHIP_SELECTED.setOrientation(Orientation.UP);
        } else if (text.equals("DOWN")){
            Constants.CURRENT_SHIP_SELECTED.setOrientation(Orientation.DOWN);
        } else if (text.equals("RIGHT")){
            Constants.CURRENT_SHIP_SELECTED.setOrientation(Orientation.RIGHT);
        }else if (text.equals("LEFT")){
            Constants.CURRENT_SHIP_SELECTED.setOrientation(Orientation.LEFT);
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        try {
            int remaining = Constants.CURRENT_PLAYER == 1 ? Constants.NUMBER_SHIP_TILES_PLAYER1 : Constants.NUMBER_SHIP_TILES_PLAYER2;
            selected = Integer.parseInt(editable.toString());

            if (selected > remaining) {
                length.setError("You dont have enough tiles");
            } else if (selected > Constants.NUMBER_ROW_TILES || selected > Constants.NUMBER_COLUMN_TILES){
                length.setError("Ship wont fit grid");

            } else if (selected < 0) {
                length.setError("Cannot select a negative amount");
            } else {
                Constants.CURRENT_SHIP_SELECTED.setTile_length(selected);


            }
        } catch(NumberFormatException e) {}

    }

    public void placeShipView(View v){

         if(selected == 0){
             length.setError("Please select a non zero value");
             return;
         }

        if(Constants.CURRENT_PLAYER == 1){
            Constants.NUMBER_SHIP_TILES_PLAYER1 -= selected;



        } else {
            Constants.NUMBER_SHIP_TILES_PLAYER2 -= selected;


        }

        Intent intent = new Intent(this, ShipPlacementActivity.class);
        intent.putExtra("selectedTiles", selected);
        startActivity(intent);

    }
}
