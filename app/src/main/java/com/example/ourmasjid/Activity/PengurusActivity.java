package com.example.ourmasjid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ourmasjid.Fragment.HomePengurusFragment;
import com.example.ourmasjid.Fragment.KegiatanMasjidFragment;
import com.example.ourmasjid.Fragment.KeuanganMasjidFragment;
import com.example.ourmasjid.Fragment.ProfileMasjidFragment;
import com.example.ourmasjid.Model.Masjid;
import com.example.ourmasjid.R;
import com.example.ourmasjid.SharedPrefManager;
import com.google.android.material.navigation.NavigationView;

public class PengurusActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Masjid mmasjid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.drawer_menu_pengurus);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new
                    HomePengurusFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }


    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_beranda_pengurus:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new
                        HomePengurusFragment()).commit();
                break;
            case R.id.nav_masjid:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new
                        ProfileMasjidFragment()).commit();
                break;
            case R.id.nav_kegiatan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new
                        KegiatanMasjidFragment()).commit();
                break;
            case R.id.nav_keuangan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new
                        KeuanganMasjidFragment()).commit();
                break;
            case R.id.nav_logout:
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setMmasjid(Masjid masjid) {
        this.mmasjid = masjid;
    }

    public Masjid getMmasjid() {
        return mmasjid;
    }
}
