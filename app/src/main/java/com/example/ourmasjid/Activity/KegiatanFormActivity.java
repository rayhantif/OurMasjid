package com.example.ourmasjid.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourmasjid.R;
import com.example.ourmasjid.RequestHandler;
import com.example.ourmasjid.SharedPrefManager;
import com.example.ourmasjid.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public  class KegiatanFormActivity extends AppCompatActivity {
   private EditText namakegiatan, tanggal,lokasi, pemateri, pj, laporankegiatan, linkvideokegiatan;
   private Button simpan;
   Spinner jenis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kegiatanform);
        namakegiatan=findViewById(R.id.editTextnamakegiatan);
        tanggal=findViewById(R.id.editTextTanggalKegiatan);
        lokasi=findViewById(R.id.editTextLokasiKegiatan);
        pemateri=findViewById(R.id.editTextPemateri);
        pj=findViewById(R.id.editTextPJ);
        jenis=findViewById(R.id.spinnerjenis);
        laporankegiatan=findViewById(R.id.editTextLaporankegiatan);
        linkvideokegiatan=findViewById(R.id.editTextVideoKegiatan);
        String[] listjenis=new String[]{"Dalam Masjid", "Luar Masjid"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listjenis);
        jenis.setAdapter(adapter);

        simpan=findViewById(R.id.buttonsimpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TambahKegiatan();
            }
        });

    }


    @Override
    public void onBackPressed(){
       finish();
    }

    private void TambahKegiatan(){
        int jumlah=10;
       final String namatext=namakegiatan.getText().toString().trim();
        final String tanggaltext=tanggal.getText().toString().trim();
        final String lokasitext=lokasi.getText().toString().trim();
        final String pemateritext=pemateri.getText().toString().trim();
        final String pjtext=pj.getText().toString().trim();
        final String jenistext=jenis.getSelectedItem().toString().trim();
        final String laporantext=laporankegiatan.getText().toString().trim();
        final String videotext=linkvideokegiatan.getText().toString().trim();

        if(TextUtils.isEmpty(namatext)){
            namakegiatan.setError("Please enter Nama Kegiatan");
            namakegiatan.requestFocus();
        }
        if(TextUtils.isEmpty(tanggaltext)){
            tanggal.setError("Please enter Tanggal Kegiatan");
            tanggal.requestFocus();
        }
        if(TextUtils.isEmpty(lokasitext)){
            lokasi.setError("Please enter Lokasi Kegiatan");
            lokasi.requestFocus();
        }
        if(TextUtils.isEmpty(pemateritext)){
            pemateri.setError("Please enter Pemateri");
            pemateri.requestFocus();
        }
        if(TextUtils.isEmpty(pjtext)){
            pj.setError("Please enter Penanggugn jawab kegiatan");
            pj.requestFocus();
        }
        if(TextUtils.isEmpty(laporantext)){
            jumlah-=1;
        }
        if(TextUtils.isEmpty(videotext)){
            jumlah-=2;
        }



        class Tambah extends AsyncTask<Void, Void, String> {
            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();
                //creating request parameters
                HashMap<String, String> params = new HashMap<>();

                params.put("id_masjid", Integer.toString(SharedPrefManager.IDMASJID));
                params.put("nama_kegiatan", namatext);
                if(jenistext.compareTo("Dalam Masjid")==0){
                    params.put("jenis_kegiatan","0");
                }else{
                    params.put("jenis_kegiatan","1");
                }
                params.put("waktu_kegiatan", tanggaltext);
                params.put("lokasi_kegiatan", lokasitext);
                params.put("penanggung_jawab", pjtext);
                params.put("pemateri", pemateritext);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_TAMBAHKEGIATAN, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = (ProgressBar) findViewById(R.id.progressBarkegiatan);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task

        Tambah ru = new Tambah();
        if((!TextUtils.isEmpty(namatext)) || (!TextUtils.isEmpty(tanggaltext)) || (!TextUtils.isEmpty(lokasitext)) ||
                (!TextUtils.isEmpty(pemateritext)) || (!TextUtils.isEmpty(pjtext))){
            ru.execute();
        }


    }




}