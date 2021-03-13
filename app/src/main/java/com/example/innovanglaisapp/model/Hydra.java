package com.example.innovanglaisapp.model;


import com.google.gson.annotations.SerializedName;

public class Hydra {

    @SerializedName("id")
    int id;

    @SerializedName("libelle")
    String libelle;


    //id
    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    //libelle
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle() {
        this.libelle = libelle;
    }





}
