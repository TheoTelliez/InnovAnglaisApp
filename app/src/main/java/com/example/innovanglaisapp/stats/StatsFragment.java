package com.example.innovanglaisapp.stats;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.innovanglaisapp.MainActivity;
import com.example.innovanglaisapp.R;
import com.example.innovanglaisapp.model.Innov;
import com.example.innovanglaisapp.themes.ThemesListAdapter;
import com.example.innovanglaisapp.webservices.WebServicesInterface;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings({"SameParameterValue", "WeakerAccess"})
public class StatsFragment extends Fragment {

    private Typeface tf;
    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public StatsFragment() {
    }

    @SuppressWarnings("FieldCanBeLocal")
    private PieChart chart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_stats, container, false);

        chart = v.findViewById(R.id.chart);

        chart.getDescription().setEnabled(false);

        chart.animateX(3000);

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");

        Legend l = chart.getLegend();

        l.setTypeface(tf);

        return v;

    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://serveur1.arras-sio.com/symfony4-4059/InnovAnglais/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebServicesInterface webServicesInterface = retrofit.create(WebServicesInterface.class);

        Call<Innov> callGetHydraByEntreprises = webServicesInterface.getHydraByEntreprises();

        callGetHydraByEntreprises.enqueue(new Callback<Innov>() {
            @Override
            public void onResponse(Call<Innov> call, Response<Innov> response) {

                System.out.println("Ca marche");

                int tailleEntreprises = response.body().getHydra().size();

                String[] tableauStats = new String[tailleEntreprises];
                int[] tableauUtilisateurs = new int[tailleEntreprises];


                for (int i = 0; i < tailleEntreprises; i++) {
                    System.out.println(response.body().getHydra().get(i).getLibelle());
                    System.out.println(response.body().getHydra().get(i).getUtilisateurs().length);

                    tableauStats[i] = response.body().getHydra().get(i).getLibelle();
                    tableauUtilisateurs[i] = response.body().getHydra().get(i).getUtilisateurs().length;

                }

                chart.setData(generatePieData(tableauStats, tableauUtilisateurs));
            }

            @Override
            public void onFailure(Call<Innov> call, Throwable t) {
                System.out.println("Echec du chargement de InnovAnglaisApp");
            }
        });

    }


    /**
     * generates less data (1 DataSet, 4 values)
     *
     * @return PieData
     */
    protected PieData generatePieData(String[] tableauStats, int[] tableauUtilisateurs) {

        ArrayList<PieEntry> listeEntreprises = new ArrayList<>();

        for (int i = 0; i < tableauStats.length; i++) {

            if(tableauUtilisateurs[i] != 0) { // si les utilisateurs ne sont pas à 0 pour l'entreprise, sinon on affiche rien, ca ne sert a rien
                PieEntry valeurAAjouter = new PieEntry(tableauUtilisateurs[i], tableauStats[i]);
                listeEntreprises.add(valeurAAjouter);
            }

        }


        PieDataSet ds1 = new PieDataSet(listeEntreprises, "");
        ds1.setColors(ColorTemplate.MATERIAL_COLORS);
        ds1.setSliceSpace(2f);
        chart.setEntryLabelColor(Color.BLACK); // le label color
        ds1.setValueTextColor(Color.BLACK); // le number color
        ds1.setValueTextSize(12f);

        PieData d = new PieData(ds1);
        d.setValueTypeface(tf);

        return d;


    }


}