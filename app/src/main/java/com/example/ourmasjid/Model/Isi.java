package com.example.ourmasjid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Isi {
    @SerializedName("Isi")
    private List<Masjid> mmasjid;

    public Isi(List<Masjid> masjid){
        mmasjid=masjid;
    }

    public int getNumber(){
        return mmasjid.size();
    }
}
