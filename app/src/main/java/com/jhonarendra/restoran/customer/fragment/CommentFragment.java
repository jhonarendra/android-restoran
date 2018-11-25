package com.jhonarendra.restoran.customer.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jhonarendra.restoran.customer.activity.MainActivity;
import com.jhonarendra.restoran.customer.storage.DatabaseHelper;
import com.jhonarendra.restoran.customer.storage.PreferencesHelper;
import com.jhonarendra.restoran.customer.R;
import com.jhonarendra.restoran.customer.adapter.RecyclerViewKomentar;
import com.jhonarendra.restoran.customer.model.Komentar;
import com.jhonarendra.restoran.customer.api.RegisterAPI;
import com.jhonarendra.restoran.customer.api.Value;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jhonarendra on 11/7/2018.
 */

public class CommentFragment extends Fragment {
    TextView tvKirim;
    EditText etIsiKomentar;
    LinearLayout llCommentNotLogin, llCommentLoggedIn;
    RecyclerView rvKomentar;
    SharedPreferences sharedPreferences;
    PreferencesHelper preferencesHelper;
    DatabaseHelper db;

    private List<Komentar> komentarList = new ArrayList<>();
    private List<Komentar> allKomentarList = new ArrayList<>();
    RecyclerViewKomentar rvKomentarAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);

        preferencesHelper = new PreferencesHelper(getActivity());
        sharedPreferences = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        db = new DatabaseHelper(getActivity());

        llCommentLoggedIn = view.findViewById(R.id.ll_comment_logged_in);
        llCommentNotLogin = view.findViewById(R.id.ll_comment_not_login);

        rvKomentar = view.findViewById(R.id.rv_komentar_pelanggan);
        rvKomentarAdapter = new RecyclerViewKomentar(getActivity().getApplicationContext(), komentarList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvKomentar.setLayoutManager(layoutManager);
        rvKomentar.setItemAnimator(new DefaultItemAnimator());
        rvKomentar.setAdapter(rvKomentarAdapter);

        String login = sharedPreferences.getString("login", "");

        if (login.equals("true")){
            final String idPelanggan = sharedPreferences.getString("id", "");

            llCommentLoggedIn.setVisibility(View.VISIBLE);
            llCommentNotLogin.setVisibility(View.GONE);
            tvKirim = view.findViewById(R.id.tv_kirim_komentar);
            etIsiKomentar = view.findViewById(R.id.et_isi_komentar);

            tvKirim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String isiKomentar = etIsiKomentar.getText().toString();
                    kirimKomentar(isiKomentar, idPelanggan);
                }
            });
            if (db.getKomentarCount()==0){
                loadKomentarKeSQLite(idPelanggan);
            }
            loadDataKomentar(idPelanggan);
        } else {
            llCommentNotLogin.setVisibility(View.VISIBLE);
            llCommentLoggedIn.setVisibility(View.GONE);
        }

        return view;


    }

    private void loadKomentarKeSQLite(String idPelanggan) {
        String url = MainActivity.URL+"komentar/"+idPelanggan;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<Value> call = api.komentarPelanggan(idPelanggan);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                allKomentarList = response.body().getKomentar();
                for (int i=0;i<allKomentarList.size();i++){
                    db.insertKomentar(
                            allKomentarList.get(i).getIsi_komentar()
                    );
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Toast.makeText(getActivity(), "Tidak ada jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void kirimKomentar(String isiKomentar, String idPelanggan) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.kirimKomentar(idPelanggan, isiKomentar);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                Boolean success = response.body().getSuccess();
                if(success){
                    Toast.makeText(getActivity(), "Berhasil mengirim data. Silahkan refresh", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Gagal mengirim data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }

    private void loadDataKomentar(String idPelanggan) {
        String url = MainActivity.URL+"komentar/"+idPelanggan;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<Value> call = api.komentarPelanggan(idPelanggan);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                komentarList = response.body().getKomentar();
                rvKomentarAdapter = new RecyclerViewKomentar(getActivity(), komentarList);
                rvKomentar.setAdapter(rvKomentarAdapter);
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                komentarList = db.getAllKomentar();
                rvKomentarAdapter = new RecyclerViewKomentar(getActivity(), komentarList);
                rvKomentar.setAdapter(rvKomentarAdapter);
            }
        });
    }
}
