package com.jorgegiance.chess_timer.ui;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jorgegiance.chess_timer.R;
import com.jorgegiance.chess_timer.util.GameSavedCallback;
import com.jorgegiance.chess_timer.util.Utils;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GameActivity extends AppCompatActivity implements
        View.OnClickListener,
        GameSavedCallback {


    // UI components
    private View mContentView;
    private View mControlsView;
    private View changeTurnButton;
    private TextView p1Clock, p2Clock;
    private ImageButton pauseP1Button, pauseP2Button, saveButton, resetButton, stopButton;

    // vars
    int player1Time = 0, player2Time = 0;
    int initialPlayer1Time = 1800, initialPlayer2Time = 1800;
    int player1Moves = 0, player2Moves = 0;
    int increment = -1;
    private boolean turnStatus = true;      //  true-> player1,      false-> player2
    private boolean pauseStatus = false;
    private static final String TAG = "GameActivity";

    CountDownTimer clock;






    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        mControlsView = findViewById(R.id.button_layer);
        mContentView = findViewById(R.id.fullscreen_content);
        changeTurnButton = findViewById(R.id.change_turn_button);
        p1Clock = findViewById(R.id.player_1_time);
        p2Clock = findViewById(R.id.player_2_time);
        pauseP1Button = findViewById(R.id.pause_player1_button);
        pauseP2Button = findViewById(R.id.pause_player2_button);
        saveButton = findViewById(R.id.save_game_button);
        resetButton = findViewById(R.id.reset_game_button);
        stopButton = findViewById(R.id.stop_game_button);

        setListeners();
        initTimers();
        setTurnToPlayer1();


    }


    private void setTurnToPlayer1() {
        turnStatus = true;
        changeTurnButton.setBackgroundColor(getResources().getColor(R.color.colorWhiteTimer));
    }

    private void setListeners() {
        mControlsView.setOnClickListener(this);
        mContentView.setOnClickListener(this);
        changeTurnButton.setOnClickListener(this);
        p1Clock.setOnClickListener(this);
        p2Clock.setOnClickListener(this);
        pauseP1Button.setOnClickListener(this);
        pauseP2Button.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
    }

    @Override
    protected void onPostCreate( Bundle savedInstanceState ) {
        super.onPostCreate(savedInstanceState);

        setFullscreen();
        blink();
    }

    private void setFullscreen() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }





    private void show() {

        mControlsView.setVisibility(View.VISIBLE);
    }

    private void hide() {

        mControlsView.setVisibility(View.GONE);
    }



    public void changeTurn() {

        if (turnStatus){
            player1Moves++;
            turnStatus = false;
            changeTurnButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDarkTimer));


        }else {
            player2Moves++;
            turnStatus = true;
            changeTurnButton.setBackgroundColor(getResources().getColor(R.color.colorWhiteTimer));
        }
        blink();
    }

    private void blink() {
        if (clock != null){
            clock.cancel();
        }

        clock = new CountDownTimer(30000000, 1000) {

            public void onTick(long millisUntilFinished) {
                if (turnStatus){
                    player1Time += increment;
                    p1Clock.setText(Utils.time2String(player1Time));

                }else {
                    player2Time += increment;
                    p2Clock.setText(Utils.time2String(player2Time));
                }

            }

            @Override
            public void onFinish() {
            }


        }.start();
    }

    @Override
    public void onClick( View v ) {

        switch (v.getId()){
            case R.id.button_layer:{
                break;
            }
            case R.id.change_turn_button:{
                if (!pauseStatus){
                    changeTurn();
                }
                break;
            }
            case R.id.pause_player1_button:
            case R.id.pause_player2_button: {
                pause();
                break;
            }
            case R.id.save_game_button:{
                showGameSaveDialog();
                break;
            }
            case R.id.reset_game_button:{
                reset();
                break;
            }
            case R.id.stop_game_button:{
                finish();
                break;
            }
        }

    }

    private void showGameSaveDialog() {
        FragmentManager fm = getSupportFragmentManager();
        GameSavedDialog dialog = new GameSavedDialog(this);
        dialog.show(fm, "fragment_alert");
    }

    private void reset() {
        initTimers();
        setTurnToPlayer1();
        pause();
    }

    private void pause() {

        if (!pauseStatus){

            if (clock != null){
                clock.cancel();
            }

            pauseStatus = true;
            pauseP1Button.setBackground(getResources().getDrawable(R.drawable.ic_play_arrow));
            pauseP2Button.setBackground(getResources().getDrawable(R.drawable.ic_play_arrow));

            show();

        }else{

            pauseStatus = false;
            pauseP1Button.setBackground(getResources().getDrawable(R.drawable.ic_pause_game));
            pauseP2Button.setBackground(getResources().getDrawable(R.drawable.ic_pause_game));

            hide();
            blink();
        }

    }


    private void initTimers(){
        player1Time = initialPlayer1Time;
        player2Time = initialPlayer2Time;

        p1Clock.setText(Utils.time2String(player1Time));
        p2Clock.setText(Utils.time2String(player2Time));

        player1Moves = 0;
        player2Moves = 0;
    }

    @Override
    public void gameSaved( String name ) {

        finish();
    }

    @Override
    public void dialogCancel() {
        setFullscreen();
        show();
    }


}
