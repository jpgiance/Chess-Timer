package com.jorgegiance.chess_timer.adarters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jorgegiance.chess_timer.R;
import com.jorgegiance.chess_timer.models.Game;
import com.jorgegiance.chess_timer.util.Utils;

import java.util.List;

public class GamesListAdapter extends RecyclerView.Adapter<GamesListAdapter.GamesListHolder>{

    private Context ctx;
    private List<Game> gameList;

    public GamesListAdapter( Context ctx ) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public GamesListHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        LayoutInflater movieInflater = LayoutInflater.from(ctx);
        View gamesView = movieInflater.inflate(R.layout.dialog_game_saved, parent, false);
        GamesListHolder holder = new GamesListHolder(gamesView);

        return holder;
    }

    @Override
    public void onBindViewHolder( @NonNull GamesListHolder holder, int position ) {

        holder.gameText.setVisibility(View.GONE);
        holder.player1CheckBox.setVisibility(View.GONE);
        holder.player2CheckBox.setVisibility(View.GONE);
        holder.gameId.setText(gameList.get(position).getId());
        holder.player1.setText(gameList.get(position).getNamePlayer1());
        holder.player2.setText(gameList.get(position).getNamePlayer2());
        holder.movesP1.setText(Integer.toString(gameList.get(position).getMovesPlayer1()));
        holder.movesP2.setText(Integer.toString(gameList.get(position).getMovesPlayer2()));
        holder.timeP1.setText(Utils.time2String(gameList.get(position).getTimePlayer1()));
        holder.timeP2.setText(Utils.time2String(gameList.get(position).getTimePlayer2()));
        holder.deleteIcon.setVisibility(View.VISIBLE);

        if (gameList.get(position).getWinner() == 1){
            holder.player1Trophy.setVisibility(View.VISIBLE);
        }else{
            holder.player2Trophy.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }


    /**
     * This method update Games List state
     *
     * @param  list
     */
    public void setGameList( List<Game> list ) {
        gameList = list;
        notifyDataSetChanged();
    }




    public class GamesListHolder extends RecyclerView.ViewHolder{

        private EditText gameText;
        private TextView gameId, player1, player2, movesP1, movesP2, timeP1, timeP2;
        private ImageView player1Trophy, player2Trophy, deleteIcon;
        private CheckBox player1CheckBox, player2CheckBox;

        public GamesListHolder( @NonNull View itemView ) {
            super(itemView);

            gameText = itemView.findViewById(R.id.game_name);
            gameId = itemView.findViewById(R.id.check_winner_text);
            player1 = itemView.findViewById(R.id.p1_name);
            player2 = itemView.findViewById(R.id.p2_name);
            movesP1 = itemView.findViewById(R.id.p1_moves);
            movesP2 = itemView.findViewById(R.id.p2_moves);
            timeP1 = itemView.findViewById(R.id.p1_time);
            timeP2 = itemView.findViewById(R.id.p2_time);
            player1Trophy = itemView.findViewById(R.id.trophy_1);
            player2Trophy = itemView.findViewById(R.id.trophy_2);
            player1CheckBox = itemView.findViewById(R.id.p1_check_box);
            player2CheckBox = itemView.findViewById(R.id.p2_check_box);
            deleteIcon = itemView.findViewById(R.id.delete_icon);
        }
    }
}
