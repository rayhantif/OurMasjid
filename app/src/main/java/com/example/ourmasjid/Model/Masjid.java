package com.example.ourmasjid.Model;

import com.google.gson.annotations.SerializedName;

public class Masjid {

    @SerializedName("id_masjid")
    private String mid_masjid;

    @SerializedName("nama_masjid")
    private String mnamamasjid;
    @SerializedName("nohp")
    private String mnohp;
    @SerializedName("alamat")
    private String malamat;

    @SerializedName("langitude")
    private  String mlongitude;

    @SerializedName("latitude")
    private String mlatidue;

    public Masjid(String id_masjid, String namamasjid, String alamat, String longitude, String latitude){
        mid_masjid=id_masjid;
        mnamamasjid=namamasjid;
        malamat=alamat;
        mlongitude=longitude;
        mlatidue=latitude;
    }

    public String getMalamat() {
        return malamat;
    }

    public String getMlatidue() {
        return mlatidue;
    }

    public String getMid_masjid() {
        return mid_masjid;
    }

    public String getMlongitude() {
        return mlongitude;
    }

    public String getMnamamasjid() {
        return mnamamasjid;
    }

    public String getMnohp() {
        return mnohp;
    }
}
