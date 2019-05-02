package com.putaystudio.cj.cariaja;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.JsonReader;
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
import com.tapadoo.alerter.OnHideAlertListener;

import org.json.JSONObject;

import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText tvUsername,tvPassword;
    private Button btnLogin;
    private ProgressDialog progressDialog;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvUsername = (EditText) findViewById(R.id.tvUsername);
        tvPassword = (EditText) findViewById(R.id.tvPassword);
        btnLogin   = (Button) findViewById(R.id.btnLogin);
        progressDialog = new ProgressDialog(this);
        sessionManager = new SessionManager(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username,password;
                username = tvUsername.getText().toString().trim().toLowerCase();
                password = tvPassword.getText().toString();

                progressDialog.setTitle("Mengautentikasi");
                progressDialog.setMessage("Tunggu Sebentar");
                progressDialog.show();
                ApiUser apiUser = RetroServer.getClient().create(ApiUser.class);
                retrofit2.Call<ModelUser> goLogin =  apiUser.goLogin(username,password);
                goLogin.enqueue(new Callback<ModelUser>() {
                    @Override
                    public void onResponse(retrofit2.Call<ModelUser> call, Response<ModelUser> response) {
                        if (response.body().getMessage() != null)
                        {
                            progressDialog.hide();
                            Alerter.create(LoginActivity.this)
                                    .setTitle("Peringatan")
                                    .setText("Username atau password salah")
                                    .setBackgroundColorRes(R.color.colorAccent)
                                    .disableOutsideTouch()
                                    .show();
                        }else{
                            progressDialog.dismiss();
                            Alerter.create(LoginActivity.this)
                                    .setTitle("Berhasil")
                                    .setText("Tunggu Sebentar")
                                    .setBackgroundColorRes(R.color.colorAccent)
                                    .setOnHideListener(new OnHideAlertListener() {
                                        @Override
                                        public void onHide() {
                                            Intent intent = new Intent(getApplicationContext(),HomepageActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                    })
                                    .disableOutsideTouch()
                                    .show();
                            sessionManager.setKeyId(response.body().getId());
                            sessionManager.setKeyEmail(response.body().getEmail());
                            sessionManager.setKeyNama(response.body().getName());
                            sessionManager.setIsLogin(true);
                            sessionManager.setImageKey(response.body().getImage());
                            sessionManager.setKeyNohp(response.body().getNo_hp());
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ModelUser> call, Throwable t) {
                        progressDialog.hide();
                        Toast.makeText(LoginActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
