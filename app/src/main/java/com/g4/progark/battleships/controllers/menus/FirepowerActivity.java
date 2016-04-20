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
    Firepower currentFirepower = null;
    int currentPlayer;
    Intent intent;
    TextView firepowerName;
    ListView lv;
    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firepower2);
        currentPlayer = 1;
        ArrayList<HashMap<String, String>> firepowerArrayList = dbTools.getFirepower(currentPlayer);

        if(firepowerArrayList.size() != 0) {
         /*s   ArrayList<Integer> empty = new ArrayList<Integer>();
            for(int i = 0; i < firepowerArrayList.size(); i++) {
                Log.d("Ayyyy", Integer.toString(i));
                if(firepowerArrayList.get(i).get("ammo").equals("0")) {
                    Log.d("Ayyyy", firepowerArrayList.get(i).get("firepowerID"));
                    Log.d("Ayyyy", firepowerArrayList.get(i).get("firepowerName"));
                    Log.d("Ayyyy", firepowerArrayList.get(i).get("ammo"));
                    empty.add(i +1);
                }

            }
            */

            ListView listView = getListView();
            listView.setOnItemClickListener(new OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                    firepowerName = (TextView) view.findViewById(R.id.firepowerId);
                    String firepowerIdValue = firepowerName.getText().toString();

                    int i = Integer.parseInt(firepowerIdValue);
                    if(dbTools.checkAmmo(Integer.parseInt(firepowerIdValue), currentPlayer) != 0)
                        setFirepower(Integer.parseInt(firepowerIdValue) -1);

                    //intent back to grid + putExtra type of id on firepower

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



    }

    public void setFirepower(int i) {
        currentFirepower = firePowerFactory.setFirePower(i);
        Log.d("Ayyyy", currentFirepower.getName());
        dbTools.updateFirepower(i, currentPlayer);
        //Intent intent = new Intent(this, IntermediateActivity.class);
        Intent intent = new Intent(this, ShipSelectionActivity.class);
        startActivity(intent);
    }

}
