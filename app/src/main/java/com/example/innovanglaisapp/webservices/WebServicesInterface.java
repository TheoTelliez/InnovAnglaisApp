package com.example.innovanglaisapp.webservices;

import com.example.innovanglaisapp.model.Innov;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServicesInterface {

    @GET("themes")
    Call<Innov> getHydraByTheme();

    @GET("entreprises")
    Call<Innov> getHydraByEntreprises();

    @GET("listes")
    Call<Innov> getHydraByListe();

    @GET("mots")
    Call<Innov> getHydraByMots();

    // Exemple :  http://serveur1.arras-sio.com/symfony4-4059/InnovAnglais/public/api/themes

}
