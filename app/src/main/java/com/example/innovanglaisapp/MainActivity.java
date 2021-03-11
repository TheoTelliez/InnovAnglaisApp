package com.example.innovanglaisapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.innovanglaisapp.themes.ThemesListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    Fragment themeFragment;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        randomFragment = new RandomFragment();
        themeFragment = new ThemesListFragment();
//        searchFragment = new SearchFragment(this);

        loadFragment(themeFragment); //sert à dire que le fragment par défaut quand on lance l'appli c'est celui de texte

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
//            case R.id.action_un:
//                fragment = randomFragment;
//                break;
//            case R.id.action_deux:
//                fragment = listFragment;
//                break;
            case R.id.action_trois:
                fragment = themeFragment;
                break;
            default: //si jamais il ne fait ni l'un ni l'autre alors on fait un truc quand même par defaut
                break;
        }
        return loadFragment(fragment); //ca c'est pour savoir si ca s'est bien passé, il retourne bien normalement
    }

}