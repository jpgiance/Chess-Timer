package com.jorgegiance.chess_timer.models;

public class Game {

    private String namePlayer1;
    private String namePlayer2;

    private int movesPlayer1;
    private int movesPlayer2;

    public Game( String namePlayer1, String namePlayer2, int movesPlayer1, int movesPlayer2 ) {
        this.namePlayer1 = namePlayer1;
        this.namePlayer2 = namePlayer2;
        this.movesPlayer1 = movesPlayer1;
        this.movesPlayer2 = movesPlayer2;
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
}
