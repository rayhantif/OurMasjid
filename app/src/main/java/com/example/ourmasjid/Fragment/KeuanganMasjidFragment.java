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
import com.example.ourmasjid.Activity.KeuanganFormActivity;
import com.example.ourmasjid.Adapter.KeuanganMasjidAdapter;
import com.example.ourmasjid.Model.Keuangan;
import com.example.ourmasjid.Model.KeuanganList;
import com.example.ourmasjid.R;
import com.example.ourmasjid.SharedPrefManager;
import com.example.ourmasjid.URLs;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class KeuanganMasjidFragment extends Fragment {
    RecyclerView recyclerView;
    private KeuanganMasjidAdapter mKeuanganMasjidAdapter;
    private ArrayList<Keuangan> mKeuanganList;
    Button tambahtransaksi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_keuanganmasjid, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_keuanganmasjid);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        JsonGet();
        tambahtransaksi=view.findViewById(R.id.buttonTambahtransaksi);
        tambahtransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), KeuanganFormActivity.class));
            }
        });

        return view;
    }
    private void  JsonGet(){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
            String URL = "http://"+ URLs.IP+"/ourmasjid/keuanganDisplayAll.php?id_masjid="+ SharedPrefManager.IDMASJID;
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("Title", "Android Volley Demo");
            jsonBody.put("Author", "BNK");
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.i("VOLLEY", response);
                    Gson gson=new Gson();
                    KeuanganList isi=gson.fromJson(response, KeuanganList.class);
                    mKeuanganList=isi.getkeuangan();
                    mKeuanganMasjidAdapter=new KeuanganMasjidAdapter(getActivity().getApplicationContext(), isi.getkeuangan());
                    recyclerView.setAdapter(mKeuanganMasjidAdapter);

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
