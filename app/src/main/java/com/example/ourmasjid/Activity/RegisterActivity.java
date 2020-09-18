package com.example.ourmasjid.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourmasjid.R;
import com.example.ourmasjid.RequestHandler;
import com.example.ourmasjid.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public  class RegisterActivity extends AppCompatActivity {
   private EditText email, namamasjid,alamatmasjid, password, password2;
   private Button Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerform);
        email=findViewById(R.id.emailtextregister);
        namamasjid=findViewById(R.id.textmasjidregister);
        alamatmasjid=findViewById(R.id.textalamatregister);
        password=findViewById(R.id.editTextpasswordregister);
        password2=findViewById(R.id.editTextpasswordregister2);

        Register=findViewById(R.id.Register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();

            }
        });





    }


    @Override
    public void onBackPressed(){
       finish();
    }

    private void Register(){

        final String passwordtext=password.getText().toString().trim();
        final String password2text=password2.getText().toString().trim();
        final String emailtext=email.getText().toString().trim();
        final String namatext=namamasjid.getText().toString().trim();
        final String alamattext=alamatmasjid.getText().toString().trim();

        if(TextUtils.isEmpty(passwordtext)){
            password.setError("Please enter new password");
            password.requestFocus();
        }
        if(TextUtils.isEmpty(password2text)){
            password2.setError("Please enter new password");
            password2.requestFocus();
        }
        if(password2text.compareTo(passwordtext)!=0){
            password.setError("Password harus sama");
            password.requestFocus();
            password2.setError("Password harus sama");
            password2.requestFocus();
        }
        if(TextUtils.isEmpty(emailtext)){
            email.setError("Please enter email");
            email.requestFocus();
        }
        if(TextUtils.isEmpty(namatext)){
            namamasjid.setError("Please enter nama masjid");
            namamasjid.requestFocus();
        }
        if(TextUtils.isEmpty(alamattext)){
            alamatmasjid.setError("Please enter alamat");
            alamatmasjid.requestFocus();
        }



        class UserRegister extends AsyncTask<Void, Void, String> {
            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();
                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("password", passwordtext);
                params.put("email_pengurus", emailtext);
                params.put("nama_masjid", namatext);
                params.put("alamat", alamattext);


                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = (ProgressBar) findViewById(R.id.progressBarregister);
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

        UserRegister ru = new UserRegister();
        if((!TextUtils.isEmpty(passwordtext)) || (!TextUtils.isEmpty(password2text)) || (!TextUtils.isEmpty(emailtext)) ||
                (!TextUtils.isEmpty(namatext)) || (!TextUtils.isEmpty(alamattext)) || (passwordtext.compareTo(password2text)==0)){
            ru.execute();
        }


    }




}