package com.example.ourmasjid.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ourmasjid.Activity.PengurusActivity;
import com.example.ourmasjid.R;

public class RegisterFragment extends Fragment {
    private Button login, register;
    private EditText email, pass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View views = inflater.inflate(R.layout.fragment_register, container, false);
        email=views.findViewById(R.id.emailtext);
        pass=views.findViewById(R.id.editTextTextPasswordLogin);
        login = views.findViewById(R.id.Login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((email.getText().toString().compareTo("rayhan@gmail.com")==0) &&
                        (pass.getText().toString().compareTo("123456")==0)){

                    Intent intent=new Intent(view.getContext(), PengurusActivity.class);
                    view.getContext().startActivity(intent);
                    Activity activity=(Activity) getActivity();
                    activity.finish();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return views;


    }
}
