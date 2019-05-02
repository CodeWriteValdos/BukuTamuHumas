package com.putaystudio.cj.cariaja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.putaystudio.cj.cariaja.Model.ModelHistori;
import com.putaystudio.cj.cariaja.Model.ModelSiswa;
import com.putaystudio.cj.cariaja.Model.ModelUser;
import com.putaystudio.cj.cariaja.RetroServer.ApiSiswa;
import com.putaystudio.cj.cariaja.RetroServer.ApiUser;
import com.putaystudio.cj.cariaja.RetroServer.RetroServer;
import com.putaystudio.cj.cariaja.SessionManager.SessionManager;
import com.squareup.picasso.Picasso;
import com.tapadoo.alerter.Alerter;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.felix.imagezoom.ImageZoom;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {
    private IntentIntegrator qrScan;
    private ImageZoom resultFoto;
    private TextView resultNama,resultRombel,resultRayon;
    private Button btnBack;

    public static String base_url = "http://kitekodein.com/Laporkeun/public/images/siswa/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultFoto = findViewById(R.id.resultFoto);
        resultNama = findViewById(R.id.resultNama);
        resultRayon = findViewById(R.id.resultRayon);
        resultRombel = findViewById(R.id.resultRombel);
        btnBack = findViewById(R.id.btnBack);

        Bundle bundle = getIntent().getExtras();

        if (bundle.getString("NIS") != null)
        {
            Picasso.get().load(base_url+bundle.getString("FOTO")).fit().centerCrop().into(resultFoto);
            resultNama.setText(bundle.getString("NAMA"));
            resultRayon.setText(bundle.getString("RAYON"));
            resultRombel.setText(bundle.getString("ROMBEL"));
        }else{
            qrScan = new IntentIntegrator(this);
            qrScan.initiateScan();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomepageActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null)
        {
            if (result.getContents() == null)
            {
                startActivity(new Intent(getApplicationContext(),HomepageActivity.class));
            }else{
                try{
                    JSONObject obj = new JSONObject(result.getContents());
                    Log.v("JSON",result.getContents());
                    final ApiSiswa apiSiswa = RetroServer.getClient().create(ApiSiswa.class);
                    retrofit2.Call<ModelSiswa> getData = apiSiswa.getSiswa(obj.getString("nis"));
                    getData.enqueue(new Callback<ModelSiswa>() {
                        @Override
                        public void onResponse(Call<ModelSiswa> call, Response<ModelSiswa> response) {
                            Alerter.create(ResultActivity.this)
                                    .setTitle("Data ditemukan")
                                    .setText("Detail...")
                                    .setBackgroundColorRes(R.color.colorAccent)
                                    .disableOutsideTouch()
                                    .show();
                            Picasso.get()
                                    .load(base_url+response.body().getFoto())
                                    .fit().centerCrop()
                                    .into(resultFoto);
                            resultNama.setText(response.body().getNama());
                            resultRayon.setText(response.body().getRayon());
                            resultRombel.setText(response.body().getRombel());

                            ApiUser apiUser  = RetroServer.getClient().create(ApiUser.class);
                            SessionManager sessionManager = new SessionManager(getApplicationContext());
                            Call<ModelHistori> process_create = apiUser.setHistory(response.body().getNis(),sessionManager.getKeyId());
                            process_create.enqueue(new Callback<ModelHistori>() {
                                @Override
                                public void onResponse(Call<ModelHistori> call, Response<ModelHistori> response) {
                                    Log.d("SUCCESS","yee");
                                }

                                @Override
                                public void onFailure(Call<ModelHistori> call, Throwable t) {
                                    Log.e("ERROR",t.getMessage());
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<ModelSiswa> call, Throwable t) {
                            Log.d("ERROR","Terjadi Kesalahan");
                            Log.e("ERROR",t.getMessage());
                        }
                    });
                }catch (JSONException e)
                {
                    e.printStackTrace();
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),HomepageActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
