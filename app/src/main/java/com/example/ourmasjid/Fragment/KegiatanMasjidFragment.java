package com.example.ourmasjid.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ourmasjid.Activity.KegiatanFormActivity;
import com.example.ourmasjid.Adapter.KegiatanMasjidAdapter;
import com.example.ourmasjid.Model.Kegiatan;
import com.example.ourmasjid.Model.KegiatanList;
import com.example.ourmasjid.R;
import com.example.ourmasjid.SharedPrefManager;
import com.example.ourmasjid.URLs;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class KegiatanMasjidFragment extends Fragment {
    RecyclerView recyclerView;
    private KegiatanMasjidAdapter mKegiatanMasjidAdapter;
    private ArrayList<Kegiatan> mKegiatanList;
    Button tambah;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_kegiatanmasjid, container, false);
        tambah=view.findViewById(R.id.buttonTambahkegiatan);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_kegiatanmasjid);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        JsonGet();
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), KegiatanFormActivity.class));
            }
        });
        return view;
    }

    private void  JsonGet(){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
            String URL = "http://"+ URLs.IP+"/ourmasjid/kegiatanMasjidSpecific.php?id_masjid="+ SharedPrefManager.IDMASJID;
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("Title", "Android Volley Demo");
            jsonBody.put("Author", "BNK");
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.i("VOLLEY", response);
                    Gson gson=new Gson();
                    KegiatanList isi=gson.fromJson(response, KegiatanList.class);
                    mKegiatanList=isi.getkegiatan();
                    mKegiatanMasjidAdapter=new KegiatanMasjidAdapter(getActivity().getApplicationContext(), isi.getkegiatan());
                    recyclerView.setAdapter(mKegiatanMasjidAdapter);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        String statusCode = String.valueOf(response.statusCode);
                        //Handling logic
                        return super.parseNetworkResponse(response);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
