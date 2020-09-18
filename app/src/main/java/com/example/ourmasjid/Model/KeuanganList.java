package com.example.ourmasjid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class KeuanganList {
    @SerializedName("listkeuangan")
    private ArrayList<Keuangan> mkeuangan;

    public KeuanganList(ArrayList<Keuangan> keuangan){
        mkeuangan=keuangan;
    }

    public int getNumber(){
        return mkeuangan.size();
    }

    public ArrayList<Keuangan> getkeuangan() {
        return mkeuangan;
    }
}
