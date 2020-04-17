package com.jorgegiance.chess_timer.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.jorgegiance.chess_timer.R;
import com.jorgegiance.chess_timer.util.LoadSettingsCallback;

import java.util.ArrayList;

public class LoadSettingsDialog extends DialogFragment {

    private ArrayList<String> SettingsSetArray;
    LoadSettingsCallback dialogCallback;






    public LoadSettingsDialog( LoadSettingsCallback dialogCallback, ArrayList<String> SettingsSetArray ) {
        this.dialogCallback = dialogCallback;
        this.SettingsSetArray = SettingsSetArray;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog( @Nullable Bundle savedInstanceState ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View mdialog = inflater.inflate(R.layout.dialog_menu_buttons, null);

        builder.setView(mdialog)
                .setTitle(R.string.load_settings_title)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LoadSettingsDialog.this.getDialog().cancel();

                    }
                });



        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.dialog_load_settings_item, SettingsSetArray);

        ListView listView = mdialog.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                LoadSettingsDialog.this.getDialog().cancel();
                dialogCallback.SettingsSetSelected(position);

            }
        });




        return builder.create();
    }


}
