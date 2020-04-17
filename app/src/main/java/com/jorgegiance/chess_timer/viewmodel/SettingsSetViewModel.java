package com.jorgegiance.chess_timer.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jorgegiance.chess_timer.models.SettingsSet;
import com.jorgegiance.chess_timer.repo.AppRepository;

import java.util.List;

public class SettingsSetViewModel extends AndroidViewModel {

    private AppRepository mRepo;
    private LiveData<List<SettingsSet>> mAllSettingsSet;

    public SettingsSetViewModel( @NonNull Application application ) {
        super(application);

        mRepo = new AppRepository(application);
        mAllSettingsSet = mRepo.getAllSettings();
    }

    public LiveData<List<SettingsSet>> getAllSettingsSet(){
        return mAllSettingsSet;
    }

    public void insertSettingsSet(SettingsSet set){
        mRepo.insertSettingsSet(set);
    }

    public void deleteSettingsSet(SettingsSet set){
        mRepo.deleteSettingsSet(set);
    }

    public void deleteAllSettingsSet(){
        mRepo.deleteAllSettingsSet();
    }
}
