package com.example.ourmasjid.Model;

import com.google.gson.annotations.SerializedName;

public class Kegiatan {
    @SerializedName("id_kegiatan")
    private String midkegiatan;

    @SerializedName("nama_kegiatan")
    private String mnamakegiatan;

    @SerializedName("waktu_kegiatan")
    private String mwaktukegiatan;

    @SerializedName("lokasi_kegiatan")
    private String mlokasikegiatan;

    @SerializedName("penanggung_jawab")
    private String mpenanggungjawab;

    @SerializedName("pemateri")
    private String mpemateri;

    public Kegiatan(String idkegiatan, String namakegiatan, String waktukegiatan, String lokasikegiatan, String penanggungjawab,
    String pemateri){
        this.midkegiatan=idkegiatan;
        this.mnamakegiatan=namakegiatan;
        this.mwaktukegiatan=waktukegiatan;
        this.mlokasikegiatan=lokasikegiatan;
        this.mpenanggungjawab=penanggungjawab;
        this.mpemateri=pemateri;
    }

    public String getMidkegiatan() {
        return midkegiatan;
    }

    public String getMlokasikegiatan() {
        return mlokasikegiatan;
    }

    public String getMnamakegiatan() {
        return mnamakegiatan;
    }

    public String getMpemateri() {
        return mpemateri;
    }

    public String getMpenanggungjawab() {
        return mpenanggungjawab;
    }

    public String getMwaktukegiatan() {
        return mwaktukegiatan;
        //return "hariini";
    }
}
