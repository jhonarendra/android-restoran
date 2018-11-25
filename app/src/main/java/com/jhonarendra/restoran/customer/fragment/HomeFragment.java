package com.jhonarendra.restoran.customer.fragment;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jhonarendra.restoran.customer.activity.RegisterActivity;
import com.jhonarendra.restoran.customer.activity.TesByte;
import com.jhonarendra.restoran.customer.adapter.RecyclerViewHidangan;
import com.jhonarendra.restoran.customer.activity.MenuPerKategoriActivity;
import com.jhonarendra.restoran.customer.model.Hidangan;
import com.jhonarendra.restoran.customer.storage.DatabaseHelper;
import com.jhonarendra.restoran.customer.activity.MainActivity;
import com.jhonarendra.restoran.customer.R;
import com.jhonarendra.restoran.customer.api.RegisterAPI;
import com.jhonarendra.restoran.customer.api.Value;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jhonarendra on 11/7/2018.
 */

public class HomeFragment extends Fragment {
    protected LinearLayout llBurger, llSalad, llMinuman, llDessert, llBreakfast;

    private List<Hidangan> allHidanganList = new ArrayList<>();
    private List<Hidangan> hidanganList = new ArrayList<>();
    private List<Hidangan> burgerList = new ArrayList<>();
    private List<Hidangan> saladList = new ArrayList<>();
    private List<Hidangan> minumanList = new ArrayList<>();
    private List<Hidangan> dessertList = new ArrayList<>();
    private List<Hidangan> breakfastList = new ArrayList<>();
    private RecyclerViewHidangan hidanganAdapter, burgerAdapter, saladAdapter, minumanAdapter, dessertAdapter, breakfastAdapter;

    RecyclerView rvHidangan, rvBurger, rvSalad, rvMinuman, rvDessert, rvBreakfast;

    CardView cvGetBurger, cvGetSalad, cvGetMinuman, cvGetDessert, cvGetBreakfast;

    private DatabaseHelper db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvHidangan = view.findViewById(R.id.rv_all_menu);
        rvBurger = view.findViewById(R.id.rv_burger_menu);
        rvBreakfast = view.findViewById(R.id.rv_breakfast_menu);
        rvSalad = view.findViewById(R.id.rv_salad_menu);
        rvDessert = view.findViewById(R.id.rv_dessert_menu);
        rvMinuman = view.findViewById(R.id.rv_minuman_menu);

        hidanganAdapter = new RecyclerViewHidangan(getActivity().getApplicationContext(), hidanganList);
        burgerAdapter = new RecyclerViewHidangan(getActivity().getApplicationContext(), burgerList);
        saladAdapter = new RecyclerViewHidangan(getActivity().getApplicationContext(), saladList);
        minumanAdapter = new RecyclerViewHidangan(getActivity().getApplicationContext(), minumanList);
        dessertAdapter = new RecyclerViewHidangan(getActivity().getApplicationContext(), dessertList);
        breakfastAdapter = new RecyclerViewHidangan(getActivity().getApplicationContext(), breakfastList);


        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager burgerLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager saladLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager minumanLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager dessertLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager breakfastLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        rvHidangan.setLayoutManager(mLayoutManager);
        rvHidangan.setItemAnimator(new DefaultItemAnimator());
        rvHidangan.setAdapter(hidanganAdapter);


        rvBurger.setLayoutManager(burgerLayoutManager);
        rvBurger.setItemAnimator(new DefaultItemAnimator());
        rvBurger.setAdapter(burgerAdapter);


        rvBreakfast.setLayoutManager(breakfastLayoutManager);
        rvBreakfast.setItemAnimator(new DefaultItemAnimator());
        rvBreakfast.setAdapter(breakfastAdapter);


        rvSalad.setLayoutManager(saladLayoutManager);
        rvSalad.setItemAnimator(new DefaultItemAnimator());
        rvSalad.setAdapter(saladAdapter);


        rvDessert.setLayoutManager(dessertLayoutManager);
        rvDessert.setItemAnimator(new DefaultItemAnimator());
        rvDessert.setAdapter(dessertAdapter);


        rvMinuman.setLayoutManager(minumanLayoutManager);
        rvMinuman.setItemAnimator(new DefaultItemAnimator());
        rvMinuman.setAdapter(minumanAdapter);



        db = new DatabaseHelper(getActivity());

        int jmlHidangan = db.getHidanganCount();
        Toast.makeText(getActivity(), "jumlah hidangan "+jmlHidangan, Toast.LENGTH_SHORT).show();

        if(jmlHidangan==0){ // Kalo di sqlite gak ada data, berarti dia baru nginstal, jadi pindahin data database ke sqlite
            loadSemuaHidanganKeSQLite();
        }

        loadDataMenu();
        String kategori = "Burger";
        loadHidanganPerKategori(kategori);
        kategori = "Salad";
        loadHidanganPerKategori(kategori);
        kategori = "Minuman";
        loadHidanganPerKategori(kategori);
        kategori = "Dessert";
        loadHidanganPerKategori(kategori);
        kategori = "Breakfast";
        loadHidanganPerKategori(kategori);


        /**
         * Ini hanya kodingan untuk melihat menu per kategori
         */

        llBurger = view.findViewById(R.id.ll_burger);
        llSalad = view.findViewById(R.id.ll_salad);
        llMinuman = view.findViewById(R.id.ll_minuman);
        llDessert = view.findViewById(R.id.ll_dessert);
        llBreakfast = view.findViewById(R.id.ll_breakfast);

        cvGetBurger = view.findViewById(R.id.cv_get_all_burger);
        cvGetSalad = view.findViewById(R.id.cv_get_all_salad);
        cvGetMinuman = view.findViewById(R.id.cv_get_all_minuman);
        cvGetDessert = view.findViewById(R.id.cv_get_all_dessert);
        cvGetBreakfast = view.findViewById(R.id.cv_get_all_breakfast);

        llBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent burger = new Intent(getActivity(),MenuPerKategoriActivity.class);
                burger.putExtra("kategori_hidangan", "Burger");
                startActivity(burger);
            }
        });
        llSalad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent salad = new Intent(getActivity(),MenuPerKategoriActivity.class);
                salad.putExtra("kategori_hidangan", "Salad");
                startActivity(salad);
            }
        });
        llMinuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent minuman = new Intent(getActivity(),MenuPerKategoriActivity.class);
                minuman.putExtra("kategori_hidangan", "Minuman");
                startActivity(minuman);
            }
        });
        llDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dessert = new Intent(getActivity(),MenuPerKategoriActivity.class);
                dessert.putExtra("kategori_hidangan", "Dessert");
                startActivity(dessert);
            }
        });
        llBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent breakfast = new Intent(getActivity(),MenuPerKategoriActivity.class);
                breakfast.putExtra("kategori_hidangan", "Breakfast");
                startActivity(breakfast);
            }
        });

        cvGetBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent burger = new Intent(getActivity(),MenuPerKategoriActivity.class);
                burger.putExtra("kategori_hidangan", "Burger");
                startActivity(burger);
            }
        });
        cvGetSalad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent salad = new Intent(getActivity(),MenuPerKategoriActivity.class);
                salad.putExtra("kategori_hidangan", "Salad");
                startActivity(salad);
            }
        });
        cvGetMinuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent minuman = new Intent(getActivity(),MenuPerKategoriActivity.class);
                minuman.putExtra("kategori_hidangan", "Minuman");
                startActivity(minuman);
            }
        });
        cvGetDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dessert = new Intent(getActivity(),MenuPerKategoriActivity.class);
                dessert.putExtra("kategori_hidangan", "Dessert");
                startActivity(dessert);
            }
        });
        cvGetBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent breakfast = new Intent(getActivity(),MenuPerKategoriActivity.class);
                breakfast.putExtra("kategori_hidangan", "Breakfast");
                startActivity(breakfast);
            }
        });

        return view;
    }

    private void loadSemuaHidanganKeSQLite(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.hidangan();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                allHidanganList = response.body().getHidangan();
                for (int i=0;i<allHidanganList.size();i++){
                    db.insertHidangan(
                            allHidanganList.get(i).getNama_hidangan(),
                            allHidanganList.get(i).getDeskripsi_hidangan(),
                            allHidanganList.get(i).getKategori_hidangan(),
                            allHidanganList.get(i).getHarga_hidangan(),
                            allHidanganList.get(i).getFoto_hidangan()
                    );
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Toast.makeText(getActivity(), "Tidak ada jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDataMenu() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.hidanganLimit();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                hidanganList = response.body().getHidangan();
                hidanganAdapter = new RecyclerViewHidangan(getActivity(), hidanganList);
                rvHidangan.setAdapter(hidanganAdapter);
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                hidanganList = db.getHidanganLimit();
                hidanganAdapter = new RecyclerViewHidangan(getActivity(), hidanganList);
                rvHidangan.setAdapter(hidanganAdapter);
            }
        });
    }

    private void loadHidanganPerKategori(final String kategori) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        switch (kategori){
            case "Burger":
                Call<Value> callBurger = api.hidanganKategoriLimit(kategori);
                callBurger.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        burgerList = response.body().getHidangan();
                        burgerAdapter = new RecyclerViewHidangan(getActivity(), burgerList);
                        rvBurger.setAdapter(burgerAdapter);
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        burgerList = db.getHidanganperKategori(kategori);
                        burgerAdapter = new RecyclerViewHidangan(getActivity(), burgerList);
                        rvBurger.setAdapter(burgerAdapter);
                    }
                });
                break;
            case "Salad":
                Call<Value> callSalad = api.hidanganKategoriLimit(kategori);
                callSalad.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        saladList = response.body().getHidangan();
                        saladAdapter = new RecyclerViewHidangan(getActivity(), saladList);
                        rvSalad.setAdapter(saladAdapter);
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        saladList = db.getHidanganperKategori(kategori);
                        saladAdapter = new RecyclerViewHidangan(getActivity(), saladList);
                        rvSalad.setAdapter(saladAdapter);
                    }
                });
                break;
            case "Minuman":
                Call<Value> callMinuman = api.hidanganKategoriLimit(kategori);
                callMinuman.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        minumanList = response.body().getHidangan();
                        minumanAdapter = new RecyclerViewHidangan(getActivity(), minumanList);
                        rvMinuman.setAdapter(minumanAdapter);
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        minumanList = db.getHidanganperKategori(kategori);
                        minumanAdapter = new RecyclerViewHidangan(getActivity(), minumanList);
                        rvMinuman.setAdapter(minumanAdapter);
                    }
                });
                break;
            case "Dessert":
                Call<Value> callDessert = api.hidanganKategoriLimit(kategori);
                callDessert.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        dessertList = response.body().getHidangan();
                        dessertAdapter = new RecyclerViewHidangan(getActivity(), dessertList);
                        rvDessert.setAdapter(dessertAdapter);
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        dessertList = db.getHidanganperKategori(kategori);
                        dessertAdapter = new RecyclerViewHidangan(getActivity(), dessertList);
                        rvDessert.setAdapter(dessertAdapter);
                    }
                });
                break;
            case "Breakfast":
                Call<Value> callBreakfast = api.hidanganKategoriLimit(kategori);
                callBreakfast.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        breakfastList = response.body().getHidangan();
                        breakfastAdapter = new RecyclerViewHidangan(getActivity(), breakfastList);
                        rvBreakfast.setAdapter(breakfastAdapter);
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        breakfastList = db.getHidanganperKategori(kategori);
                        breakfastAdapter = new RecyclerViewHidangan(getActivity(), breakfastList);
                        rvBreakfast.setAdapter(breakfastAdapter);
                    }
                });
                break;
        }
    }
}
