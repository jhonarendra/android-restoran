package com.jhonarendra.restoran.customer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.TextView;

import com.jhonarendra.restoran.customer.api.RegisterAPI;
import com.jhonarendra.restoran.customer.api.Result;
import com.jhonarendra.restoran.customer.api.Value;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    protected Button btnLogin;
    protected TextView txtUser, txtLogin, txtLogout;

    SharedPreferences sharedPreferences;
    PreferencesHelper preferencesHelper;


    public static final String URL = "http://jonarendra.000webhostapp.com/";
    private List<Result> results = new ArrayList<>();
    private RVMhs viewAdapter;

    @BindView(R.id.rv_mhs) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        viewAdapter = new RVMhs(this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

        loadDataMahasiswa();



        preferencesHelper = new PreferencesHelper(getApplicationContext());

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

//        btnLogin = findViewById(R.id.btn_login);
        txtUser = findViewById(R.id.txt_user);
        txtLogin = findViewById(R.id.txt_login);
        txtLogout = findViewById(R.id.txt_logout);

        String login = sharedPreferences.getString("login", "");

        if (login.equals("true")){
            String nama = sharedPreferences.getString("nama", "");
            txtUser.setText(nama);
            txtLogin.setVisibility(View.INVISIBLE);
        } else {
            txtLogout.setVisibility(View.INVISIBLE);
        }

        txtLogin.setOnClickListener(this);
        txtLogout.setOnClickListener(this);
    }

    private void loadDataMahasiswa() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.view();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();

                    results = response.body().getResult();
                    viewAdapter = new RVMhs(MainActivity.this, results);
                    recyclerView.setAdapter(viewAdapter);

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.txt_logout:
                sharedPreferences.edit()
                        .clear()
                        .apply();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
        }
    }
}
