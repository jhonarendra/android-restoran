package com.jhonarendra.restoran.customer.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jhonarendra.restoran.customer.activity.LoginActivity;
import com.jhonarendra.restoran.customer.activity.MainActivity;
import com.jhonarendra.restoran.customer.storage.PreferencesHelper;
import com.jhonarendra.restoran.customer.R;

/**
 * Created by Jhonarendra on 11/7/2018.
 */

public class UserFragment extends Fragment{
    protected TextView tvLogin, tvUser, tvLogout, tvEmail, tvUsername;
    protected LinearLayout llNotLogin, llLoggedIn;
    SharedPreferences sharedPreferences;
    PreferencesHelper preferencesHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        preferencesHelper = new PreferencesHelper(getActivity());
        sharedPreferences = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        String login = sharedPreferences.getString("login", "");
        tvUser = view.findViewById(R.id.tv_nama_user);
        tvLogout = view.findViewById(R.id.tv_logout);
        tvEmail = view.findViewById(R.id.tv_email_user);
        tvUsername = view.findViewById(R.id.tv_username_user);
        llNotLogin = view.findViewById(R.id.ll_not_login);
        llLoggedIn = view.findViewById(R.id.ll_logged_in);

        if (login.equals("true")){
            String nama = sharedPreferences.getString("nama", "");
            String email = sharedPreferences.getString("email", "");
            String username = sharedPreferences.getString("username", "");
            tvUser.setText(nama);
            tvEmail.setText(email);
            tvUsername.setText(username);
            llNotLogin.setVisibility(View.GONE);
            llLoggedIn.setVisibility(View.VISIBLE);
        } else {
            llLoggedIn.setVisibility(View.GONE);
            llNotLogin.setVisibility(View.VISIBLE);
        }

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit()
                        .clear()
                        .apply();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        tvLogin = view.findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),LoginActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}
