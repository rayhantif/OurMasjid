package com.example.ourmasjid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MasjidList {
    @SerializedName("listmasjid")
    private ArrayList<Masjid> mmasjid;

    public MasjidList(ArrayList<Masjid> masjid){
        mmasjid=masjid;
    }

    public int getNumber(){
        return mmasjid.size();
    }

    public ArrayList<Masjid> getMmasjid() {
        return mmasjid;
    }
}
