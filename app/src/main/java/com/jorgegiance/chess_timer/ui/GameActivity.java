package com.jorgegiance.chess_timer.ui;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jorgegiance.chess_timer.R;
import com.jorgegiance.chess_timer.models.Game;
import com.jorgegiance.chess_timer.ui.dialogs.GameSavedDialog;
import com.jorgegiance.chess_timer.util.Utils;
import com.jorgegiance.chess_timer.viewmodel.GameActivityViewModel;
import com.jorgegiance.chess_timer.viewmodel.GameViewModel;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GameActivity extends AppCompatActivity implements
        View.OnClickListener {


    // UI components
    private View mContentView;
    private View mControlsView;
    private View changeTurnButton;
    private TextView p1Clock, p2Clock;
    private ImageButton pauseP1Button, pauseP2Button, saveButton, resetButton, stopButton;

    // vars
//    int player1Time = 0, player2Time = 0;
//    int initialPlayer1Time = 1800, initialPlayer2Time = 1800;
//    int player1Moves = 0, player2Moves = 0;
//    int increment = -1;
    private boolean turnStatus = true;      //  true-> player1,      false-> player2
    private boolean pauseStatus = false;
    private static final String TAG = "GameActivity";

    private CountDownTimer clock;
    private GameViewModel mGameViewModel;
    private GameActivityViewModel mGameActivityViewModel;







    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        mGameActivityViewModel = new ViewModelProvider(this).get(GameActivityViewModel.class);

        // ....getting intent from previous Activity
        Intent gameIntent = getIntent();

        if (gameIntent.hasExtra(getResources().getString(R.string.settingsSet_key))) {

            mGameActivityViewModel.defaultSet = gameIntent.getParcelableExtra(getResources().getString(R.string.settingsSet_key));
            initCurrentGame();

        }

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
        initObserver();
        initGame();


    }

    private void initObserver() {

        mGameActivityViewModel.getGameSavedStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged( Boolean aBoolean ) {
                if (aBoolean){
                    finish();
                    mGameActivityViewModel.setGameSavedStatus(false);
                }

            }
        });

        mGameActivityViewModel.getGameDialogCancelStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged( Boolean aBoolean ) {
                if (aBoolean){
                    setFullscreen();
                    show();
                    mGameActivityViewModel.setGameDialogCancelStatus(false);
                }

            }
        });

    }

    private void initCurrentGame() {

        mGameActivityViewModel.currentGame = new Game(
                getResources().getString(R.string.default_game_name),
                mGameActivityViewModel.defaultSet.getNamePlayer1(),
                mGameActivityViewModel.defaultSet.getNamePlayer2(),
                0,
                0,
                mGameActivityViewModel.defaultSet.getTimerPlayer1(),
                mGameActivityViewModel.defaultSet.getTimerPlayer2(),
                0);


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
        checkSimpleDelay();
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
            mGameActivityViewModel.currentGame.setMovesPlayer1(mGameActivityViewModel.currentGame.getMovesPlayer1() - mGameActivityViewModel.getIncrement());
            mGameActivityViewModel.currentGame.setTimePlayer2(mGameActivityViewModel.currentGame.getTimePlayer2() - mGameActivityViewModel.getIncrement());
            turnStatus = false;
            changeTurnButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDarkTimer));


        }else {
            mGameActivityViewModel.currentGame.setMovesPlayer2(mGameActivityViewModel.currentGame.getMovesPlayer2() - mGameActivityViewModel.getIncrement());
            mGameActivityViewModel.currentGame.setTimePlayer1(mGameActivityViewModel.currentGame.getTimePlayer1() - mGameActivityViewModel.getIncrement());
            turnStatus = true;
            changeTurnButton.setBackgroundColor(getResources().getColor(R.color.colorWhiteTimer));
        }

        checkSimpleDelay();


    }

    private void checkSimpleDelay() {
        if (mGameActivityViewModel.getDefaultSet().getDelayMode() == 0 && mGameActivityViewModel.getDefaultSet().getTimerMode() == 1) {

            if (clock != null){
                clock.cancel();
            }

           clock = new CountDownTimer((long) mGameActivityViewModel.getDefaultSet().getDelayTime() * 1000, 1000 ) {
               @Override
               public void onTick( long millisUntilFinished ) {


               }

               @Override
               public void onFinish() {
                   blink();
               }
           }.start();

        }else {
            blink();
        }
    }


    private void blink() {

        if (clock != null){
            clock.cancel();
        }



        addDelay();


        clock = new CountDownTimer(30000000, 1000) {


            public void onTick(long millisUntilFinished) {

                if (turnStatus){
                    mGameActivityViewModel.currentGame.setTimePlayer1(mGameActivityViewModel.currentGame.getTimePlayer1() + mGameActivityViewModel.getIncrement());

                    p1Clock.setText(Utils.time2String(mGameActivityViewModel.currentGame.getTimePlayer1()));

                }else {
                    mGameActivityViewModel.currentGame.setTimePlayer2(mGameActivityViewModel.currentGame.getTimePlayer2() + mGameActivityViewModel.getIncrement());

                    p2Clock.setText(Utils.time2String(mGameActivityViewModel.currentGame.getTimePlayer2()));
                }

            }

            @Override
            public void onFinish() {
            }


        }.start();



    }

    private void addDelay() {

        if (mGameActivityViewModel.getDefaultSet().getDelayMode() == 1 && mGameActivityViewModel.getDefaultSet().getTimerMode() == 1){

            if (turnStatus){
                mGameActivityViewModel.currentGame.setTimePlayer1(mGameActivityViewModel.currentGame.getTimePlayer1() + mGameActivityViewModel.getDefaultSet().getDelayTime());

            }else {
                mGameActivityViewModel.currentGame.setTimePlayer2(mGameActivityViewModel.currentGame.getTimePlayer2() + mGameActivityViewModel.getDefaultSet().getDelayTime());

            }
        }

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
        GameSavedDialog dialog = new GameSavedDialog();
        dialog.show(fm, "fragment_alert");
    }

    private void reset() {
        this.recreate();
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


    private void initGame(){

        // Timer Mode: Increment   or   Decrement  -> set increment to  +1  or  -1
        if(mGameActivityViewModel.getDefaultSet().getTimerMode() == 0){
            mGameActivityViewModel.setIncrement(1);
        }else if(mGameActivityViewModel.getDefaultSet().getTimerMode() == 1 || mGameActivityViewModel.getDefaultSet().getTimerMode() == 2){
            mGameActivityViewModel.setIncrement(-1);
        }

        // Timer Mode: Increment
        if (mGameActivityViewModel.getDefaultSet().getTimerMode() == 0){
            mGameActivityViewModel.currentGame.setTimePlayer1(-1);
            mGameActivityViewModel.currentGame.setTimePlayer2(0);
        }else if (mGameActivityViewModel.getDefaultSet().getTimerMode() == 1 && mGameActivityViewModel.getDefaultSet().getDelayMode() == 0){
            mGameActivityViewModel.currentGame.setTimePlayer1(mGameActivityViewModel.defaultSet.getTimerPlayer1());
            mGameActivityViewModel.currentGame.setTimePlayer2(mGameActivityViewModel.defaultSet.getTimerPlayer2());
        }else{
            mGameActivityViewModel.currentGame.setTimePlayer1(mGameActivityViewModel.defaultSet.getTimerPlayer1() - mGameActivityViewModel.getIncrement());
            mGameActivityViewModel.currentGame.setTimePlayer2(mGameActivityViewModel.defaultSet.getTimerPlayer2());

        }


        // Timer Mode: Increment   or  Decrement without Delay  -> set Delay to 0
        if (mGameActivityViewModel.getDefaultSet().getTimerMode() == 2 || mGameActivityViewModel.getDefaultSet().getTimerMode() == 0){
            mGameActivityViewModel.getDefaultSet().setDelayTime(0);
        }

        setTurnToPlayer1();


        p1Clock.setText(Utils.time2String(mGameActivityViewModel.currentGame.getTimePlayer1()));
        p2Clock.setText(Utils.time2String(mGameActivityViewModel.currentGame.getTimePlayer2()));


        mGameActivityViewModel.currentGame.setMovesPlayer1(0);
        mGameActivityViewModel.currentGame.setMovesPlayer2(0);
    }





}
