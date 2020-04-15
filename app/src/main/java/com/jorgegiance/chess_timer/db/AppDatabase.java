package com.jorgegiance.chess_timer.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jorgegiance.chess_timer.models.Game;
import com.jorgegiance.chess_timer.models.SettingsSet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Game.class, SettingsSet.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GameDao gameDao();
    public abstract SettingsSetDao settingsSetDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBERS_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBERS_OF_THREADS);

    public static AppDatabase getDatabase( final Context context ) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
