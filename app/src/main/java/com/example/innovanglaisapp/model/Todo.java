package com.example.innovanglaisapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Todo {

    @Expose
    @SerializedName("id")
    int id;

    @Expose
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
