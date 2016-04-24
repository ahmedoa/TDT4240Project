package com.g4.progark.battleships.controllers.menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.g4.progark.battleships.R;
import com.g4.progark.battleships.utility.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rout on 22.04.2016.
 */
public class GameMapSelectionListActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {

        public static final String[] titles = new String[] {"sea","naturesky","oceanwater","seabeach","sunset","wave"

        };


        public static final Integer[] images = { R.drawable.sea,R.drawable.naturesky,R.drawable.oceanwater,R.drawable.seabeach,R.drawable.sunset,
                R.drawable.wave};

        ListView listView;
        List<RowItem> rowItems;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game_map_selection_list);
            rowItems = new ArrayList<>();
            for (int i = 0; i < titles.length; i++) {
                RowItem item = new RowItem(images[i], titles[i]);
                rowItems.add(item);
            }

            listView = (ListView) findViewById(R.id.list);
            CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                    R.layout.list_item_map, rowItems);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
        long id) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Item " + (position + 1) + ": " + rowItems.get(position),
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            Constants.chosenGameMap = rowItems.get(position).toString().trim();
            Intent theIntent = new Intent(GameMapSelectionListActivity.this, ShipSelectionActivity.class);
            startActivity(theIntent);
        }
    }

