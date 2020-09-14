package com.example.ourmasjid.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ourmasjid.Activity.PengurusActivity;
import com.example.ourmasjid.R;
import com.example.ourmasjid.RequestHandler;
import com.example.ourmasjid.SharedPrefManager;
import com.example.ourmasjid.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.HashMap;

public class ProfileMasjidFragment extends Fragment {
    TextView namamasjid,alamat,nohp;
    EditText Email, password, Nama;
    Button buttonemail, buttonpengurus, buttonpassword;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profilemasjid, container, false);
        namamasjid=view.findViewById(R.id.namamasjidpengururs);
        alamat=view.findViewById(R.id.alamatmasjidpengurus);
        nohp=view.findViewById(R.id.nohppengurus);

        Email=view.findViewById(R.id.editTextemail);
        password=view.findViewById(R.id.editTextpassword);
        Nama=view.findViewById(R.id.editTextnamapengurus);

        namamasjid.setText(SharedPrefManager.NAMAMASJID);
        alamat.setText(SharedPrefManager.ALAMAT);
       nohp.setText(SharedPrefManager.NOHP);

       Email.setText(SharedPrefManager.EMAIL);
       Nama.setText(SharedPrefManager.NAMA);

       buttonemail=view.findViewById(R.id.buttonemail);
       buttonpassword=view.findViewById(R.id.buttonpassword);
       buttonpengurus=view.findViewById(R.id.buttonpengurus);

       buttonemail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               updatePengurusemail();
           }
       });
       buttonpengurus.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               updatePengurusnama();
           }
       });
       buttonpassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               updatePenguruspassword();
           }
       });

        return view;

    }

    private void updatePengurusemail(){
        final String emailtext=Email.getText().toString().trim();
        final String namatext=Nama.getText().toString().trim();
        final String passwordtext=password.getText().toString().trim();

        if(TextUtils.isEmpty(emailtext)){
            Email.setError("Please enter Email");
            Email.requestFocus();
        }



        class UpdatePengurus extends AsyncTask<Void, Void, String>{
            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();
                int id=SharedPrefManager.IDPENGURUS;
                SharedPrefManager.EMAIL=emailtext;
                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("email_pengurus", emailtext);
                params.put("id_pengurus", Integer.toString(id));


                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_UPDATEPROFILE, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = (ProgressBar) getView().findViewById(R.id.progressBarprofile);
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
                        Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getActivity(), "Some error occurred", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        UpdatePengurus ru = new UpdatePengurus();
        ru.execute();
        }

    private void updatePengurusnama(){
        final String namatext=Nama.getText().toString().trim();
        final String passwordtext=password.getText().toString().trim();

        if(TextUtils.isEmpty(namatext)){
            Nama.setError("Please enter Nama Pengurus");
            Nama.requestFocus();
        }



        class UpdatePengurus extends AsyncTask<Void, Void, String>{
            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();
                int id=SharedPrefManager.IDPENGURUS;
                SharedPrefManager.NAMA=namatext;
                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("nama_pengurus", namatext);
                params.put("id_pengurus", Integer.toString(id));


                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_UPDATEPROFILE, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = (ProgressBar) getView().findViewById(R.id.progressBarprofile);
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
                        Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getActivity(), "Some error occurred", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        UpdatePengurus ru = new UpdatePengurus();
        ru.execute();
    }

    private void updatePenguruspassword(){

        final String passwordtext=password.getText().toString().trim();

        if(TextUtils.isEmpty(passwordtext)){
            password.setError("Please enter new password");
            password.requestFocus();
        }



        class UpdatePengurus extends AsyncTask<Void, Void, String>{
            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();
                int id=SharedPrefManager.IDPENGURUS;
                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("password", passwordtext);
                params.put("id_pengurus", Integer.toString(id));


                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_UPDATEPROFILE, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = (ProgressBar) getView().findViewById(R.id.progressBarprofile);
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
                        Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getActivity(), "Some error occurred", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        UpdatePengurus ru = new UpdatePengurus();
        ru.execute();
    }


}
