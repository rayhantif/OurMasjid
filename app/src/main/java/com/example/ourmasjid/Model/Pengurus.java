package com.example.ourmasjid.Model;

import com.google.gson.annotations.SerializedName;

public class Pengurus {
    @SerializedName("id_pengurus")
    private int midpengurus;

    @SerializedName("id_masjid")
    private int midmasjid;

    @SerializedName("nama_pengurus")
    private String mnamapengurus;

    @SerializedName("email_pengurus")
    private String memailpengurus;

    public Pengurus(int idpengurus, int idmasjid, String namapengurus, String emailpengurus){
        this.midpengurus=idpengurus;
        this.midmasjid=idmasjid;
        this.mnamapengurus=namapengurus;
        this.memailpengurus=emailpengurus;
    }

    public int getMidmasjid() {
        return midmasjid;
    }

    public int getMidpengurus() {
        return midpengurus;
    }

    public String getMemailpengurus() {
        return memailpengurus;
    }

    public String getMnamapengurus() {
        return mnamapengurus;
    }
}
