package com.example.ourmasjid.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ourmasjid.Fragment.HomeFragment;
import com.example.ourmasjid.Fragment.HomePengurusFragment;
import com.example.ourmasjid.Fragment.KegiatanMasjidFragment;
import com.example.ourmasjid.Fragment.KeuanganMasjidFragment;
import com.example.ourmasjid.Fragment.LoginFragment;
import com.example.ourmasjid.Fragment.MasjidTerdekatFragment;
import com.example.ourmasjid.Fragment.ProfilMasjidFragment;
import com.example.ourmasjid.R;
import com.google.android.material.navigation.NavigationView;

public class PengurusActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        ProfilMasjidFragment()).commit();
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
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
