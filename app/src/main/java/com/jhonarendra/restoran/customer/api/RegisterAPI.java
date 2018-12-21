package com.jhonarendra.restoran.customer.api;

/**
 * Created by Jhonarendra on 11/2/2018.
 */
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RegisterAPI {
    @GET("hidangan/set/limit")
    Call<Value> hidanganLimit();

    @GET("hidangan")
    Call<Value> hidangan();

    @GET("hidangan/kategori/{kategori}")
    Call<Value> hidanganKategori(@Path("kategori") String kategori);

    @GET("hidangan/kategori/{kategori}/limit")
    Call<Value> hidanganKategoriLimit(@Path("kategori") String kategori);

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

    @FormUrlEncoded
    @PUT("pelanggan/{id}")
    Call<Value> editPelanggan(@Path("id") String id,
                              @Field("nama_pelanggan") String nama_pelanggan,
                              @Field("email_pelanggan") String email_pelanggan,
                              @Field("username_pelanggan") String username_pelanggan);

    @GET("komentar/{id}")
    Call<Value> komentarPelanggan(@Path("id") String id);

    @DELETE("komentar/{id}")
    Call<Value> deteleKomentarPelanggan(@Path("id") String id);

    @FormUrlEncoded
    @PUT("komentar/{id}")
    Call<Value> editKomentarPelanggan(@Path("id") String id,
                                      @Field("isi_komentar") String isi_komentar);

    @FormUrlEncoded
    @POST("komentar")
    Call<Value> kirimKomentar(@Field("id_pelanggan") String id_pelanggan,
                         @Field("isi_komentar") String isi_komentar);

    @FormUrlEncoded
    @POST("fcm")
    Call<Value> registerToken(@Field("token") String token);

    @DELETE("pelanggan/{id}")
    Call<Value> hapusPelanggan(@Path("id") String id);
}
