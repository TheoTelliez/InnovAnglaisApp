package com.example.innovanglaisapp.mots;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.innovanglaisapp.MainActivity;
import com.example.innovanglaisapp.R;
import com.example.innovanglaisapp.listes.ListesListAdapter;
import com.example.innovanglaisapp.model.Innov;
import com.example.innovanglaisapp.model.Hydra;
import com.example.innovanglaisapp.themes.ThemesListAdapter;
import com.example.innovanglaisapp.webservices.WebServicesInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MotsListFragment extends Fragment {

    private RecyclerView motListRecyclerView;
    private RecyclerView.Adapter innovListAdapter;
    private int idListe;

    public MotsListFragment(int idListe) {
        this.idListe = idListe;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, null); //le 2e paramètre sera toujours null tandis que le premier représentera l’id de notre layout de fragment (ici : R.layout.fragment_random).
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        motListRecyclerView = view.findViewById(R.id.listRecyclerView);
        motListRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager listeListLayoutManager = new LinearLayoutManager(getContext());
        motListRecyclerView.setLayoutManager(listeListLayoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://serveur1.arras-sio.com/symfony4-4059/InnovAnglais/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebServicesInterface webServicesInterface = retrofit.create(WebServicesInterface.class);

        Call<Innov> callGetHydraByMots = webServicesInterface.getHydraByMots();

        callGetHydraByMots.enqueue(new Callback<Innov>() {
            @Override
            public void onResponse(Call<Innov> call, Response<Innov> response) {
                ArrayList<Hydra> listeTrierMots = new ArrayList<>();
                int i;
                int n;

                assert response.body() != null; // Si hydra null pointer

                int sizeTab = response.body().getHydra().size();
                String listeActuelle = "/symfony4-4059/InnovAnglais/public/api/listes/";

                for (i = 0; i < sizeTab; i++) {
                    for (n = 0; n < response.body().getHydra().get(i).getListe().length; n++) {
                        if (response.body().getHydra().get(i).getListe()[n].equals(listeActuelle + idListe)) {
                            listeTrierMots.add((response.body().getHydra().get(i)));
                        }
                    }
                }

                innovListAdapter = new MotsListAdapter(listeTrierMots);
                motListRecyclerView.setAdapter(innovListAdapter);
            }

            @Override
            public void onFailure(Call<Innov> call, Throwable t) {
                System.out.println("Echec du chargement de InnovAnglaisApp");
            }
        });
    }
}





















