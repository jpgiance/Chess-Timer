package com.jorgegiance.chess_timer.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.jorgegiance.chess_timer.R;
import com.jorgegiance.chess_timer.util.NameDialogCallback;

public class PlayerNameDialog extends DialogFragment {

    NameDialogCallback dialogCallback;
    String defaultName;
    EditText nameText;


    public PlayerNameDialog( NameDialogCallback dialogCallback, String defaultName ) {
        this.dialogCallback = dialogCallback;
        this.defaultName = defaultName;
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
         View mdialog = inflater.inflate(R.layout.dialog_player_name, null);
         builder.setView(mdialog)
                .setTitle(R.string.name_dialog_title)
                // Add action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                         // User clicked OK button ...
                        dialogCallback.nameSaved(nameText.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PlayerNameDialog.this.getDialog().cancel();
                    }
                });

        nameText = mdialog.findViewById(R.id.player_name);
        nameText.setText(defaultName);

        return builder.create();
    }


}
