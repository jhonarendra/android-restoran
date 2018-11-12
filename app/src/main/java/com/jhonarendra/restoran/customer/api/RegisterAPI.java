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
    @GET("hidangan")//    @GET("select.php")
    Call<Value> view();

    @FormUrlEncoded
    @POST("pelanggan/login")
    Call<Value> login(@Field("username_pelanggan") String username_pelanggan,
                       @Field("password_pelanggan") String password_pelanggan);

    @FormUrlEncoded
    @POST("pelanggan/register")
    Call<Value> register(@Field("nama_pelanggan") String nama_pelanggan,
                         @Field("email_pelanggan") String email_pelanggan,
                         @Field("username_pelanggan") String username_pelanggan,
                      @Field("password_pelanggan") String password_pelanggan);
}
