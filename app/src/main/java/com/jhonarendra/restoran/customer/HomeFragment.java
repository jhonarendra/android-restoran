package com.jhonarendra.restoran.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

/**
 * Created by Jhonarendra on 11/7/2018.
 */

public class HomeFragment extends Fragment {
    protected LinearLayout llBurger, llSalad, llMinuman, llDessert, llBreakfast;

    private List<Result> hidanganList = new ArrayList<>();
    private List<Result> results = new ArrayList<>();
    private List<Result> resultsBurger = new ArrayList<>();
    private List<Result> resultsSalad = new ArrayList<>();
    private List<Result> resultsMinuman = new ArrayList<>();
    private List<Result> resultsDessert = new ArrayList<>();
    private List<Result> resultsBreakfast = new ArrayList<>();
    private RecyclerViewMenu menuAdapter, menuBurgerAdapter, menuSaladAdapter, menuMinumanAdapter, menuDessertAdapter, menuBreakfastAdapter;

    RecyclerView recyclerView, rvBurger, rvSalad, rvMinuman, rvDessert, rvBreakfast;

    CardView cvGetBurger, cvGetSalad, cvGetMinuman, cvGetDessert, cvGetBreakfast;

    private DatabaseHelper db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

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
                Intent burger = new Intent(getActivity(),MenuBurgerActivity.class);
                burger.putExtra("kategori_hidangan", "Burger");
                startActivity(burger);
            }
        });
        llSalad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent salad = new Intent(getActivity(),MenuBurgerActivity.class);
                salad.putExtra("kategori_hidangan", "Salad");
                startActivity(salad);
            }
        });
        llMinuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent minuman = new Intent(getActivity(),MenuBurgerActivity.class);
                minuman.putExtra("kategori_hidangan", "Minuman");
                startActivity(minuman);
            }
        });
        llDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dessert = new Intent(getActivity(),MenuBurgerActivity.class);
                dessert.putExtra("kategori_hidangan", "Dessert");
                startActivity(dessert);
            }
        });
        llBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent breakfast = new Intent(getActivity(),MenuBurgerActivity.class);
                breakfast.putExtra("kategori_hidangan", "Breakfast");
                startActivity(breakfast);
            }
        });

        cvGetBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent burger = new Intent(getActivity(),MenuBurgerActivity.class);
                burger.putExtra("kategori_hidangan", "Burger");
                startActivity(burger);
            }
        });
        cvGetSalad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent salad = new Intent(getActivity(),MenuBurgerActivity.class);
                salad.putExtra("kategori_hidangan", "Salad");
                startActivity(salad);
            }
        });
        cvGetMinuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent minuman = new Intent(getActivity(),MenuBurgerActivity.class);
                minuman.putExtra("kategori_hidangan", "Minuman");
                startActivity(minuman);
            }
        });
        cvGetDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dessert = new Intent(getActivity(),MenuBurgerActivity.class);
                dessert.putExtra("kategori_hidangan", "Dessert");
                startActivity(dessert);
            }
        });
        cvGetBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent breakfast = new Intent(getActivity(),MenuBurgerActivity.class);
                breakfast.putExtra("kategori_hidangan", "Breakfast");
                startActivity(breakfast);
            }
        });

        recyclerView = view.findViewById(R.id.rv_all_menu);
        rvBurger = view.findViewById(R.id.rv_burger_menu);
        rvBreakfast = view.findViewById(R.id.rv_breakfast_menu);
        rvSalad = view.findViewById(R.id.rv_salad_menu);
        rvDessert = view.findViewById(R.id.rv_dessert_menu);
        rvMinuman = view.findViewById(R.id.rv_minuman_menu);

        menuAdapter = new RecyclerViewMenu(getActivity().getApplicationContext(), results);
        menuBurgerAdapter = new RecyclerViewMenu(getActivity().getApplicationContext(), resultsBurger);
        menuSaladAdapter = new RecyclerViewMenu(getActivity().getApplicationContext(), resultsSalad);
        menuMinumanAdapter = new RecyclerViewMenu(getActivity().getApplicationContext(), resultsMinuman);
        menuDessertAdapter = new RecyclerViewMenu(getActivity().getApplicationContext(), resultsDessert);
        menuBreakfastAdapter = new RecyclerViewMenu(getActivity().getApplicationContext(), resultsBreakfast);

        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager burgerLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager saladLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager minumanLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager dessertLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager breakfastLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(menuAdapter);


        rvBurger.setLayoutManager(burgerLayoutManager);
        rvBurger.setItemAnimator(new DefaultItemAnimator());
        rvBurger.setAdapter(menuBurgerAdapter);


        rvBreakfast.setLayoutManager(breakfastLayoutManager);
        rvBreakfast.setItemAnimator(new DefaultItemAnimator());
        rvBreakfast.setAdapter(menuBreakfastAdapter);


        rvSalad.setLayoutManager(saladLayoutManager);
        rvSalad.setItemAnimator(new DefaultItemAnimator());
        rvSalad.setAdapter(menuSaladAdapter);


        rvDessert.setLayoutManager(dessertLayoutManager);
        rvDessert.setItemAnimator(new DefaultItemAnimator());
        rvDessert.setAdapter(menuDessertAdapter);


        rvMinuman.setLayoutManager(minumanLayoutManager);
        rvMinuman.setItemAnimator(new DefaultItemAnimator());
        rvMinuman.setAdapter(menuMinumanAdapter);

        db = new DatabaseHelper(getActivity());

//        if(db.getHidanganCount()==0){
            loadSemuaMenu();
//        }
        loadDataMenu();
        loadDataBurger();
        loadDataSalad();
        loadDataMinuman();
        loadDataDessert();
        loadDataBreakfast();
        return view;

    }

    private void loadSemuaMenu() {
        db = new DatabaseHelper(getActivity());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Main2Activity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<Value> call = api.hidangan();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                hidanganList = response.body().getResult();
                for (int i=0;i<hidanganList.size();i++){
                    db.insertNote(
                            hidanganList.get(i).getNama_hidangan(),
                            hidanganList.get(i).getDeskripsi_hidangan(),
                            hidanganList.get(i).getKategori_hidangan(),
                            hidanganList.get(i).getHarga_hidangan()
                    );
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }

    private void loadDataMenu() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Main2Activity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<Value> call = api.hidanganLimit();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                results = response.body().getResult();
                menuAdapter = new RecyclerViewMenu(getActivity(), results);
                recyclerView.setAdapter(menuAdapter);
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                results = db.getAllHidangan();
                menuAdapter = new RecyclerViewMenu(getActivity(), results);
                recyclerView.setAdapter(menuAdapter);
            }
        });
    }

    private void loadDataBurger() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Main2Activity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<Value> call = api.burgerLimit();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                resultsBurger = response.body().getResult();
                menuBurgerAdapter = new RecyclerViewMenu(getActivity(), resultsBurger);
                rvBurger.setAdapter(menuBurgerAdapter);
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                String kategori = "Burger";
                resultsBurger = db.getHidanganperKategori(kategori);
                menuBurgerAdapter = new RecyclerViewMenu(getActivity(), resultsBurger);
                rvBurger.setAdapter(menuBurgerAdapter);
            }
        });
    }

    private void loadDataBreakfast() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Main2Activity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<Value> call = api.breakfastLimit();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                resultsBreakfast = response.body().getResult();
                menuBreakfastAdapter = new RecyclerViewMenu(getActivity(), resultsBreakfast);
                rvBreakfast.setAdapter(menuBreakfastAdapter);
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                String kategori = "Breakfast";
                resultsBreakfast = db.getHidanganperKategori(kategori);
                menuBreakfastAdapter = new RecyclerViewMenu(getActivity(), resultsBreakfast);
                rvBreakfast.setAdapter(menuBreakfastAdapter);
            }
        });
    }

    private void loadDataDessert() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Main2Activity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<Value> call = api.dessertLimit();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                resultsDessert = response.body().getResult();
                menuDessertAdapter = new RecyclerViewMenu(getActivity(), resultsDessert);
                rvDessert.setAdapter(menuDessertAdapter);
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                String kategori = "Dessert";
                resultsDessert = db.getHidanganperKategori(kategori);
                menuDessertAdapter = new RecyclerViewMenu(getActivity(), resultsDessert);
                rvDessert.setAdapter(menuDessertAdapter);
//                resultsDessert = db.getHidanganperKategori(kategori);
//                menuBurgerAdapter = new RecyclerViewMenu(getActivity(), resultsDessert);
//                rvDessert.setAdapter(menuDessertAdapter);
            }
        });
    }

    private void loadDataMinuman() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Main2Activity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<Value> call = api.minumanLimit();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                resultsMinuman = response.body().getResult();
                menuMinumanAdapter = new RecyclerViewMenu(getActivity(), resultsMinuman);
                rvMinuman.setAdapter(menuMinumanAdapter);
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                String kategori = "Minuman";
                resultsMinuman = db.getHidanganperKategori(kategori);
                menuMinumanAdapter = new RecyclerViewMenu(getActivity(), resultsMinuman);
                rvMinuman.setAdapter(menuMinumanAdapter);
            }
        });
    }

    private void loadDataSalad() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Main2Activity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<Value> call = api.saladLimit();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                resultsSalad = response.body().getResult();
                menuSaladAdapter = new RecyclerViewMenu(getActivity(), resultsSalad);
                rvSalad.setAdapter(menuSaladAdapter);
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                String kategori = "Salad";
                resultsSalad = db.getHidanganperKategori(kategori);
                menuSaladAdapter = new RecyclerViewMenu(getActivity(), resultsSalad);
                rvSalad.setAdapter(menuSaladAdapter);
            }
        });
    }

}
