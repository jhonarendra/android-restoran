package com.jhonarendra.restoran.customer;

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

/**
 * Created by Jhonarendra on 11/7/2018.
 */

public class UserFragment extends Fragment{
    protected TextView tvLogin, tvUser, tvLogout;
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
        llNotLogin = view.findViewById(R.id.ll_not_login);
        llLoggedIn = view.findViewById(R.id.ll_logged_in);

        if (login.equals("true")){
            String nama = sharedPreferences.getString("nama", "");
            tvUser.setText(nama);
            llNotLogin.setVisibility(View.GONE);
        } else {
            llLoggedIn.setVisibility(View.GONE);
        }

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit()
                        .clear()
                        .apply();
                Intent intent = new Intent(getActivity(), Main2Activity.class);
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
