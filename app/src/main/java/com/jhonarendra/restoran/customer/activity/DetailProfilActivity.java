package com.jhonarendra.restoran.customer.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jhonarendra.restoran.customer.R;
import com.jhonarendra.restoran.customer.api.RegisterAPI;
import com.jhonarendra.restoran.customer.api.Value;
import com.jhonarendra.restoran.customer.storage.PreferencesHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jhonarendra on 12/20/2018.
 */

public class DetailProfilActivity extends AppCompatActivity {
    String namaPelanggan, emailPelanggan, usernamePelanggan, idPelanggan;
    EditText etEditNama, etEditEmail, etEditUsername;
    Button btnEditSubmit;
    SharedPreferences sharedPreferences;
    PreferencesHelper preferencesHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profil);

        preferencesHelper = new PreferencesHelper(this);
        sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        etEditNama = findViewById(R.id.et_edit_nama);
        etEditEmail = findViewById(R.id.et_edit_email);
        etEditUsername = findViewById(R.id.et_edit_username);
        btnEditSubmit = findViewById(R.id.btn_edit_submit);

        namaPelanggan = intent.getExtras().getString("nama_pelanggan");
        emailPelanggan = intent.getExtras().getString("email_pelanggan");
        usernamePelanggan = intent.getExtras().getString("username_pelanggan");
        idPelanggan = sharedPreferences.getString("id", "");

        etEditNama.setText(namaPelanggan);
        etEditEmail.setText(emailPelanggan);
        etEditUsername.setText(usernamePelanggan);

        btnEditSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaEdit = String.valueOf(etEditNama.getText());
                String emailEdit = String.valueOf(etEditEmail.getText());
                String usernameEdit = String.valueOf(etEditUsername.getText());

                Toast.makeText(DetailProfilActivity.this, ""+idPelanggan, Toast.LENGTH_SHORT).show();
                editPelanggan(idPelanggan, namaEdit, emailEdit, usernameEdit);
                sharedPreferences.edit()
                        .putString("login","true")
                        .putString("nama",namaEdit)
                        .putString("email",emailEdit)
                        .putString("username",usernameEdit)
                        .apply();
                finish();
            }
        });
//        id_komentar = intent.getExtras().getString("id_komentar");
//        aksi = intent.getExtras().getString("aksi");

//        switch (aksi){
//            case "Hapus":
//                deleteKomentar(id_komentar);
//                Intent i = new Intent(this, MainActivity.class);
//                startActivity(i);
//                finish();
//                break;
//            case "Edit":
//                isi_komentar = intent.getExtras().getString("isi_komentar");
//                etEditKomentar.setText("isi_komentar");
//                btnEditSubmit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        editKomentar(id_komentar, isi_komentar);
//                        Intent i2 = new Intent(DetailProfilActivity.this, MainActivity.class);
//                        startActivity(i2);
//                    }
//                });
//
//        }


//        if(aksi == "Hapus"){
//            deleteKomentar(id_komentar);
//
//        }



    }
    public void editPelanggan(String idPelanggan, String namaPelanggan, String emailPelanggan, String usernamePelanggan) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.editPelanggan(idPelanggan, namaPelanggan, emailPelanggan, usernamePelanggan);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                Boolean success = response.body().getSuccess();
                if(success){
                    Toast.makeText(DetailProfilActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailProfilActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Toast.makeText(DetailProfilActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
//    public void deleteKomentar(String idKomentar) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(MainActivity.URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        RegisterAPI api = retrofit.create(RegisterAPI.class);
//        Call<Value> call = api.deteleKomentarPelanggan(idKomentar);
//        call.enqueue(new Callback<Value>() {
//            @Override
//            public void onResponse(Call<Value> call, Response<Value> response) {
//                Boolean success = response.body().getSuccess();
//                if(success){
//                    Toast.makeText(DetailProfilActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(DetailProfilActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Value> call, Throwable t) {
//                Toast.makeText(DetailProfilActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
