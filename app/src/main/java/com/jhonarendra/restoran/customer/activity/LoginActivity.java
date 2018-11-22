package com.jhonarendra.restoran.customer.activity;

/**
 * Created by Jhonarendra on 10/31/2018.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jhonarendra.restoran.customer.R;
import com.jhonarendra.restoran.customer.model.Pelanggan;
import com.jhonarendra.restoran.customer.api.RegisterAPI;
import com.jhonarendra.restoran.customer.api.Value;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    protected TextView tvRegister, tvLogin;
    protected EditText etUsername,etPassword;

//    private final String USERNAME="jona";
//    private final String PASSWORD="jona";
//    public static String LOGIN_STATUS="false";

    SharedPreferences sharedPreferences;
    private List<Pelanggan> pelangganList = new ArrayList<>();
    Pelanggan pelanggan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
//        String login=sharedPreferences.getString("login","");

        setContentView(R.layout.activity_login);

        tvLogin=findViewById(R.id.tv_login);
        tvRegister=findViewById(R.id.tv_register);

        etUsername=findViewById(R.id.et_username);
        etPassword=findViewById(R.id.et_password);

        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_login:
                final String inputUsername = etUsername.getText().toString();
                String inputPassword = etPassword.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(MainActivity.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RegisterAPI api = retrofit.create(RegisterAPI.class);
                Call<Value> call = api.login(inputUsername, inputPassword);
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        Boolean success = response.body().getSuccess();
                        if (success){
                            Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            pelangganList = response.body().getPelanggan();

                            pelanggan = pelangganList.get(0);
                            sharedPreferences.edit()
                                    .putString("login","true")
                                    .putString("id",pelanggan.getId_pelanggan())
                                    .putString("nama",pelanggan.getNama_pelanggan())
                                    .putString("email",pelanggan.getEmail_pelanggan())
                                    .putString("username",pelanggan.getUsername_pelanggan())
                                    .putString("password",pelanggan.getPassword_pelanggan())
                                    .apply();

                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {

                    }
                });

                break;
            case R.id.tv_register:
                Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
                break;
        }
    }
}

