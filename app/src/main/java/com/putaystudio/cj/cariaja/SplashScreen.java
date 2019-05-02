package com.putaystudio.cj.cariaja;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.putaystudio.cj.cariaja.SessionManager.SessionManager;

public class SplashScreen extends AppCompatActivity {

    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sessionManager = new SessionManager(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ConnectivityManager CM = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = CM.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected())
                {
                    if (sessionManager.getIsLogin() == false) {
                        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }else{
                        Intent o = new Intent(getApplicationContext(),HomepageActivity.class);
                        o.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        o.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(o);
                    }
                }else{
                    startActivity(new Intent(getApplicationContext(),NoConnectionActivity.class));
                    Intent p = new Intent(getApplicationContext(),NoConnectionActivity.class);
                    p.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    p.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(p);
                }

            }
        },3000);
    }



}
