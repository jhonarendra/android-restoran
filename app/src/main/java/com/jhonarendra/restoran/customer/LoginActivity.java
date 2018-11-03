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
    protected TextView tvRegister, tvLogin;
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
                String inputUsername = etUsername.getText().toString();
                String inputPassword = etPassword.getText().toString();
                if (inputUsername.equals(USERNAME)&&inputPassword.equals(PASSWORD)){
                    Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    sharedPreferences.edit()
                            .putString("login","true")
                            .putString("nama",inputUsername)
                            .apply();

                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_register:
                Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}

