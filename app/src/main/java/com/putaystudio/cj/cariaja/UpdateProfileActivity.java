
package com.putaystudio.cj.cariaja;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.putaystudio.cj.cariaja.Model.ModelUser;
import com.putaystudio.cj.cariaja.RetroServer.ApiUser;
import com.putaystudio.cj.cariaja.RetroServer.RetroServer;
import com.putaystudio.cj.cariaja.SessionManager.SessionManager;
import com.tapadoo.alerter.Alerter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity {
    private Toolbar ProfileToobar;
    private EditText tveName,tveEmail,tveNohp;
    private Button btnEdit;
    SessionManager sessionManager;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        ProfileToobar = findViewById(R.id.ToolbarUpdate);
        setSupportActionBar(ProfileToobar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ProfileToobar.setTitle("Edit Profile");

        tveName = findViewById(R.id.tveName);
        tveNohp = findViewById(R.id.tveNohp);
        btnEdit = findViewById(R.id.btnEdit);

        progressDialog = new ProgressDialog(this);
        sessionManager = new SessionManager(this);

        tveName.setText(sessionManager.getKeyNama().toString());
        if (!sessionManager.getKeyNohp().equals(null)) {
            tveNohp.setText(sessionManager.getKeyNohp().toString());
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,nohp;
                name = tveName.getText().toString();
                nohp = tveNohp.getText().toString();
                if (name.isEmpty() || nohp.isEmpty())
                {
                    Alerter.create(UpdateProfileActivity.this)
                            .setTitle("Peringatan")
                            .setText("Lengkapi data")
                            .setBackgroundColorRes(R.color.colorAccent)
                            .disableOutsideTouch()
                            .show();
                }else{
                    ApiUser apiUser = RetroServer.getClient().create(ApiUser.class);
                    String id = sessionManager.getKeyId().toString();
                    Call<ModelUser> editProfile = apiUser.editProfile(id,name,nohp);
                    editProfile.enqueue(new Callback<ModelUser>() {
                        @Override
                        public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                            if (response.body().getStatus() != null)
                            {
                                Toast.makeText(UpdateProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }else{
                                sessionManager.setKeyId(response.body().getId());
                                sessionManager.setKeyEmail(response.body().getEmail());
                                sessionManager.setKeyNama(response.body().getName());
                                sessionManager.setIsLogin(true);
                                sessionManager.setImageKey(response.body().getImage());
                                sessionManager.setKeyNohp(response.body().getNo_hp());
                                Toast.makeText(UpdateProfileActivity.this, "Berhasil Update", Toast.LENGTH_SHORT).show();
                                Intent AfterUpdate = new Intent(getApplicationContext(),UserProfileActivity.class);
                                AfterUpdate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                AfterUpdate.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(AfterUpdate);
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelUser> call, Throwable t) {
                            Toast.makeText(UpdateProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
