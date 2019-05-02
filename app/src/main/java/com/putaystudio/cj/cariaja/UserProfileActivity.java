package com.putaystudio.cj.cariaja;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.putaystudio.cj.cariaja.SessionManager.SessionManager;
import com.squareup.picasso.Picasso;

public class UserProfileActivity extends AppCompatActivity {
    private Toolbar toolbar;
    SessionManager sessionManager;
    private TextView tvName,tvEmail,tvHp;
    private ImageView imageUser;
    private Button btnEdit;
    private static final String base_url = "http://kitekodein.com/Laporkeun/public/images/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        toolbar = (Toolbar) findViewById(R.id.ToolbarProfile);
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sessionManager = new SessionManager(this);
        tvName = (TextView) findViewById(R.id.tvName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvHp = (TextView)findViewById(R.id.tvHp);
        imageUser = findViewById(R.id.imageUser);
        btnEdit = findViewById(R.id.btnEdit);

        tvName.setText(sessionManager.getKeyNama());
        tvEmail.setText(sessionManager.getKeyEmail());
        if (sessionManager.getKeyNohp().equals(""))
        {
            tvHp.setText("Nomer belum di set");
        }else{
            tvHp.setText(sessionManager.getKeyNohp());
        }

        Log.v("IMAGE_NAME",base_url+sessionManager.getImageKey());
        if (sessionManager.getImageKey().equals("userdefault.jpg"))
        {
            imageUser.setImageResource(R.drawable.userdefault);
        }else {
            Picasso.get().load(base_url+sessionManager.getImageKey()).into(imageUser);
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UpdateProfileActivity.class));
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomepageActivity.class));
        return true;
    }
}
