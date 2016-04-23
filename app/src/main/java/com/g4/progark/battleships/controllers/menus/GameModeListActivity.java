package com.g4.progark.battleships.controllers.menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.g4.progark.battleships.R;

/**
 * Created by rout on 18.04.2016.
 */
public class GameModeListActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode_list);
        ListView listView=(ListView)findViewById(R.id.listView);
        ViewGroup headerView=(ViewGroup)getLayoutInflater().inflate(R.layout.header,listView,false);
        listView.addHeaderView(headerView);
        final String[] items=getResources().getStringArray(R.array.list_game);
        ListAdapter adapter=new ListAdapter(this,R.layout.rowlayout,R.id.txtmode,items);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               if (position == 1) {
                   Intent intent = new Intent(GameModeListActivity.this, GameMapSelectionListActivity.class);
                   startActivity(intent);
                   Toast.makeText(getApplicationContext(), "Standard game mode",
                           Toast.LENGTH_LONG).show();
               }
               else if (position == 2){
                   Intent intent = new Intent(GameModeListActivity.this, GameMapSelectionListActivity.class);
                   startActivity(intent);
                   Toast.makeText(getApplicationContext(), "Oneshot game mode",
                           Toast.LENGTH_LONG).show();
               }
               else if (position == 3){
                   Intent intent = new Intent(GameModeListActivity.this, GameMapSelectionListActivity.class);
                   startActivity(intent);
                   Toast.makeText(getApplicationContext(), "Advanced game mode",
                           Toast.LENGTH_LONG).show();
               }
           }
       });
        listView.setAdapter(adapter);
    }

   /** @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);

    }**/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
