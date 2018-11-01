package com.jhonarendra.restoran.customer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    protected Button btnLogin;
    protected TextView txtUser, txtLogin, txtLogout;

    SharedPreferences sharedPreferences;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
