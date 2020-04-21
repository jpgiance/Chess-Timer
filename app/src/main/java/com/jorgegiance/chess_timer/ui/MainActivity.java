package com.jorgegiance.chess_timer.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.jorgegiance.chess_timer.R;
import com.jorgegiance.chess_timer.models.Game;
import com.jorgegiance.chess_timer.models.SettingsSet;
import com.jorgegiance.chess_timer.ui.dialogs.GameListDialog;
import com.jorgegiance.chess_timer.ui.dialogs.LoadSettingsDialog;
import com.jorgegiance.chess_timer.ui.dialogs.PlayerNameDialog;
import com.jorgegiance.chess_timer.ui.dialogs.SettingSavedDialog;
import com.jorgegiance.chess_timer.ui.dialogs.TimerPickerDialog;
import com.jorgegiance.chess_timer.util.NameDialogCallback;
import com.jorgegiance.chess_timer.util.TimerPickerDialogCallback;
import com.jorgegiance.chess_timer.util.SettingSavedCallback;
import com.jorgegiance.chess_timer.util.Utils;
import com.jorgegiance.chess_timer.viewmodel.GameViewModel;
import com.jorgegiance.chess_timer.viewmodel.SettingsSetViewModel;
import com.jorgegiance.chess_timer.viewmodel.MainActivityViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        NameDialogCallback,
        TimerPickerDialogCallback,
        SettingSavedCallback,
        AdapterView.OnItemSelectedListener {

    // UI components
    private TextView player1Name, player2Name;
    private TextView player1Timer, player2Timer, delayTime;
    private ImageButton saveButton, playButton;
    private Spinner timerSpinner, delaySpinner;
    private MenuItem loadSettingButton, showGameListButton;

    // vars
    private int dialogState = 0;
    private int timerState = 0;

    private SettingsSetViewModel mSettingsSetViewModel;
    private GameViewModel mGameViewModel;
    private MainActivityViewModel mMainActivityViewModel;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSettingsSetViewModel = new ViewModelProvider(this).get(SettingsSetViewModel.class);
        mGameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);


        player1Name = findViewById(R.id.player_1_name);
        player2Name = findViewById(R.id.player_2_name);
        saveButton = findViewById(R.id.save_settings_button);
        playButton = findViewById(R.id.main_start_button);
        player1Timer = findViewById(R.id.player_1_timer);
        player2Timer = findViewById(R.id.player_2_timer);
        delayTime = findViewById(R.id.delay_timer);
        loadSettingButton = findViewById(R.id.settings);
        showGameListButton = findViewById(R.id.games);

        setSpinnerAdapter();
        setListeners();
        initSettingSet();
        initObserver();


    }

    private void initObserver() {

        mGameViewModel.getAllGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged( List<Game> games ) {
                mMainActivityViewModel.setGamesList(games);
            }
        });


        mSettingsSetViewModel.getAllSettingsSet().observe(this, new Observer<List<SettingsSet>>() {
            @Override
            public void onChanged( List<SettingsSet> settingsSets ) {
                mMainActivityViewModel.setSettingsSetsList(settingsSets);

            }
        });

        mMainActivityViewModel.getSettingsSelectedStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged( Boolean aBoolean ) {
                if (aBoolean){
                    settingsSetSelected();
                    mMainActivityViewModel.setSettingsSelectedStatus(false);
                }
            }
        });


    }


    private void initSettingSet() {

        mMainActivityViewModel.defaultSet = new SettingsSet(
                getResources().getString(R.string.default_settingsSet_name),
                player1Name.getText().toString(),
                player2Name.getText().toString(),
                Utils.string2TotalSeconds(player1Timer.getText().toString()),
                Utils.string2TotalSeconds(player2Timer.getText().toString()),
                timerSpinner.getSelectedItemPosition(),
                delaySpinner.getSelectedItemPosition(),
                Utils.string2TotalSeconds(delayTime.getText().toString())
        );

    }

    private void setListeners() {
        player1Name.setOnClickListener(this);
        player2Name.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
        player1Timer.setOnClickListener(this);
        player2Timer.setOnClickListener(this);
        delayTime.setOnClickListener(this);
        timerSpinner.setOnItemSelectedListener(this);
        delaySpinner.setOnItemSelectedListener(this);


    }


    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {


        switch (item.getItemId()) {
            case R.id.settings: {
                showLoadSettingsDialog();
                return true;
            }
            case R.id.games: {
                showGameListDialog();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showGameListDialog() {
        FragmentManager fm = getSupportFragmentManager();
        GameListDialog dialog = new GameListDialog();
        dialog.show(fm, getResources().getString(R.string.dialog_tag));

    }

    private void showLoadSettingsDialog() {

        FragmentManager fm = getSupportFragmentManager();
        LoadSettingsDialog dialog = new LoadSettingsDialog();
        dialog.show(fm, getResources().getString(R.string.dialog_tag));

    }


    private void setSpinnerAdapter() {

        timerSpinner = findViewById(R.id.timer_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> timerAdapter = ArrayAdapter.createFromResource(this,
                R.array.timer_spinner_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        timerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        timerSpinner.setAdapter(timerAdapter);


        delaySpinner = findViewById(R.id.delay_spinner);
        ArrayAdapter<CharSequence> delayAdapter = ArrayAdapter.createFromResource(this,
                R.array.delay_spinner_array, R.layout.spinner_item);
        delayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        delaySpinner.setAdapter(delayAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);

        return true;
    }

    @Override
    public void onClick( View view ) {

        switch (view.getId()) {
            case R.id.player_1_name: {
                dialogState = 1;
                showDialog(player1Name.getText().toString());
                break;
            }
            case R.id.player_2_name: {
                dialogState = 2;
                showDialog(player2Name.getText().toString());
                break;
            }
            case R.id.save_settings_button: {
                if (mMainActivityViewModel.getSettingsSetsList() != null) {
                    if (mMainActivityViewModel.getSettingsSetsList().size() < 10) {
                        showSettingSavedDialog();
                    } else {
                        showSettingAlert();
                    }
                } else {
                    showSettingSavedDialog();
                }

                break;
            }
            case R.id.main_start_button: {
                Intent gameIntent = new Intent(this, GameActivity.class);
                gameIntent.putExtra(getResources().getString(R.string.settingsSet_key), mMainActivityViewModel.defaultSet);
                startActivity(gameIntent);
                break;
            }
            case R.id.player_1_timer: {
                timerState = 1;
                showTimePickerDialog(player1Timer.getText().toString());
                break;
            }
            case R.id.player_2_timer: {
                timerState = 2;
                showTimePickerDialog(player2Timer.getText().toString());
                break;
            }
            case R.id.delay_timer: {
                timerState = 3;
                showTimePickerDialog(delayTime.getText().toString());
                break;
            }


        }
    }

    private void showSettingAlert() {
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.save_settings_alert_title))
                .setMessage(getResources().getString(R.string.save_settings_alert_text))
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void showDialog( String name ) {
        FragmentManager fm = getSupportFragmentManager();
        PlayerNameDialog nameDialog = new PlayerNameDialog(this, name);
        nameDialog.show(fm, getResources().getString(R.string.dialog_tag));

    }

    public void showSettingSavedDialog() {
        FragmentManager fm = getSupportFragmentManager();
        SettingSavedDialog settingDialog = new SettingSavedDialog(this);
        settingDialog.show(fm, getResources().getString(R.string.dialog_tag));
    }


    @Override
    public void nameSaved( String name ) {
        if (dialogState == 1) {
            player1Name.setText(name);
            mMainActivityViewModel.defaultSet.setNamePlayer1(name);
        }
        if (dialogState == 2) {
            player2Name.setText(name);
            mMainActivityViewModel.defaultSet.setNamePlayer2(name);
        }
    }


    public void showTimePickerDialog( String time ) {
        FragmentManager fm = getSupportFragmentManager();
        TimerPickerDialog timerDialog = new TimerPickerDialog(this, time, timerState);
        timerDialog.show(fm, getResources().getString(R.string.dialog_tag));

    }


    @Override
    public void timeSaved( String time ) {

        if (timerState == 1) {
            player1Timer.setText(time);
            mMainActivityViewModel.defaultSet.setTimerPlayer1(Utils.string2TotalSeconds(time));
        }
        if (timerState == 2) {
            player2Timer.setText(time);
            mMainActivityViewModel.defaultSet.setTimerPlayer2(Utils.string2TotalSeconds(time));
        }
        if (timerState == 3) {
            delayTime.setText(time);
            mMainActivityViewModel.defaultSet.setDelayTime(Utils.string2TotalSeconds(time));
        }
    }

    @Override
    public void settingSaved( String name ) {
        mMainActivityViewModel.defaultSet.setId(name);
        mSettingsSetViewModel.insertSettingsSet(mMainActivityViewModel.defaultSet);
    }


    @Override
    public void onItemSelected( AdapterView<?> parent, View view, int position, long id ) {

        switch (parent.getId()) {
            case R.id.timer_spinner: {
                mMainActivityViewModel.defaultSet.setTimerMode(position);
                break;
            }
            case R.id.delay_spinner: {
                mMainActivityViewModel.defaultSet.setDelayMode(position);
                break;
            }
        }


    }

    @Override
    public void onNothingSelected( AdapterView<?> parent ) {

    }


    public void settingsSetSelected() {


        player1Name.setText(mMainActivityViewModel.getSettingsSetSelected().getNamePlayer1());
        player2Name.setText(mMainActivityViewModel.getSettingsSetSelected().getNamePlayer2());
        player1Timer.setText(Utils.time2String(mMainActivityViewModel.getSettingsSetSelected().getTimerPlayer1()));
        player2Timer.setText(Utils.time2String(mMainActivityViewModel.getSettingsSetSelected().getTimerPlayer2()));
        timerSpinner.setSelection(mMainActivityViewModel.getSettingsSetSelected().getTimerMode());
        delaySpinner.setSelection(mMainActivityViewModel.getSettingsSetSelected().getDelayMode());
        delayTime.setText(Utils.time2String(mMainActivityViewModel.getSettingsSetSelected().getDelayTime()));

        initSettingSet();


    }
}
