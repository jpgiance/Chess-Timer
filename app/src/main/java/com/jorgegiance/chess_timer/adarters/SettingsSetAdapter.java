package com.jorgegiance.chess_timer.adarters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jorgegiance.chess_timer.R;
import com.jorgegiance.chess_timer.models.SettingsSet;

import java.util.List;

public class SettingsSetAdapter extends RecyclerView.Adapter<SettingsSetAdapter.SettingsSetHolder> {


    private Context ctx;
    private SettingsSetAdapterOnClickHandler clickHandler;
    private List<SettingsSet> settingsSetList;


    public SettingsSetAdapter( Context ctx, SettingsSetAdapterOnClickHandler clickHandler ) {
        this.ctx = ctx;
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public SettingsSetHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {

        LayoutInflater inflater = LayoutInflater.from(ctx);
        View settingView = inflater.inflate(R.layout.load_settings_item, parent, false);
        SettingsSetHolder holder = new SettingsSetHolder(settingView);

        return holder;
    }

    @Override
    public void onBindViewHolder( @NonNull SettingsSetHolder holder, int position ) {

        holder.settingSetText.setText(settingsSetList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return settingsSetList.size();
    }


    /**
     * This method update SettingsSet List state
     *
     * @param  list
     */
    public void setSettingsSetList( List<SettingsSet> list ) {
        settingsSetList = list;
        notifyDataSetChanged();
    }





    public interface SettingsSetAdapterOnClickHandler {
        void onClick( SettingsSet set );
    }



    public class SettingsSetHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView settingSetText;

        public SettingsSetHolder( @NonNull View itemView ) {
            super(itemView);

            settingSetText = itemView.findViewById(R.id.setting_item_text);
            settingSetText.setOnClickListener(this);
        }

        @Override
        public void onClick( View v ) {
            clickHandler.onClick(settingsSetList.get(getAdapterPosition()));
        }
    }
}
