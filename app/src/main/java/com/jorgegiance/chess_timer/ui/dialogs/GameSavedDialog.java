package com.jorgegiance.chess_timer.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.jorgegiance.chess_timer.R;
import com.jorgegiance.chess_timer.util.Utils;
import com.jorgegiance.chess_timer.viewmodel.GameActivityViewModel;
import com.jorgegiance.chess_timer.viewmodel.GameViewModel;

public class GameSavedDialog extends DialogFragment {


    GameViewModel mGameViewModel;
    GameActivityViewModel mGameActivityViewModel;

    // UI components
    private EditText gameText;
    private TextView player1, player2, movesP1, movesP2, timeP1, timeP2;
    private CheckBox player1CheckBox, player2CheckBox;

    public GameSavedDialog() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog( @Nullable Bundle savedInstanceState ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View mDialog = inflater.inflate(R.layout.dialog_game_saved, null);

        builder.setView(mDialog)
                .setTitle(R.string.save_game_dialog_title)
                // Add action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button ...
                        mGameActivityViewModel.currentGame.setId(gameText.getText().toString());
                        mGameViewModel.insertGame(mGameActivityViewModel.currentGame);
                        mGameActivityViewModel.setGameSavedStatus(true);
                        GameSavedDialog.this.getDialog().cancel();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        mGameActivityViewModel.setGameDialogCancelStatus(true);
                        GameSavedDialog.this.getDialog().cancel();
                    }
                });

        mGameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);
        mGameActivityViewModel = new ViewModelProvider(getActivity()).get(GameActivityViewModel.class);

        gameText = mDialog.findViewById(R.id.game_name);
        player1 = mDialog.findViewById(R.id.p1_name);
        player2 = mDialog.findViewById(R.id.p2_name);
        movesP1 = mDialog.findViewById(R.id.p1_moves);
        movesP2 = mDialog.findViewById(R.id.p2_moves);
        timeP1 = mDialog.findViewById(R.id.p1_time);
        timeP2 = mDialog.findViewById(R.id.p2_time);
        player1CheckBox = mDialog.findViewById(R.id.p1_check_box);
        player2CheckBox = mDialog.findViewById(R.id.p2_check_box);

        setListeners();
        populateDialog();


        return builder.create();
    }

    private void setListeners() {
        player1CheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if (mGameActivityViewModel.currentGame.getWinner() == 1){
                    mGameActivityViewModel.currentGame.setWinner(0);
                }else{
                    mGameActivityViewModel.currentGame.setWinner(1);
                    player2CheckBox.setChecked(false);
                }
            }
        });

        player2CheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if (mGameActivityViewModel.currentGame.getWinner() == 2){
                    mGameActivityViewModel.currentGame.setWinner(0);
                }else{
                    mGameActivityViewModel.currentGame.setWinner(2);
                    player1CheckBox.setChecked(false);
                }

            }
        });

    }

    private void populateDialog() {
        gameText.setText(mGameActivityViewModel.currentGame.getId());
        player1.setText(mGameActivityViewModel.currentGame.getNamePlayer1());
        player2.setText(mGameActivityViewModel.currentGame.getNamePlayer2());
        movesP1.setText(Integer.toString(mGameActivityViewModel.currentGame.getMovesPlayer1()));
        movesP2.setText(Integer.toString(mGameActivityViewModel.currentGame.getMovesPlayer2()));
        timeP1.setText(Utils.time2String(mGameActivityViewModel.currentGame.getTimePlayer1()));
        timeP2.setText(Utils.time2String(mGameActivityViewModel.currentGame.getTimePlayer2()));

        if (mGameActivityViewModel.currentGame.getWinner() == 1){
            player1CheckBox.setChecked(true);
            player2CheckBox.setChecked(false);
        }
        if (mGameActivityViewModel.currentGame.getWinner() == 2){
            player1CheckBox.setChecked(false);
            player2CheckBox.setChecked(true);
        }

    }

}
