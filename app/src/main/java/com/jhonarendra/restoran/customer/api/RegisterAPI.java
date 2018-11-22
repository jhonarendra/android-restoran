package com.jhonarendra.restoran.customer.api;

/**
 * Created by Jhonarendra on 11/2/2018.
 */
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.GET;
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

//    @GET("hidangan/kategori/Burger")
//    Call<Value> burger();
//    @GET("hidangan/kategori/Burger/limit")
//    Call<Value> burgerLimit();
//
//    @GET("hidangan/kategori/Salad")
//    Call<Value> salad();
//    @GET("hidangan/kategori/Salad/limit")
//    Call<Value> saladLimit();
//
//    @GET("hidangan/kategori/Minuman")
//    Call<Value> minuman();
//    @GET("hidangan/kategori/Minuman/limit")
//    Call<Value> minumanLimit();
//
//    @GET("hidangan/kategori/Dessert")
//    Call<Value> dessert();
//    @GET("hidangan/kategori/Dessert/limit")
//    Call<Value> dessertLimit();
//
//    @GET("hidangan/kategori/Breakfast")
//    Call<Value> breakfast();
//    @GET("hidangan/kategori/Breakfast/limit")
//    Call<Value> breakfastLimit();

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

    @GET("komentar/{id}")
    Call<Value> komentarPelanggan(@Path("id") String id);

    @FormUrlEncoded
    @POST("komentar")
    Call<Value> kirimKomentar(@Field("id_pelanggan") String id_pelanggan,
                         @Field("isi_komentar") String isi_komentar);
}
