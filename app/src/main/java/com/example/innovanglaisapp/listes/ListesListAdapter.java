package com.example.innovanglaisapp.listes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.innovanglaisapp.R;
import com.example.innovanglaisapp.model.Hydra;
import java.util.ArrayList;

public class ListesListAdapter extends RecyclerView.Adapter<ListesListAdapter.ViewHolder> {


    ArrayList<Hydra> listeTrier;
    private ListesListClickListener listesListClickListener;


    public ListesListAdapter(ArrayList<Hydra> listeTrier, ListesListClickListener listesListClickListener){
        this.listeTrier = listeTrier;
        this.listesListClickListener = listesListClickListener;

    }


    @NonNull
    @Override
    public ListesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell, parent, false);
        ListesListAdapter.ViewHolder viewHolder = new ListesListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListesListAdapter.ViewHolder holder, final int position) {

        holder.listeNameCell.setText(listeTrier.get(position).getLibelle());
        holder.listeNameCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listesListClickListener.onListeListClick(listeTrier.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listeTrier.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView listeNameCell;

        public ViewHolder(@NonNull View itemList) {
            super(itemList);

            listeNameCell = itemList.findViewById(R.id.nameCell);

        }
    }



}
