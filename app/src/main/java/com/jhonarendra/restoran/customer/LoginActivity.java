package com.jhonarendra.restoran.customer;

/**
 * Created by Jhonarendra on 10/31/2018.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    protected Button btnLogin;
    protected TextView tvRegister;
    protected EditText etUsername,etPassword;

    private final String USERNAME="jona";
    private final String PASSWORD="jona";
//    public static String LOGIN_STATUS="false";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
//        String login=sharedPreferences.getString("login","");

        setContentView(R.layout.activity_login);

        btnLogin=findViewById(R.id.btn_login);
        tvRegister=findViewById(R.id.tv_register);

        etUsername=findViewById(R.id.et_username);
        etPassword=findViewById(R.id.et_password);

        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                String inputUsername = etUsername.getText().toString();
                String inputPassword = etPassword.getText().toString();
                if (inputUsername.equals(USERNAME)&&inputPassword.equals(PASSWORD)){
                    Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
//                    LOGIN_STATUS="true";
                    sharedPreferences.edit()
                            .putString("login","true")
                            .putString("nama",inputUsername)
//                            .putString("username",inputUsername)
//                            .putInt("umur",21)
                            .apply();

                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_register:
                break;
        }
    }
}

