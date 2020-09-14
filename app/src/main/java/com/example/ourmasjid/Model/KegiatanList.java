package com.example.ourmasjid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class KegiatanList {
    @SerializedName("listkegiatan")
    private ArrayList<Kegiatan> mkegiatan;

    public KegiatanList(ArrayList<Kegiatan> kegiatan){
        mkegiatan=kegiatan;
    }

    public int getNumber(){
        return mkegiatan.size();
    }

    public ArrayList<Kegiatan> getkegiatan() {
        return mkegiatan;
    }
}
