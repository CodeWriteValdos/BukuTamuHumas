package com.putaystudio.cj.cariaja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.putaystudio.cj.cariaja.Model.ModelSiswa;
import com.putaystudio.cj.cariaja.RetroServer.ApiSiswa;
import com.putaystudio.cj.cariaja.RetroServer.RetroServer;
import com.putaystudio.cj.cariaja.SessionManager.SessionManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar homeToolbar;
    SessionManager sessionManager;
    private Button btnScan;
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.HomeToolbar);
        setSupportActionBar(homeToolbar);
        sessionManager = new SessionManager(getApplicationContext());

        btnScan = findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ResultActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_profile)
        {
            startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
        }else if(id == R.id.action_pengaturan)
        {
            Toast.makeText(this, "Menu File", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_logout)
        {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            sessionManager.setImageKey("");
            sessionManager.setKeyId("");
            sessionManager.setKeyEmail("");
            sessionManager.setKeyNama("");
            sessionManager.setIsLogin(false);
        }

        return super.onOptionsItemSelected(item);
    }


}
