package com.jhonarendra.restoran.customer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.ViewFlipper;

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

    protected TextView txtUser, txtLogin, txtLogout, txtTes;
    protected CardView btnBurger;
    protected ViewFlipper viewFlipper;

    SharedPreferences sharedPreferences;
    PreferencesHelper preferencesHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFlipper = findViewById(R.id.viewflipper);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.startFlipping();





        preferencesHelper = new PreferencesHelper(getApplicationContext());

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        txtUser = findViewById(R.id.txt_user);
        txtLogin = findViewById(R.id.txt_login);
        txtLogout = findViewById(R.id.txt_logout);
        txtTes = findViewById(R.id.txt_tes);

        btnBurger = findViewById(R.id.btn_burger);

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
        txtTes.setOnClickListener(this);
        btnBurger.setOnClickListener(this);
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
                break;
            case R.id.btn_burger:
                Intent i=new Intent(getApplicationContext(),MenuBurgerActivity.class);
                startActivity(i);
                break;
            case R.id.txt_tes:
                Intent a = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(a);
                break;
        }
    }
}
