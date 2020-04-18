package com.jorgegiance.chess_timer.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.jorgegiance.chess_timer.models.Game;
import com.jorgegiance.chess_timer.models.SettingsSet;

public class GameActivityViewModel extends AndroidViewModel {

    public SettingsSet defaultSet;
    public Game currentGame;


    public GameActivityViewModel( @NonNull Application application ) {
        super(application);
    }
}
