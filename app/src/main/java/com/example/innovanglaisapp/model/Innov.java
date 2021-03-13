package com.example.innovanglaisapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Innov {
    @SerializedName("hydra:member")
    private List<Hydra> hydra = null;

    public List<Hydra> getHydra() {
        return hydra;
    }

    public void setHydra(List<Hydra> hydra) {
        this.hydra = hydra;
    }


}
