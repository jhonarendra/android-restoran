package com.jhonarendra.restoran.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jhonarendra.restoran.customer.R;
import com.jhonarendra.restoran.customer.api.RegisterAPI;
import com.jhonarendra.restoran.customer.api.Value;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jhonarendra on 12/20/2018.
 */

public class DetailKomentarActivity extends AppCompatActivity {
    String id_komentar, aksi;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_komentar);

        Intent intent = getIntent();
        id_komentar = intent.getExtras().getString("id_komentar");
        aksi = intent.getExtras().getString("aksi");

//        switch (aksi){
//            case "Hapus":
                deleteKomentar(id_komentar);
//                Intent i = new Intent(this, MainActivity.class);
//                startActivity(i);
//                finish();
//                break;
//        }


//        if(aksi == "Hapus"){
//            deleteKomentar(id_komentar);
//
//        }



    }
    public void deleteKomentar(String idKomentar) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.deteleKomentarPelanggan(idKomentar);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                Boolean success = response.body().getSuccess();
                if(success){
                    Toast.makeText(DetailKomentarActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailKomentarActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Toast.makeText(DetailKomentarActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
