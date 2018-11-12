package com.jhonarendra.restoran.customer;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class MenuBurgerActivity extends AppCompatActivity {

    private List<Result> results = new ArrayList<>();
    private RVMhs viewAdapter;

    @BindView(R.id.rv_mhs)
    RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_burger);


	// recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        // recyclerView.setNestedScrollingEnabled(false);
        // recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        ButterKnife.bind(this);
        viewAdapter = new RVMhs(this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

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

        loadDataMahasiswa();
    }
    private void loadDataMahasiswa() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Main2Activity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.view();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
//                String value = response.body().getValue();

                results = response.body().getResult();
                viewAdapter = new RVMhs(MenuBurgerActivity.this, results);
                recyclerView.setAdapter(viewAdapter);

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }
}
