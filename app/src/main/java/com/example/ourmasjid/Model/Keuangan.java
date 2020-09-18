package com.example.ourmasjid.Model;

import com.google.gson.annotations.SerializedName;

public class Keuangan {
    @SerializedName("id_keuangan")
    private int midkeuangan;

    @SerializedName("id_kegiatan")
    private int midkegiatan;

    @SerializedName("nama_transaksi")
    private String mnamatransaksi;

    @SerializedName("jenis_keuangan")
    private int mjeniskeuangan;

    @SerializedName("nominal")
    private int mnominal;

    @SerializedName("tanggal")
    private String mtanggal;

    public Keuangan(int idkeuangan, int idkegiatan, String namatransaksi, int jeniskeuangan,
                    int nominal, String tanggal){
        midkeuangan=idkeuangan;
        midkegiatan=idkegiatan;
        mnamatransaksi=namatransaksi;
        mjeniskeuangan=jeniskeuangan;
        mnominal=nominal;
        mtanggal=tanggal;
    }

    public int getMidkeuangan() {
        return midkeuangan;
    }

    public int getMidkegiatan() {
        return midkegiatan;
    }

    public String getMnamatransaksi() {
        return mnamatransaksi;
    }

    public int getMjeniskeuangan() {
        return mjeniskeuangan;
    }

    public int getMnominal() {
        return mnominal;
    }

    public String getMtanggal() {
        return mtanggal;
    }
}
