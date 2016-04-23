package com.g4.progark.battleships.controllers.menus;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.g4.progark.battleships.models.Firepower;
import com.g4.progark.battleships.models.FirepowerFactory;
import com.g4.progark.battleships.utility.Constants;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;
import java.util.HashMap;

/**
 * Created by Eier on 14.04.2016.
 */
public class FirepowerActivity extends ListActivity {

    FirepowerFactory firePowerFactory = new FirepowerFactory();
    Intent intent;
    TextView firepowerName;
    ListView lv;
    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firepower2);

        intent = getIntent();
        ArrayList<HashMap<String, String>> firepowerArrayList = dbTools.getFirepower(Constants.CURRENT_PLAYER);

        if(firepowerArrayList.size() != 0) {

            ListView listView = getListView();
            listView.setOnItemClickListener(new OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                    firepowerName = (TextView) view.findViewById(R.id.firepowerId);
                    String firepowerIdValue = firepowerName.getText().toString();

                    int i = Integer.parseInt(firepowerIdValue);
                    if(dbTools.checkAmmo(Integer.parseInt(firepowerIdValue), Constants.CURRENT_PLAYER) != 0) {
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
        dbTools.updateFirepower(Constants.currentFirePower, Constants.CURRENT_PLAYER);
        Intent gameViewIntent = new Intent(this, GameViewActivity.class);
        gameViewIntent.putExtra("selectedFirePower", Constants.currentFirePower);
        startActivity(gameViewIntent);

    }

}
