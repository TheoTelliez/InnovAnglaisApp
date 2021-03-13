package com.example.innovanglaisapp.themes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.innovanglaisapp.R;
import com.example.innovanglaisapp.model.Innov;


public class ThemesListAdapter extends RecyclerView.Adapter<ThemesListAdapter.ViewHolder> {

    Innov innov;
    private ThemesListClickListener themesListClickListener;


    public ThemesListAdapter(Innov innov, ThemesListClickListener themesListClickListener){
       this.innov = innov; //innov permet de savoir toutes les données dont on va se servir
       this.themesListClickListener = themesListClickListener;

    }

    @NonNull
    @Override
    public ThemesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.themes_list_cell, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThemesListAdapter.ViewHolder holder, final int position) {
        holder.themeNameCell.setText(innov.getHydra().get(position).getLibelle());
        holder.themeNameCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themesListClickListener.onThemeListClick(innov.getHydra().get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return innov.getHydra().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView themeNameCell;

        public ViewHolder(@NonNull View itemList) {
            super(itemList);
            themeNameCell = itemList.findViewById(R.id.themeNameCell);

        }
    }
}