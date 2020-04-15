package com.jorgegiance.chess_timer.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.jorgegiance.chess_timer.models.Game;

import java.util.List;

@Dao
public interface GameDao {

    @Insert
    void insert( Game game );

    @Delete
    void delete(Game game);

    @Query("DELETE FROM game_table")
    void deleteAll();

    @Query("SELECT * from game_table ORDER BY id ASC")
    LiveData<List<Game>> getGames();

}
