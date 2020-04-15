package com.jorgegiance.chess_timer.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "settings_table")
public class SettingsSet {

    @PrimaryKey
    @NonNull
    private String id;

    private String namePlayer1;
    private String namePlayer2;

    private int timerPlayer1;
    private int timerPlayer2;

    private String timerMode;
    private String delayMode;
    private int delayTime;

    public SettingsSet( String id, String namePlayer1, String namePlayer2, int timerPlayer1, int timerPlayer2, String timerMode, String delayMode, int delayTime ) {
        this.id = id;
        this.namePlayer1 = namePlayer1;
        this.namePlayer2 = namePlayer2;
        this.timerPlayer1 = timerPlayer1;
        this.timerPlayer2 = timerPlayer2;
        this.timerMode = timerMode;
        this.delayMode = delayMode;
        this.delayTime = delayTime;
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

    public int getTimerPlayer1() {
        return timerPlayer1;
    }

    public void setTimerPlayer1( int timerPlayer1 ) {
        this.timerPlayer1 = timerPlayer1;
    }

    public int getTimerPlayer2() {
        return timerPlayer2;
    }

    public void setTimerPlayer2( int timerPlayer2 ) {
        this.timerPlayer2 = timerPlayer2;
    }

    public String getTimerMode() {
        return timerMode;
    }

    public void setTimerMode( String timerMode ) {
        this.timerMode = timerMode;
    }

    public String getDelayMode() {
        return delayMode;
    }

    public void setDelayMode( String delayMode ) {
        this.delayMode = delayMode;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime( int delayTime ) {
        this.delayTime = delayTime;
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }
}
