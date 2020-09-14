package com.example.ourmasjid.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.example.ourmasjid.Adapter.KegiatanMasjidAdapter;
import com.example.ourmasjid.Adapter.MasjidTerdekatAdapter;
import com.example.ourmasjid.Model.Kegiatan;
import com.example.ourmasjid.Model.KegiatanList;
import com.example.ourmasjid.Model.Masjid;
import com.example.ourmasjid.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class KegiatanMasjidFragment extends Fragment {
    RecyclerView recyclerView;
    private KegiatanMasjidAdapter mKegiatanMasjidAdapter;
    private RequestQueue mRequestQueue;
    private ArrayList<Kegiatan> mKegiatanList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_kegiatanmasjid, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_kegiatanmasjid);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        JsonGet();
        return view;
    }

    private void  JsonGet(){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
            String URL = "http://192.168.43.251/ourmasjid/kegiatanMasjidSpecific.php";
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
