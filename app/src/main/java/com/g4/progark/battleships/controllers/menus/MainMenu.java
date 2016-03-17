package com.g4.progark.battleships.controllers.menus;

import android.view.View;

import com.g4.progark.battleships.controllers.AndroidMenu;

/**
 * Created by Kristian on 11/03/2016.
 */
public class MainMenu extends AndroidMenu {



    public void goToOptions(View view) {
        goTo(OptionsMenu.class);
    }

    public void goToGameModes(View view) {
        goTo(GameMode.class);
    }
    public void goToLobby(View view) {
        goTo(Lobby.class);
    }

}
