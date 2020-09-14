package com.example.ourmasjid.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ourmasjid.Activity.MainActivity;
import com.example.ourmasjid.Activity.PengurusActivity;
import com.example.ourmasjid.Model.Masjid;
import com.example.ourmasjid.Model.Pengurus;
import com.example.ourmasjid.R;
import com.example.ourmasjid.RequestHandler;
import com.example.ourmasjid.SharedPrefManager;
import com.example.ourmasjid.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginFragment extends Fragment {
    private Button login, register;
     private EditText email, pass;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View views = inflater.inflate(R.layout.fragment_login, container, false);
        email = views.findViewById(R.id.emailtext);
        pass = views.findViewById(R.id.editTextTextPasswordLogin);
        login = views.findViewById(R.id.Login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if((email.getText().toString().compareTo("rayhan@gmail.com")==0) &&
                        (pass.getText().toString().compareTo("123456")==0)){

                    Intent intent=new Intent(view.getContext(), PengurusActivity.class);
                    view.getContext().startActivity(intent);
                    Activity activity=(Activity) getActivity();
                    activity.finish();
                }*/
                userLogin();
            }
        });




                  /* final FragmentTransaction ft = getFragmentManager().beginTransaction();
                   ft.replace(R.id.textViewRegister, new RegisterFragment(), "NewFragmentTag");
                   ft.commit();*/





        return views;
    }

    private void userLogin(){
        final String email_akun=email.getText().toString();
        final String password_akun=pass.getText().toString();

        if(TextUtils.isEmpty(email_akun)){
            email.setError("Please enter your email");
            email.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(password_akun)){
            pass.setError("Please enter your password");
            pass.requestFocus();
            return;
        }

        class UserLogin extends AsyncTask<Void, Void, String>{
            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {


                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        Pengurus pengurus = new Pengurus(
                                userJson.getInt("id_pengurus"),
                                userJson.getInt("id_masjid"),
                                userJson.getString("nama_pengurus"),
                                userJson.getString("email_pengurus")
                        );
                        JSONObject masjidJson = obj.getJSONObject("masjid");
                        Masjid masjid=new Masjid(
                                masjidJson.getInt("id_masjid"),
                                masjidJson.getString("nama_masjid"),
                                masjidJson.getString("alamat"),
                                masjidJson.getString("nohp"),
                                masjidJson.getString("longitude"),
                                masjidJson.getString("latitude")
                        );

                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getActivity()).userLogin(pengurus, masjid);
                        Toast.makeText(getActivity(), "Login Succesful", Toast.LENGTH_SHORT).show();
                        //starting the profile activity
                        getActivity().finish();
                        startActivity(new Intent(getActivity(), PengurusActivity.class));
                    } else {
                        Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("email_pengurus", email_akun);
                params.put("password", password_akun);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();

    }
}
