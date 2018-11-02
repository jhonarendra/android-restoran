package com.jhonarendra.restoran.customer.api;

/**
 * Created by Jhonarendra on 11/2/2018.
 */
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface RegisterAPI {
    @GET("select.php")
    Call<Value> view();
//    @FormUrlEncoded
//    @POST("/insert.php")
//    Call<Value> daftar(@Field("nim_mhs") String nim_mhs,
//                       @Field("nama_mhs") String nama_mhs);
}
