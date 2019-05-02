package com.putaystudio.cj.cariaja;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NoConnectionActivity extends AppCompatActivity {
    private Button btnCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);

        btnCheck = findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager CM = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = CM.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnectedOrConnecting())
                {
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }else{
                    Toast.makeText(NoConnectionActivity.this, "Tidak ada Koneksi yang tersambung", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
