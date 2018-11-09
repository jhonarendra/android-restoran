package com.jhonarendra.restoran.customer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jhonarendra on 11/2/2018.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    protected EditText etNama, etEmail, etUsernameR, etPasswordR;
    protected TextView tvRegister, tvLogin;
    SharedPreferences sharedPreferences;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        etNama = findViewById(R.id.et_nama);
        etEmail = findViewById(R.id.et_email);
        etUsernameR = findViewById(R.id.et_username_r);
        etPasswordR = findViewById(R.id.et_password_r);
        tvLogin = findViewById(R.id.tv_login_r);
        tvRegister = findViewById(R.id.tv_register_r);

        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_register_r:
                String inputNama = etNama.getText().toString();
                String inputEmail = etEmail.getText().toString();
                String inputUsername = etUsernameR.getText().toString();
                String inputPassword = etPasswordR.getText().toString();

                Toast.makeText(RegisterActivity.this, "Akun "+inputNama+" berhasil login. Email anda "+inputEmail+". Username anda"+inputUsername+". Password anda"+inputPassword, Toast.LENGTH_SHORT).show();

                sharedPreferences.edit()
                        .putString("login","true")
                        .putString("nama",inputNama)
                        .apply();
                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_login_r:
                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                break;
        }
    }
}
