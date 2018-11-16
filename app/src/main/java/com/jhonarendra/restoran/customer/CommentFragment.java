package com.jhonarendra.restoran.customer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Jhonarendra on 11/7/2018.
 */

public class CommentFragment extends Fragment {
    TextView tvKirim;
    EditText etIsiKomentar;
    SharedPreferences sharedPreferences;
    PreferencesHelper preferencesHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);

        preferencesHelper = new PreferencesHelper(getActivity());
        sharedPreferences = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        String login = sharedPreferences.getString("login", "");

        if (login.equals("true")){
            String nama = sharedPreferences.getString("nama", "");
//            tvUser.setText(nama);
//            llNotLogin.setVisibility(View.GONE);
        } else {
//            llLoggedIn.setVisibility(View.GONE);
        }

        tvKirim = view.findViewById(R.id.tv_kirim_komentar);
        etIsiKomentar = view.findViewById(R.id.et_isi_komentar);

        tvKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isiKomentar = etIsiKomentar.getText().toString();
            }
        });

        return view;


    }
}
