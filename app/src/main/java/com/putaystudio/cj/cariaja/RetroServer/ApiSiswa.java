package com.putaystudio.cj.cariaja.RetroServer;

import com.putaystudio.cj.cariaja.Model.ModelSiswa;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiSiswa {
    @GET("api/siswa/{nis}")
    Call<ModelSiswa> getSiswa(@Path("nis") String nis);
}
