package com.g4.progark.battleships.controllers.menus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.g4.progark.battleships.R;
import com.g4.progark.battleships.draw_classes.GameView;
import com.g4.progark.battleships.draw_classes.GridView;
import com.g4.progark.battleships.utility.Constants;
import com.g4.progark.battleships.utility.Coordinate;

public class GameViewActivity extends AppCompatActivity {




    private GridView player1_ship_grid;
    private GridView player1_strike_grid;

    private GridView player2_ship_grid;
    private GridView player2_strike_grid;

    private GameView gameView;



    //SharedPreferences shared = getSharedPreferences("test", Context.MODE_PRIVATE);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        float x1 = Constants.SCREEN_WIDTH/(float)2 - (Constants.SHIP_GRID_WIDTH+Constants.SHIP_GRID_BORDER)/(float)2;
        float y1 = Constants.SHIP_GRID_BORDER;

        float x2 = Constants.SCREEN_WIDTH/(float)2 - (Constants.STRIKE_GRID_WIDTH+Constants.STRIKE_GRID_BORDER)/(float)2;
        float y2 = Constants.SCREEN_HEIGHT - Constants.STRIKE_GRID_BORDER  - Constants.STRIKE_GRID_HEIGHT;


        try {

            if(Constants.CURRENT_PLAYER == 1){

                player1_ship_grid = new GridView(new Coordinate(x1,y1),
                        Constants.SHIP_GRID_BORDER, Constants.SHIP_GRID_WIDTH, Constants.SHIP_GRID_HEIGHT, Constants.NUMBER_COLUMN_TILES, Constants.NUMBER_ROW_TILES);

                player1_strike_grid = new GridView(new Coordinate(x2,y2), Constants.STRIKE_GRID_BORDER, Constants.STRIKE_GRID_WIDTH, Constants.STRIKE_GRID_HEIGHT, Constants.NUMBER_COLUMN_TILES, Constants.NUMBER_ROW_TILES);

                player2_ship_grid = new GridView(new Coordinate(x1,y1),
                        Constants.SHIP_GRID_BORDER, Constants.SHIP_GRID_WIDTH, Constants.SHIP_GRID_HEIGHT, Constants.NUMBER_COLUMN_TILES, Constants.NUMBER_ROW_TILES);

                if(Constants.SHIP_TILES1 == null){
                    throw new Exception("STOOOOP");
                }

                player1_ship_grid.setTiles(Constants.SHIP_TILES1);
                player2_ship_grid.setTiles(Constants.SHIP_TILES2);

                if(Constants.STRIKE_TILES1 != null){
                    player1_strike_grid.setTiles(Constants.STRIKE_TILES1);
                }

                gameView = new GameView(this, "sea", player1_ship_grid, player1_strike_grid, player2_ship_grid);
            } else {

                player2_ship_grid = new GridView(new Coordinate(x1,y1),
                        Constants.SHIP_GRID_BORDER, Constants.SHIP_GRID_WIDTH, Constants.SHIP_GRID_HEIGHT, Constants.NUMBER_COLUMN_TILES, Constants.NUMBER_ROW_TILES);

                player2_strike_grid = new GridView(new Coordinate(x2,y2),
                        Constants.STRIKE_GRID_BORDER, Constants.STRIKE_GRID_WIDTH, Constants.STRIKE_GRID_HEIGHT, Constants.NUMBER_COLUMN_TILES, Constants.NUMBER_ROW_TILES);


                player1_ship_grid = new GridView(new Coordinate(x1,y1),
                        Constants.SHIP_GRID_BORDER, Constants.SHIP_GRID_WIDTH, Constants.SHIP_GRID_HEIGHT, Constants.NUMBER_COLUMN_TILES, Constants.NUMBER_ROW_TILES);


                if(Constants.STRIKE_TILES2 != null){
                    //player2_ship_grid = new GridView(new Coordinate(x1,y1),
                            //Constants.SHIP_GRID_BORDER, Constants.SHIP_GRID_WIDTH, Constants.SHIP_GRID_HEIGHT, Constants.NUMBER_COLUMN_TILES, Constants.NUMBER_ROW_TILES);


                    player2_strike_grid.setTiles(Constants.STRIKE_TILES2);

                }

                player1_ship_grid.setTiles(Constants.SHIP_TILES1);
                player2_ship_grid.setTiles(Constants.SHIP_TILES2);


                gameView = new GameView(this, "sea", player2_ship_grid, player2_strike_grid, player1_ship_grid);
            }

        } catch(Exception e){
            e.printStackTrace();
        }





        setContentView(gameView);
        Toast.makeText(this, "Player " + Constants.CURRENT_PLAYER + " GO!!", Toast.LENGTH_SHORT).show();

        //map name should be passed in as a parameter, it is at the moment hard coded


    }



    /*
    public void loadState(){



        SharedPreferences shared = getSharedPreferences("test", Context.MODE_PRIVATE);

        if(shared.contains("counter")) {
            counter = shared.getInt("counter", -1);

            Toast.makeText(this, "The new counter is " + counter, Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "Counter has not been saved yet", Toast.LENGTH_LONG).show();
        }
        */
                /*

                Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();

                String json = shared.getString("grid1", "");
                HashMap<Coordinate, GameTile> tiles = (HashMap<Coordinate,GameTile>)gson.fromJson(json, HashMap.class);

                grid1.setTiles(tiles);
                gameView.setCurrentGridView(grid1);


    }
    */
    /*

    public void saveState(){

        SharedPreferences shared = getSharedPreferences("test", Context.MODE_PRIVATE);

        SharedPreferences.Editor data = shared.edit();
        counter++;
        data.putInt("counter", counter);

        data.commit();

        Toast.makeText(this, "Counter was saved", Toast.LENGTH_LONG).show();

        /*
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();

        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor data = shared.edit();

        HashMap<Coordinate, GameTile> tiles = grid1.getTiles();

        String json = gson.toJson(tiles);

        Log.e("json", json);



        data.putString("grid1",json);
        data.commit();



    }
    */

    /*
    @Override
    protected void onResume() {
        super.onResume();

        if(player == 1){
            gameView.setCurrentGridView(grid1);
            player = 2;
        } else {
            gameView.setCurrentGridView(grid2);
            player = 1;
        }
        setContentView(gameView);
    }
    */


    /*

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putInt("player", player);
        outState.putSerializable("tiles_player1", grid1.getTiles());
        outState.putSerializable("tiles_player2", grid2.getTiles());



        //outState.putSerializable("tiles", h);
        //outState.putSerializable("game_view", gameView);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        grid1.setTiles((HashMap<Coordinate,GameTile>) savedInstanceState.get("tiles_player1"));
        grid2.setTiles((HashMap<Coordinate,GameTile>) savedInstanceState.get("tiles_player2"));

        player = (int)savedInstanceState.get("player");
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
