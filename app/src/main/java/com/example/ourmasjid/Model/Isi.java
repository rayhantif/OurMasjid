package com.example.ourmasjid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Isi {
    @SerializedName("listmasjid")
    private ArrayList<Masjid> mmasjid;

    public Isi(ArrayList<Masjid> masjid){
        mmasjid=masjid;
    }

    public int getNumber(){
        return mmasjid.size();
    }

    public ArrayList<Masjid> getMmasjid() {
        return mmasjid;
    }
}
