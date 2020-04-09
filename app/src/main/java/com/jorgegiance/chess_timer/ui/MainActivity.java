package com.jorgegiance.chess_timer.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.jorgegiance.chess_timer.R;
import com.jorgegiance.chess_timer.util.NameDialogCallback;
import com.jorgegiance.chess_timer.util.TimerPickerDialogCallback;


public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        NameDialogCallback,
        TimerPickerDialogCallback {

    // UI components
    private TextView player1Name, player2Name;
    private TextView player1Timer, player2Timer;
    private ImageButton saveButton, playButton;

    // vars
    private int dialogState = 0;
    private int timerState = 0;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1Name = findViewById(R.id.player_1_name);
        player2Name = findViewById(R.id.player_2_name);
        saveButton = findViewById(R.id.save_settings_button);
        playButton = findViewById(R.id.main_start_button);
        player1Timer = findViewById(R.id.player_1_timer);
        player2Timer = findViewById(R.id.player_2_timer);


        setListiners();
        setSpinnerAdapter();


    }

    private void setListiners() {
        player1Name.setOnClickListener(this);
        player2Name.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
        player1Timer.setOnClickListener(this);
        player2Timer.setOnClickListener(this);
    }

    private void setSpinnerAdapter() {

        Spinner timerSpinner = findViewById(R.id.timer_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> timerAdapter = ArrayAdapter.createFromResource(this,
                R.array.timer_spinner_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        timerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        timerSpinner.setAdapter(timerAdapter);


        Spinner delaySpinner = findViewById(R.id.delay_spinner);
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

        switch (view.getId()){
            case R.id.player_1_name:{
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
                break;
            }
            case R.id.main_start_button: {
                break;
            }
            case R.id.player_1_timer:{
                timerState = 1;
                showTimePickerDialog(player1Timer.getText().toString());
                break;
            }
            case R.id.player_2_timer: {
                timerState = 2;
                showTimePickerDialog(player2Timer.getText().toString());
                break;
            }


        }
    }

    public void showDialog(String name){
        FragmentManager fm = getSupportFragmentManager();
        PlayerNameDialog nameDialog = new PlayerNameDialog(this, name);
        nameDialog.show(fm, "fragment_alert");
    }


    @Override
    public void nameSaved( String name ) {
        if (dialogState == 1){
            player1Name.setText(name);
        }
        if (dialogState == 2){
            player2Name.setText(name);
        }
    }


    public void showTimePickerDialog(String time) {
        FragmentManager fm = getSupportFragmentManager();
        TimerPickerDialog timerDialog = new TimerPickerDialog(this, time);
        timerDialog.show(fm, "fragment_alert");

    }

    @Override
    public void timeSaved( String time ) {

        if (timerState == 1){
            player1Timer.setText(time);
        }
        if (timerState == 2){
            player2Timer.setText(time);
        }
    }
}
