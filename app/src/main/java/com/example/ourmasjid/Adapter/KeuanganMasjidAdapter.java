package com.example.ourmasjid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ourmasjid.Model.Keuangan;
import com.example.ourmasjid.R;

import java.util.ArrayList;

public class KeuanganMasjidAdapter extends RecyclerView.Adapter<KeuanganMasjidAdapter.MyViewHolder>{
    private ArrayList<Keuangan> mKeuangan;
    private Context mContext;
    RelativeLayout relativeLayout;
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tanggalkeuangan, namatransaksi, jenis, nominal, saldo;
        private MyViewHolder(View view) {
            super(view);
            tanggalkeuangan=(TextView) view.findViewById(R.id.texttanggalkeuangan);
            namatransaksi=(TextView) view.findViewById(R.id.textnamatransaksi);
            jenis=(TextView) view.findViewById(R.id.textjenis);
            nominal=(TextView) view.findViewById(R.id.textnominal);
            saldo=(TextView) view.findViewById(R.id.textsaldo);
            relativeLayout=view.findViewById(R.id.relativeLayout);
        }
    }


    public KeuanganMasjidAdapter(Context context, ArrayList<Keuangan> keuangan) {
        mKeuangan=keuangan;
        mContext=context;
    }

    @Override
    public KeuanganMasjidAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_keuanganmasjid, parent, false);
        return new KeuanganMasjidAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(KeuanganMasjidAdapter.MyViewHolder holder, int position) {
        Keuangan currentkeuangan=mKeuangan.get(position);


        holder.tanggalkeuangan.setText(currentkeuangan.getMtanggal());
        holder.namatransaksi.setText(currentkeuangan.getMnamatransaksi());
        if(currentkeuangan.getMjeniskeuangan()==0){
            holder.jenis.setText("Pemasukan");
        }
        else {
            holder.jenis.setText("Pengeluaran");
        }

        holder.nominal.setText(Integer.toString(currentkeuangan.getMnominal()));
        holder.saldo.setText("?");
      //  holder.itemView.setOnClickListener(clickListener);
        

    }

    @Override
    public int getItemCount() {
        return mKeuangan.size();
    }
}
