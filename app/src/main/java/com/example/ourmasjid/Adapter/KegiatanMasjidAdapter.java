package com.example.ourmasjid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ourmasjid.Model.Kegiatan;
import com.example.ourmasjid.Model.Masjid;
import com.example.ourmasjid.R;

import java.util.ArrayList;

public class KegiatanMasjidAdapter extends RecyclerView.Adapter<KegiatanMasjidAdapter.MyViewHolder>{
    private ArrayList<Kegiatan> mKegiatan;
    private Context mContext;
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView namakegiatan, tanggalkegiatan, alamat, pemateri, penanggung_jawab;
        private MyViewHolder(View view) {
            super(view);
            namakegiatan = (TextView) view.findViewById(R.id.namakegiatan);
            tanggalkegiatan = (TextView) view.findViewById(R.id.tanggalkegiatan);
            alamat = (TextView) view.findViewById(R.id.alamatkegiatan);
            pemateri=(TextView) view.findViewById(R.id.pemateri);
            penanggung_jawab=(TextView) view.findViewById(R.id.penanggung_jawab);

        }
    }


    public KegiatanMasjidAdapter(Context context, ArrayList<Kegiatan> kegiatanArrayList) {
        mKegiatan=kegiatanArrayList;
        mContext=context;
    }

    @Override
    public KegiatanMasjidAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_kegiatanmasjid, parent, false);
        return new KegiatanMasjidAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(KegiatanMasjidAdapter.MyViewHolder holder, int position) {
        Kegiatan currentkegiatan=mKegiatan.get(position);


        holder.namakegiatan.setText(currentkegiatan.getMnamakegiatan());
        holder.tanggalkegiatan.setText(currentkegiatan.getMwaktukegiatan());
        holder.alamat.setText(currentkegiatan.getMlokasikegiatan());
        holder.pemateri.setText(currentkegiatan.getMpemateri());
        holder.penanggung_jawab.setText(currentkegiatan.getMpenanggungjawab());
      //  holder.itemView.setOnClickListener(clickListener);

    }

    @Override
    public int getItemCount() {
        return mKegiatan.size();
    }
}
