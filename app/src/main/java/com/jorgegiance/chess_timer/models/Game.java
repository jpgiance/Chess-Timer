package com.jorgegiance.chess_timer.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "game_table")
public class Game {

    @PrimaryKey
    @NonNull
    private String id;

    private String namePlayer1;
    private String namePlayer2;

    private int movesPlayer1;
    private int movesPlayer2;

    private int timePlayer1;
    private int timePlayer2;

    public Game( String id, String namePlayer1, String namePlayer2, int movesPlayer1, int movesPlayer2, int timePlayer1, int timePlayer2 ) {
        this.id = id;
        this.namePlayer1 = namePlayer1;
        this.namePlayer2 = namePlayer2;
        this.movesPlayer1 = movesPlayer1;
        this.movesPlayer2 = movesPlayer2;
        this.timePlayer1 = timePlayer1;
        this.timePlayer2 = timePlayer2;
    }

    public String getNamePlayer1() {
        return namePlayer1;
    }

    public void setNamePlayer1( String namePlayer1 ) {
        this.namePlayer1 = namePlayer1;
    }

    public String getNamePlayer2() {
        return namePlayer2;
    }

    public void setNamePlayer2( String namePlayer2 ) {
        this.namePlayer2 = namePlayer2;
    }

    public int getMovesPlayer1() {
        return movesPlayer1;
    }

    public void setMovesPlayer1( int movesPlayer1 ) {
        this.movesPlayer1 = movesPlayer1;
    }

    public int getMovesPlayer2() {
        return movesPlayer2;
    }

    public void setMovesPlayer2( int movesPlayer2 ) {
        this.movesPlayer2 = movesPlayer2;
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public int getTimePlayer1() {
        return timePlayer1;
    }

    public void setTimePlayer1( int timePlayer1 ) {
        this.timePlayer1 = timePlayer1;
    }

    public int getTimePlayer2() {
        return timePlayer2;
    }

    public void setTimePlayer2( int timePlayer2 ) {
        this.timePlayer2 = timePlayer2;
    }
}
