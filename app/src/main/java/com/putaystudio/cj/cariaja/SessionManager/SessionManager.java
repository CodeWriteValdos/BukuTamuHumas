package com.putaystudio.cj.cariaja.SessionManager;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context _context;
    private static final String PREF_NAME = "session_laporkeun";
    private static final int PRIVATE_MODE = 0;
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "your_example@mail.com";
    private static final String KEY_NAMA  = "Name of User";
    private static final String IS_LOGIN = "login";
    private static final  String IMAGE_KEY = "userdefault.jpg";
    private static final String KEY_NOHP = "nohp";

    public  SessionManager(Context context)
    {
        this._context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void setKeyEmail(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public  String getKeyEmail() {
        return sharedPreferences.getString(KEY_EMAIL, "your_example");
    }

    public void setKeyNama(String nama)
    {
        editor.putString(KEY_NAMA,nama);
        editor.commit();
    }

    public String getKeyNama() {
        return sharedPreferences.getString(KEY_NAMA,"Your Name");
    }

    public void setIsLogin(boolean login)
    {
        editor.putBoolean(IS_LOGIN,login);
        editor.commit();
    }

    public boolean getIsLogin()
    {
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }

    public void setImageKey(String image)
    {
        editor.putString(IMAGE_KEY,image);
        editor.commit();
    }

    public String getImageKey()
    {
        return sharedPreferences.getString(IMAGE_KEY,"userdefault.jpg");
    }

    public void setKeyNohp(String nohp)
    {
        editor.putString(KEY_NOHP,nohp);
        editor.commit();
    }

    public String getKeyNohp()
    {
        return sharedPreferences.getString(KEY_NOHP,"");
    }

    public void setKeyId(String id)
    {
        editor.putString(KEY_ID,id);
        editor.commit();
    }

    public String getKeyId()
    {
        return sharedPreferences.getString(KEY_ID,"");
    }



}
