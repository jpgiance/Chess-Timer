package com.jorgegiance.chess_timer.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.jorgegiance.chess_timer.R;
import com.jorgegiance.chess_timer.util.GameSavedCallback;

public class GameSavedDialog extends DialogFragment {

    private GameSavedCallback dialogCallback;

    // UI components
    private EditText gameText;
    private TextView player1, player2, movesP1, movesP2, timeP1, timeP2;

    public GameSavedDialog( GameSavedCallback dialogCallback ) {
        this.dialogCallback = dialogCallback;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog( @Nullable Bundle savedInstanceState ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View mdialog = inflater.inflate(R.layout.dialog_game_saved, null);

        builder.setView(mdialog)
                .setTitle(R.string.save_game_dialog_title)
                // Add action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button ...
                        dialogCallback.gameSaved(gameText.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       // GameSavedDialog.this.getDialog().cancel();
                        dialogCallback.dialogCancel();
                    }
                });

        gameText = mdialog.findViewById(R.id.game_name);
        player1 = mdialog.findViewById(R.id.p1_name);
        player2 = mdialog.findViewById(R.id.p2_name);
        movesP1 = mdialog.findViewById(R.id.p1_moves);
        movesP2 = mdialog.findViewById(R.id.p2_moves);
        timeP1 = mdialog.findViewById(R.id.p1_time);
        timeP2 = mdialog.findViewById(R.id.p2_time);


        populateDialog();


        return builder.create();
    }

    private void populateDialog() {
        gameText.setText(R.string.default_game_name);
    }

}
