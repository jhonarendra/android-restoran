package com.jhonarendra.restoran.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jhonarendra.restoran.customer.R;
import com.jhonarendra.restoran.customer.adapter.RecyclerViewHidanganHorizontal;
import com.jhonarendra.restoran.customer.api.RegisterAPI;
import com.jhonarendra.restoran.customer.model.Hidangan;
import com.jhonarendra.restoran.customer.api.Value;
import com.jhonarendra.restoran.customer.storage.DatabaseHelper;

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

public class MenuPerKategoriActivity extends AppCompatActivity {

    private List<Hidangan> hidangans = new ArrayList<>();
    private RecyclerViewHidanganHorizontal viewAdapter;

    private DatabaseHelper db;

    @BindView(R.id.rv_mhs)
    RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidangan_per_kategori);


	// recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        // recyclerView.setNestedScrollingEnabled(false);
        // recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        ButterKnife.bind(this);
        viewAdapter = new RecyclerViewHidanganHorizontal(this, hidangans);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

//        Intent intent = getIntent();
//        tvDetNamaHidangan.setText(intent.getExtras().getString("nama_hidangan"));
        Intent intent = getIntent();
        String kategoriHidangan = intent.getExtras().getString("kategori_hidangan");

        db = new DatabaseHelper(this);




//	bangunDatarList.add(new BangunDatarItem(
              //  "Trapesium",
              //  "Trapesium adalah bangun datar yang memiliki sepasang sisi sejajar dan sisi lainnya menghubungkan sisi sejajar.",
             //   R.drawable.trapesium,
            //    R.drawable.white_trapesium,
             //   "s1 + s2 + a + b",
             //   "{(a + b) x t} / 2",
           //     R.drawable.rumus_trapesium
        // ));

//	adapter = new BangunDatarAdapter(this, bangunDatarList);
//        recyclerView.setAdapter(adapter);

        loadMenuKategori(kategoriHidangan);



    }

    private void loadMenuKategori(final String kategori) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RegisterAPI api = retrofit.create(RegisterAPI.class);

        Call<Value> call = null;
        switch (kategori){
            case "Burger":
                call = api.hidanganKategoriLimit(kategori);
                break;
            case "Salad":
                call = api.hidanganKategoriLimit(kategori);
                break;
            case "Minuman":
                call = api.hidanganKategoriLimit(kategori);
                break;
            case "Dessert":
                call = api.hidanganKategoriLimit(kategori);
                break;
            case "Breakfast":
                call = api.hidanganKategoriLimit(kategori);
        }

        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                hidangans = response.body().getHidangan();
                viewAdapter = new RecyclerViewHidanganHorizontal(MenuPerKategoriActivity.this, hidangans);
                recyclerView.setAdapter(viewAdapter);
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                hidangans = db.getHidanganperKategori(kategori);
                viewAdapter = new RecyclerViewHidanganHorizontal(MenuPerKategoriActivity.this, hidangans);
                recyclerView.setAdapter(viewAdapter);
            }
        });
    }
}
