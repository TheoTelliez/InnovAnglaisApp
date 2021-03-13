package com.example.innovanglaisapp.stats;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.innovanglaisapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

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

        chart.setData(generatePieData());
        chart.animateX(3000);

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");

        Legend l = chart.getLegend();
        l.setTypeface(tf);

        return v;

    }

    /**
     * generates less data (1 DataSet, 4 values)
     * @return PieData
     */
    protected PieData generatePieData() {

        int entreprises = 8;

        ArrayList<PieEntry> listeEntreprises = new ArrayList<>();

        for(int i = 0; i < entreprises; i++) {
            listeEntreprises.add(new PieEntry((int) ((Math.random() * 60) + 40), "Entreprise " + (i+1)));
        }

        PieDataSet ds1 = new PieDataSet(listeEntreprises, "");
        ds1.setColors(ColorTemplate.MATERIAL_COLORS);
        ds1.setSliceSpace(2f);
        ds1.setValueTextColor(Color.WHITE);
        ds1.setValueTextSize(12f);

        PieData d = new PieData(ds1);
        d.setValueTypeface(tf);

        return d;
    }




}