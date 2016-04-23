package com.g4.progark.battleships.controllers;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Kristian on 11/03/2016.
 */
public class AndroidMenu extends Activity {

    private static boolean devMode;

    // Change controller and view, to be used only by xml controllers
    public void goTo(Class javaClass) {
        Intent intent = new Intent(this, javaClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // Can change animation between transitions of the xmls
        startActivity(intent);
        finish(); //End previous activity
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed(); // Possible to create custom back buttons. This is default.
        overridePendingTransition(0, 0);
    }



    private void setDevMode(boolean isDevMode){
        devMode = isDevMode;
    }

    public static Boolean getDevMode() {
        return devMode;
    }
}

