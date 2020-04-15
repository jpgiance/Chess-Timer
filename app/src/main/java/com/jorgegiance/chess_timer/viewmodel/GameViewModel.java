package com.jorgegiance.chess_timer.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jorgegiance.chess_timer.models.Game;
import com.jorgegiance.chess_timer.repo.AppRepository;

import java.util.List;

public class GameViewModel extends AndroidViewModel {

    private AppRepository mRepo;
    private LiveData<List<Game>> mAllGames;

    public GameViewModel( @NonNull Application application ) {
        super(application);

        mRepo = new AppRepository(application);
        mAllGames = mRepo.getAllGames();
    }

    LiveData<List<Game>> getAllGames(){
        return mAllGames;
    }

    public void insertGame(Game game){
        mRepo.insertGame(game);
    }

    public void deleteGame(Game game){
        mRepo.deleteGame(game);
    }

    public void deleteAllGames(){
        mRepo.deleteAllGames();
    }
}
