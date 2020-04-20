package com.jorgegiance.chess_timer.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.jorgegiance.chess_timer.models.Game;
import com.jorgegiance.chess_timer.models.SettingsSet;

public class GameActivityViewModel extends AndroidViewModel {



    public SettingsSet defaultSet;
    public Game currentGame;
    private MutableLiveData<Boolean> gameSavedStatus = new MutableLiveData<>();
    private MutableLiveData<Boolean> gameDialogCancelStatus = new MutableLiveData<>();
    private int increment;


    public GameActivityViewModel( @NonNull Application application ) {
        super(application);
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement( int increment ) {
        this.increment = increment;
    }

    public MutableLiveData<Boolean> getGameDialogCancelStatus() {
        return gameDialogCancelStatus;
    }

    public void setGameDialogCancelStatus( Boolean aBoolean ) {
        this.gameDialogCancelStatus.setValue(aBoolean);
    }

    public SettingsSet getDefaultSet() {
        return defaultSet;
    }

    public void setDefaultSet( SettingsSet defaultSet ) {
        this.defaultSet = defaultSet;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame( Game currentGame ) {
        this.currentGame = currentGame;
    }

    public MutableLiveData<Boolean> getGameSavedStatus() {
        return gameSavedStatus;
    }

    public void setGameSavedStatus( Boolean aBoolean ) {
        this.gameSavedStatus.setValue(aBoolean);
    }


}
