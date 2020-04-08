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

public class DialogPlayerName extends DialogFragment {

    NameDialogCallback dialogCallback;
    EditText nameText;


    public DialogPlayerName( NameDialogCallback dialogCallback ) {
        this.dialogCallback = dialogCallback;
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
         View mdialog = inflater.inflate(R.layout.player_name_dialog, null);
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
                        DialogPlayerName.this.getDialog().cancel();
                    }
                });

        nameText = mdialog.findViewById(R.id.player_name);

        return builder.create();
    }


}
