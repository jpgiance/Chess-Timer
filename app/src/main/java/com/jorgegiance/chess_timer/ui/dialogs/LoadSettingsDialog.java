package com.jorgegiance.chess_timer.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.jorgegiance.chess_timer.R;
import com.jorgegiance.chess_timer.adarters.SettingsSetAdapter;
import com.jorgegiance.chess_timer.models.SettingsSet;
import com.jorgegiance.chess_timer.viewmodel.MainActivityViewModel;
import com.jorgegiance.chess_timer.viewmodel.SettingsSetViewModel;

import java.util.ArrayList;
import java.util.List;

public class LoadSettingsDialog extends DialogFragment implements SettingsSetAdapter.SettingsSetAdapterOnClickHandler {


    private SettingsSetAdapter adapter;
    private RecyclerView mRecycler;
    MainActivityViewModel mMainActivityViewModel;
    SettingsSetViewModel mSettingsSetViewModel;






    public LoadSettingsDialog() { }

    @NonNull
    @Override
    public Dialog onCreateDialog( @Nullable Bundle savedInstanceState ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View mDialog = inflater.inflate(R.layout.dialog_menu_buttons, null);

        builder.setView(mDialog)
                .setTitle(R.string.load_settings_title)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LoadSettingsDialog.this.getDialog().cancel();

                    }
                });

        mMainActivityViewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        mSettingsSetViewModel = new ViewModelProvider(getActivity()).get(SettingsSetViewModel.class);



        adapter = new SettingsSetAdapter(getContext(), this);
        mRecycler = mDialog.findViewById(R.id.base_recycler);
        mRecycler.setAdapter(adapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecycler);
        mRecycler.setHasFixedSize(true);
      //  adapter.setSettingsSetList(mMainActivityViewModel.getSettingsSetsList());

        initObserver();

        return builder.create();
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove( @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target ) {
            return false;
        }

        @Override
        public void onSwiped( @NonNull RecyclerView.ViewHolder viewHolder, int direction ) {
            deleteSettingsSet(viewHolder.getAdapterPosition());
        }
    };

    private void deleteSettingsSet(int position) {
        mSettingsSetViewModel.deleteSettingsSet(mMainActivityViewModel.getSettingsSetsList().get(position));
    }


    @Override
    public void onClick( SettingsSet set ) {
        mMainActivityViewModel.setSettingsSetSelected(set);
        mMainActivityViewModel.setSettingsSelectedStatus(true);
        LoadSettingsDialog.this.getDialog().cancel();
    }


    private void initObserver() {

        mSettingsSetViewModel.getAllSettingsSet().observe(this, new Observer<List<SettingsSet>>() {
            @Override
            public void onChanged( List<SettingsSet> settingsSets ) {
                mMainActivityViewModel.setSettingsSetsList(settingsSets);
                adapter.setSettingsSetList(settingsSets);
            }
        });


    }
}