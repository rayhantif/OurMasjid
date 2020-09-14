package com.example.ourmasjid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.ourmasjid.Activity.MainActivity;
import com.example.ourmasjid.Model.Masjid;
import com.example.ourmasjid.Model.Pengurus;

public class SharedPrefManager {
    //the constants
    private static final String SHARED_PREF_NAME = "OurMasjidPref";
    private static final String KEY_NAMA = "keynamapengurus";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_IDPENGURUS = "keyidpengurus";
    private static final String KEY_IDMASJID = "keyidmasjid";
    public static int IDMASJID=-1;
    public static String NAMAMASJID="x";
    public static String ALAMAT="x";
    public static String NOHP="x";
    public static String NAMA="x";
    public static String EMAIL="x";
    public static int IDPENGURUS=1;

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static String getKeyNama() {
        return KEY_NAMA;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(Pengurus pengurus, Masjid masjid) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_IDPENGURUS, pengurus.getMidpengurus());
        editor.putInt(KEY_IDMASJID, pengurus.getMidmasjid());
        editor.putString(KEY_EMAIL, pengurus.getMemailpengurus());
        editor.putString(KEY_NAMA, pengurus.getMnamapengurus());
        editor.apply();

        IDMASJID=pengurus.getMidmasjid();
        NAMAMASJID= masjid.getMnamamasjid();
        ALAMAT=masjid.getMalamat();
        NOHP=masjid.getMnohp();
        NAMA=pengurus.getMnamapengurus();
        EMAIL=pengurus.getMemailpengurus();
        IDPENGURUS=pengurus.getMidpengurus();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null) != null;

    }

    //this method will give the logged in user
    public Pengurus getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Pengurus(
                sharedPreferences.getInt(KEY_IDPENGURUS, -1),
                sharedPreferences.getInt(KEY_IDMASJID, -1),
                sharedPreferences.getString(KEY_NAMA, null),
                sharedPreferences.getString(KEY_EMAIL, null)

        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, MainActivity.class));
    }
}
