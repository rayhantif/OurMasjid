package com.example.ourmasjid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ourmasjid.Fragment.HomeFragment;
import com.example.ourmasjid.Fragment.LoginFragment;
import com.example.ourmasjid.Fragment.MasjidTerdekatFragment;
import com.example.ourmasjid.R;
import com.example.ourmasjid.SharedPrefManager;
import com.google.android.material.navigation.NavigationView;


public  class DetailMasjidActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_masjid);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    @Override
    public void onBackPressed(){
        finish();

    }




}