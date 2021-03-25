package com.example.innovanglaisapp.model;


import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Hydra {

    @SerializedName("id")
    int id;

    @SerializedName("libelle")
    String libelle;

    @SerializedName("utilisateurs")
    String[] utilisateurs;

    @SerializedName("theme")
    String theme;

    @SerializedName("liste")
    String[] liste;

    @SerializedName("libelleen")
    String libelleen;

    // id
    public int getId() {
        return id;
    }

    // libelle
    public String getLibelle() {
        return libelle;
    }

    //utilisateurs
    public String[] getUtilisateurs() {
        return utilisateurs;
    }

    // theme
    public String getTheme() {
        return theme;
    }

    // listes
    public String[] getListe() {
        return liste;
    }

    // libelle
    public String getLibelleEn() {
        return libelleen;
    }








}
