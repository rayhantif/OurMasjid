package com.example.ourmasjid.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ourmasjid.Activity.MainActivity;
import com.example.ourmasjid.Fragment.HomeFragment;
import com.example.ourmasjid.R;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MasjidTerdekatAdapter extends RecyclerView.Adapter<MasjidTerdekatAdapter.MyViewHolder> {
    private String[] masjidlist={"Masjid An Nur", "Masjid Al-aqsa", "Masjid Faisal", "Masjid Badashi"};
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