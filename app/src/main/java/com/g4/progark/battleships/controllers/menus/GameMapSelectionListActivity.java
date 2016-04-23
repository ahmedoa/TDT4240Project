package com.g4.progark.battleships.controllers.menus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.g4.progark.battleships.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rout on 22.04.2016.
 */
public class GameMapSelectionListActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {

        public static final String[] titles = new String[] { "Grass","NatureSky","Ocean Water","SeaBeach","Sunset","Wave"

        };


        public static final Integer[] images = { R.drawable.grass,R.drawable.nature_sky,R.drawable.ocean_water,R.drawable.sea_beach,R.drawable.sunset_sea,
                R.drawable.wave};

        ListView listView;
        List<RowItem> rowItems;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game_map_selection_list);

            rowItems = new ArrayList<RowItem>();
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
        }
    }

