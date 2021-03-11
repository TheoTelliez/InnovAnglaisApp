package com.example.innovanglaisapp.themes;

import android.content.Intent;
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
import com.example.innovanglaisapp.model.Innov;
import com.example.innovanglaisapp.webservices.WebServicesInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//public class ThemesListFragment extends Fragment implements ThemesListClickListener {
public class ThemesListFragment extends Fragment {

    private RecyclerView themeListRecyclerView;
    private RecyclerView.Adapter innovListAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, null); //le 2e paramètre sera toujours null tandis que le premier représentera l’id de notre layout de fragment (ici : R.layout.fragment_random).
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        themeListRecyclerView = view.findViewById(R.id.categoryListRecyclerView);
        themeListRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager themeListLayoutManager = new LinearLayoutManager(getContext());
        themeListRecyclerView.setLayoutManager(themeListLayoutManager);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://serveur1.arras-sio.com/symfony4-4059/InnovAnglais/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebServicesInterface webServicesInterface = retrofit.create(WebServicesInterface.class);

        Call<Innov> callGetTodoByTheme = webServicesInterface.getTodoByTheme();

        callGetTodoByTheme.enqueue(new Callback<Innov>() {
            @Override
            public void onResponse(Call<Innov> call, Response<Innov> response) {
                innovListAdapter = new ThemesListAdapter(response.body(), (MainActivity)getActivity()); // Ca bug ici :((((
                themeListRecyclerView.setAdapter(innovListAdapter);
            }

            @Override
            public void onFailure(Call<Innov> call, Throwable t) {
                System.out.println("Echec du chargement de InnovAnglaisApp");
            }
        });
    }

    ;

//    @Override
//    public void onThemeListClick(Todo todo) {
//        System.out.println(todo.getLibelle());
//        Intent drinkDetailListingActivityIntent = new Intent(getActivity(), CocktailsListFragment.class);
//        String s = todo.getLibelle();
//        drinkDetailListingActivityIntent.putExtra("nameDuStrCategory", s);
//        startActivity(drinkDetailListingActivityIntent);
//    }

}

