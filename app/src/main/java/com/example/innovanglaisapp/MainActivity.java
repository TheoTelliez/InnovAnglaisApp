package com.example.innovanglaisapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.innovanglaisapp.accueil.AccueilFragment;
import com.example.innovanglaisapp.model.Hydra;
import com.example.innovanglaisapp.stats.StatsFragment;
import com.example.innovanglaisapp.themes.ThemesListClickListener;
import com.example.innovanglaisapp.themes.ThemesListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ThemesListClickListener {

    Fragment themeFragment;
    Fragment accueilFragment;
    Fragment statsFragment;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        themeFragment = new ThemesListFragment();
        accueilFragment = new AccueilFragment();
        statsFragment = new StatsFragment();

        loadFragment(accueilFragment); //sert à dire que le fragment par défaut quand on lance l'appli c'est celui de texte

        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(this);



    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment) //id du container
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.action_un:
                fragment = themeFragment;
                break;
            case R.id.action_deux:
                fragment = accueilFragment;
                break;
            case R.id.action_trois:
                fragment = statsFragment;
                break;
            default: //si jamais il ne fait ni l'un ni l'autre alors on fait un truc quand même par defaut
                break;
        }
        return loadFragment(fragment); //ca c'est pour savoir si ca s'est bien passé, il retourne bien normalement
    }


    @Override
    public void onThemeListClick(Hydra hydra) {
        System.out.println(hydra.getLibelle()); //C'est qui qui faut passer le param
    }
}