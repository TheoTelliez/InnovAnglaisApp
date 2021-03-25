package com.example.innovanglaisapp.mots;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.innovanglaisapp.R;
import com.example.innovanglaisapp.mots.MotsListAdapter;
import com.example.innovanglaisapp.listes.ListesListClickListener;
import com.example.innovanglaisapp.model.Hydra;
import java.util.ArrayList;

public class MotsListAdapter extends RecyclerView.Adapter<MotsListAdapter.ViewHolder>{

    ArrayList<Hydra> listeTrierMots;

    public MotsListAdapter(ArrayList<Hydra> listeTrierMots){
        this.listeTrierMots = listeTrierMots;

    }

    @NonNull
    @Override
    public MotsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell, parent, false);
        MotsListAdapter.ViewHolder viewHolder = new MotsListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MotsListAdapter.ViewHolder holder, final int position) {

        holder.listeNameCell.setText(listeTrierMots.get(position).getLibelle() + " -> " + listeTrierMots.get(position).getLibelleEn() );


    }

    @Override
    public int getItemCount() {
        return listeTrierMots.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView listeNameCell;

        public ViewHolder(@NonNull View itemList) {
            super(itemList);

            listeNameCell = itemList.findViewById(R.id.nameCell);

        }
    }


}
