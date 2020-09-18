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


public  class KeuanganFormActivity extends AppCompatActivity {
   private EditText namatransaksi, tanggal, jumlahtransaksi;
   Spinner jenis;
   private Button simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keuanganform);
        namatransaksi=findViewById(R.id.editTextnamatransaksi);
        tanggal=findViewById(R.id.editTextTanggaltransaksi);
        jumlahtransaksi=findViewById(R.id.editTextJumlahtransaksi);
        jenis=findViewById(R.id.spinnerjenistransaksi);
        String[] listjenis=new String[]{"Pemasukan", "Pengeluaran"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listjenis);
        jenis.setAdapter(adapter);

        simpan=findViewById(R.id.buttonsimpantransaksi);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TambahTransaksi();
            }
        });

    }


    @Override
    public void onBackPressed(){
       finish();
    }

    private void TambahTransaksi(){

      final String namatext=namatransaksi.getText().toString().trim();
        final String tanggaltext=tanggal.getText().toString().trim();
        final String jumlahtext=jumlahtransaksi.getText().toString().trim();
        final String jenistext=jenis.getSelectedItem().toString().trim();

        if(TextUtils.isEmpty(namatext)){
            namatransaksi.setError("Please enter Nama Transaksi");
            namatransaksi.requestFocus();
        }
        if(TextUtils.isEmpty(tanggaltext)){
            tanggal.setError("Please enter Tanggal transaksi");
            tanggal.requestFocus();
        }
        if(TextUtils.isEmpty(jumlahtext)){
            jumlahtransaksi.setError("Please enter Nominal transaksi");
            jumlahtransaksi.requestFocus();
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
                params.put("id_kegiatan", "1");
                params.put("nama_transaksi", namatext);
                if(jenistext.compareTo("Pemasukan")==0){
                    params.put("jenis_keuangan","0");
                }else{
                    params.put("jenis_keuangan","1");
                }
                params.put("tanggal", tanggaltext);
                params.put("nominal", jumlahtext);
                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_TAMBAHKEUANGAN, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = (ProgressBar) findViewById(R.id.progressBartransaksi);
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
        if((!TextUtils.isEmpty(namatext)) || (!TextUtils.isEmpty(tanggaltext)) || (!TextUtils.isEmpty(jumlahtext))){
            ru.execute();
        }


    }




}