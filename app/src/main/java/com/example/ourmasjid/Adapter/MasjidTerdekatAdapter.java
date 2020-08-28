package com.example.ourmasjid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ourmasjid.R;

import java.util.List;

public class MasjidTerdekatAdapter extends RecyclerView.Adapter<MasjidTerdekatAdapter.MyViewHolder> {
    private String[] masjidlist={"Masjid Istiqlal","Masjid An-Nur","Masjid Raya Baiturrahman","Masjid Kubah Emas"};
    private String[] alamatlist={"Kebon Kopi","Pajajaran Dalam", "Buah Batu", "Batununggal"};
    private String[] nohplist={"12345", "678910", "11121314","04200007"};
    private String[] jarak={"111","87","78","20"};

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, alamat, nohp, jarak;
        private MyViewHolder(View view) {
            super(view);
            nama = (TextView) view.findViewById(R.id.namamasjid);
            alamat = (TextView) view.findViewById(R.id.alamatmasjid);
            nohp = (TextView) view.findViewById(R.id.nohp);
            jarak=(TextView) view.findViewById(R.id.jarak);
        }
    }


    public MasjidTerdekatAdapter() {

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_masjidterdekat, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        for(int i=0; i<4; i++) {
            holder.nama.setText(masjidlist[position]);
            holder.alamat.setText(alamatlist[position]);
            holder.nohp.setText(nohplist[position]);
            holder.jarak.setText(jarak[position]);

        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
