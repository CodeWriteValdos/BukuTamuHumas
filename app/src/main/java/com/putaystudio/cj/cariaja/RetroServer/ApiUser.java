package com.putaystudio.cj.cariaja.RetroServer;

import com.putaystudio.cj.cariaja.Model.JSONResponse;
import com.putaystudio.cj.cariaja.Model.ModelHistori;
import com.putaystudio.cj.cariaja.Model.ModelUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiUser {

    @FormUrlEncoded
    @POST("api/login")
    Call<ModelUser>  goLogin(@Field("email") String email,
                             @Field("password") String password);

    @FormUrlEncoded
    @POST("editProfile")
    Call<ModelUser> editProfile(@Field("id") String id,
                                @Field("name") String name,
                                @Field("no_hp") String no_hp);
    @FormUrlEncoded
    @POST("createhistory")
    Call<ModelHistori> setHistory(@Field("nis") String nis,@Field("user_id") String user_id);

    @GET("history/{id}")
    Call<JSONResponse> getAllData(@Path("id")  String id);

}
