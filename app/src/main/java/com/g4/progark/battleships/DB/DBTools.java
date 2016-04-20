package com.g4.progark.battleships.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eier on 15.04.2016.
 */
public class DBTools extends SQLiteOpenHelper {

    public DBTools(Context applicationContext) {

        super(applicationContext, "battleship.db", null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase database) {

        String query = "CREATE TABLE firepower1 (firepowerID INTEGER PRIMARY KEY, firepowerName TEXT," +
                "ammo INTEGER, damage DOUBLE)";
        String query2 = "CREATE TABLE firepower2 (firepowerID INTEGER PRIMARY KEY, firepowerName TEXT," +
                "ammo INTEGER, damage DOUBLE)";

        database.execSQL(query);
        database.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS firepower1";
        String query2 = "DROP TABLE IF EXISTS firepower2";

        database.execSQL(query);
        database.execSQL(query2);
        onCreate(database);

    }
    public void clearFirepowerTable() {

        SQLiteDatabase database = this.getWritableDatabase();
        onUpgrade(database, 0, 0);

    }

    public void insertFirepower(HashMap<String, String> queryValues) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("firepowerName", queryValues.get("firepowerName"));
        values.put("ammo", queryValues.get("ammo"));
        values.put("damage", queryValues.get("damage"));
        database.insert("firepower1", null, values);
        database.insert("firepower2", null, values);

        database.close();

    }

    public void updateFirepower(int firepowerId, int player) {

        SQLiteDatabase database = this.getWritableDatabase();
        String query = "UPDATE firepower" + player + "  SET AMMO = AMMO -1 WHERE firepowerID = " + (firepowerId +1);

        database.execSQL(query);
        database.close();
        Log.d("FUNKER", query);

    }

    public int checkAmmo(int firepowerId, int player) {

        String query = "SELECT ammo FROM firepower" + player + " WHERE firepowerID = " + firepowerId;
        int ammo = 0;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ammo = Integer.parseInt(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return ammo;
    }

    public ArrayList<HashMap<String, String>> getFirepower(int player) {

        ArrayList<HashMap<String, String>> firepowerArrayList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT * FROM firepower" + player;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                HashMap<String, String> firepowerMap = new HashMap<String, String>();

                firepowerMap.put("firepowerID", cursor.getString(0));
                firepowerMap.put("firepowerName", cursor.getString(1));
                firepowerMap.put("ammo", cursor.getString(2));
                firepowerMap.put("damage", cursor.getString(3));

                firepowerArrayList.add(firepowerMap);
            } while(cursor.moveToNext());
        }
        return firepowerArrayList;

    }
}