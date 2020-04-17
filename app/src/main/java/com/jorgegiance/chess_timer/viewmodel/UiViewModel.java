package com.jorgegiance.chess_timer.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.jorgegiance.chess_timer.models.SettingsSet;

import java.util.List;

public class UiViewModel extends AndroidViewModel {


    public SettingsSet defaultSet;
    public List<SettingsSet> settingsSetsList;

    public UiViewModel( @NonNull Application application ) {
        super(application);
    }


}
