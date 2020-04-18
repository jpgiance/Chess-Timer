package com.jorgegiance.chess_timer.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jorgegiance.chess_timer.models.SettingsSet;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {


    public SettingsSet defaultSet;
    private List<SettingsSet> settingsSetsList;
    private int settingsSetSelected;
    private MutableLiveData<Boolean> settingsSelectedStatus = new MutableLiveData<Boolean>();


    public MainActivityViewModel( @NonNull Application application ) {
        super(application);
    }


    public List<SettingsSet> getSettingsSetsList() {
        return settingsSetsList;
    }

    public void setSettingsSetsList( List<SettingsSet> settingsSetsList ) {
        this.settingsSetsList = settingsSetsList;
    }

    public int getSettingsSetSelected() {
        return settingsSetSelected;
    }

    public void setSettingsSetSelected( int settingsSetSelected ) {
        this.settingsSetSelected = settingsSetSelected;
    }

    public MutableLiveData<Boolean> getSettingsSelectedStatus() {
        return settingsSelectedStatus;
    }

    public void setSettingsSelectedStatus( Boolean aBoolean ) {
        this.settingsSelectedStatus.setValue(aBoolean);
    }
}
