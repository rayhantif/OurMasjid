package com.example.ourmasjid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ourmasjid.Activity.PengurusActivity;
import com.example.ourmasjid.R;
import com.example.ourmasjid.SharedPrefManager;

public class ProfileMasjidFragment extends Fragment {
    TextView namamasjid,alamat,nohp;
    EditText Email, password, Nama;
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

        return view;

    }

}
