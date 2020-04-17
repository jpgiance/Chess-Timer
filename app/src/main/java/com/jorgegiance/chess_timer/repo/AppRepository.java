package com.jorgegiance.chess_timer.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.jorgegiance.chess_timer.db.AppDatabase;
import com.jorgegiance.chess_timer.db.GameDao;
import com.jorgegiance.chess_timer.db.SettingsSetDao;
import com.jorgegiance.chess_timer.models.Game;
import com.jorgegiance.chess_timer.models.SettingsSet;

import java.util.List;

public class AppRepository {

    private GameDao mGameDao;
    private LiveData<List<Game>> mAllGames;

    private SettingsSetDao mSettingsSetDao;
    private LiveData<List<SettingsSet>> mAllSettings;

    public AppRepository( Application application ) {

        AppDatabase db = AppDatabase.getDatabase(application);

        mGameDao = db.gameDao();
        mAllGames = mGameDao.getGames();

        mSettingsSetDao = db.settingsSetDao();
        mAllSettings = mSettingsSetDao.getSettings();

    }


    // Game related Queries

    public LiveData<List<Game>> getAllGames(){
        return mAllGames;
    }

    public void insertGame(Game game){
        AppDatabase.databaseWriteExecutor.execute(()->{
            mGameDao.insert(game);
        });
    }

    public void deleteAllGames(){
        AppDatabase.databaseWriteExecutor.execute(()->{
            mGameDao.deleteAll();
        });
    }

    public void deleteGame(Game game){
        AppDatabase.databaseWriteExecutor.execute(()->{
            mGameDao.delete(game);
        });
    }


    // SettingsSet related Queries

    public LiveData<List<SettingsSet>> getAllSettings(){
        return mAllSettings;
    }

    public void insertSettingsSet(SettingsSet set){
        AppDatabase.databaseWriteExecutor.execute(()->{
            mSettingsSetDao.insert(set);
        });
    }

    public void deleteAllSettingsSet(){
        AppDatabase.databaseWriteExecutor.execute(() ->{
            mSettingsSetDao.deleteAll();
        });
    }

    public void deleteSettingsSet(SettingsSet set){
        AppDatabase.databaseWriteExecutor.execute(()->{
            mSettingsSetDao.delete(set);
        });
    }
}
