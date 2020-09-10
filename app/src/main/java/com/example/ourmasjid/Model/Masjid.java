package com.example.ourmasjid.Model;

import com.google.gson.annotations.SerializedName;

public class Masjid {

    @SerializedName("id_masjid")
    private String mid_masjid;

    @SerializedName("nama_masjid")
    private String mnamamasjid;

    @SerializedName("alamat")
    private String malamat;

    @SerializedName("lng")
    private  String mlongitude;

    @SerializedName("lat")
    private String mlatidue;

    public Masjid(String id_masjid, String namamasjid, String alamat, String longitude, String latitude){
        mid_masjid=id_masjid;
        mnamamasjid=namamasjid;
        malamat=alamat;
        mlongitude=longitude;
        mlatidue=latitude;
    }
}
