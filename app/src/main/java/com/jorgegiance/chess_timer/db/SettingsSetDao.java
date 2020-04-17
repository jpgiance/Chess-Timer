package com.jorgegiance.chess_timer.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.jorgegiance.chess_timer.models.SettingsSet;

import java.util.List;

@Dao
public interface SettingsSetDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert( SettingsSet set );

    @Delete
    void delete(SettingsSet set);

    @Query("DELETE FROM settings_table")
    void deleteAll();

    @Query("SELECT * from settings_table ORDER BY id ASC")
    LiveData<List<SettingsSet>> getSettings();
}
