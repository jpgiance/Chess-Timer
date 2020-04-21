package com.jorgegiance.chess_timer.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jorgegiance.chess_timer.models.Game;
import com.jorgegiance.chess_timer.models.SettingsSet;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {


    public SettingsSet defaultSet;
    private List<SettingsSet> settingsSetsList;
    private List<Game> gamesList;
    private SettingsSet settingsSetSelected;
    private MutableLiveData<Boolean> settingsSelectedStatus = new MutableLiveData<>();


    public MainActivityViewModel( @NonNull Application application ) {
        super(application);
    }


    public List<SettingsSet> getSettingsSetsList() {
        return settingsSetsList;
    }

    public void setSettingsSetsList( List<SettingsSet> settingsSetsList ) {
        this.settingsSetsList = settingsSetsList;
    }

    public SettingsSet getSettingsSetSelected() {
        return settingsSetSelected;
    }

    public void setSettingsSetSelected( SettingsSet settingsSetSelected ) {
        this.settingsSetSelected = settingsSetSelected;
    }

    public MutableLiveData<Boolean> getSettingsSelectedStatus() {
        return settingsSelectedStatus;
    }

    public void setSettingsSelectedStatus( Boolean aBoolean ) {
        this.settingsSelectedStatus.setValue(aBoolean);
    }

    public void deleteSetInSettingsSetList( Boolean aBoolean ) {
        this.settingsSelectedStatus.setValue(aBoolean);
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    public void setGamesList( List<Game> gamesList ) {
        this.gamesList = gamesList;
    }
}
