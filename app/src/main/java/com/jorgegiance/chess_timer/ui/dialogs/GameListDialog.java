package com.jorgegiance.chess_timer.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

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
import com.jorgegiance.chess_timer.adarters.GamesListAdapter;
import com.jorgegiance.chess_timer.models.Game;
import com.jorgegiance.chess_timer.viewmodel.GameViewModel;
import com.jorgegiance.chess_timer.viewmodel.MainActivityViewModel;

import java.util.List;

public class GameListDialog extends DialogFragment {

    private GamesListAdapter adapter;
    private RecyclerView mRecycler;
    MainActivityViewModel mMainActivityViewModel;
    GameViewModel mGameViewModel;

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
                .setTitle(R.string.games_list_dialog_title)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GameListDialog.this.getDialog().cancel();

                    }
                });

        mMainActivityViewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        mGameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);


        adapter = new GamesListAdapter(getContext());
        mRecycler = mDialog.findViewById(R.id.base_recycler);
        mRecycler.setAdapter(adapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecycler);
        mRecycler.setHasFixedSize(true);

        initObserver();


        return builder.create();
    }

    private void initObserver() {

        mGameViewModel.getAllGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged( List<Game> games ) {
                mMainActivityViewModel.setGamesList(games);
                adapter.setGameList(games);
            }
        });
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove( @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target ) {
            return false;
        }

        @Override
        public void onSwiped( @NonNull RecyclerView.ViewHolder viewHolder, int direction ) {
            deleteGame(viewHolder.getAdapterPosition());
        }
    };

    private void deleteGame( int adapterPosition ) {
        mGameViewModel.deleteGame(mMainActivityViewModel.getGamesList().get(adapterPosition));
    }
}
