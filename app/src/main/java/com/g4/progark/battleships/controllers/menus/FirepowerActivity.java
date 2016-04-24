package com.g4.progark.battleships.controllers.menus;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ListView;
import android.content.Intent;
import android.widget.AdapterView.OnItemClickListener;

import com.g4.progark.battleships.DB.DBTools;
import com.g4.progark.battleships.R;
import com.g4.progark.battleships.models.FirepowerFactory;
import com.g4.progark.battleships.utility.Constants;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eier on 14.04.2016.
 */
public class FirepowerActivity extends ListActivity {

    Intent intent;
    TextView firepowerName;
    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firepower);

        intent = getIntent();
        ArrayList<HashMap<String, String>> firepowerArrayList = dbTools.getFirepower(Constants.CURRENT_PLAYER);

        if(firepowerArrayList.size() != 0) {

            ListView listView = getListView();
            listView.setOnItemClickListener(new OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                    firepowerName = (TextView) view.findViewById(R.id.firepowerId);
                    String firepowerIdValue = firepowerName.getText().toString();

                    if(Integer.parseInt(firepowerIdValue) -1 == 0 || dbTools.checkAmmo(Integer.parseInt(firepowerIdValue), Constants.CURRENT_PLAYER) != 0) {
                        Constants.currentFirePower = Integer.parseInt(firepowerIdValue) -1;
                        getFirepower();
                    }
                }
            });

            ListAdapter listAdapter = new SimpleAdapter(FirepowerActivity.this, firepowerArrayList,
                    R.layout.firepower_entry, new String[] {"firepowerID", "firepowerName",
                    "ammo" },new int[] {R.id.firepowerId, R.id.firepowerName,
                    R.id.firepowerAmmo} );

            listView.setAdapter(listAdapter);
        }
    }
    public void getFirepower() {
        if(Constants.currentFirePower != 0)
            dbTools.updateFirepower(Constants.currentFirePower, Constants.CURRENT_PLAYER);
        Intent gameViewIntent = new Intent(this, GameViewActivity.class);
        gameViewIntent.putExtra("selectedFirePower", Constants.currentFirePower);
        startActivity(gameViewIntent);

    }

}
