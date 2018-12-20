package com.jhonarendra.restoran.customer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jhonarendra.restoran.customer.R;
import com.jhonarendra.restoran.customer.activity.MainActivity;
import com.jhonarendra.restoran.customer.api.RegisterAPI;
import com.jhonarendra.restoran.customer.api.Value;
import com.jhonarendra.restoran.customer.model.Komentar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jhonarendra on 11/9/2018.
 */

public class RecyclerViewKomentar extends RecyclerView.Adapter<RecyclerViewKomentar.ViewHolder> {
    private Context context;
    private List<Komentar> komentarList;

    public RecyclerViewKomentar(Context context, List<Komentar> komentarList) {
        this.context = context;
        this.komentarList = komentarList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_komentar, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Komentar result = komentarList.get(position);
        holder.tvIsiKomentar.setText(result.getIsi_komentar());
        holder.tvTanggalKomentar.setText(result.getCreated_at());
        holder.tvHapusKomentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(v.getContext(), "Menghapus id "+result.getId_komentar(), Toast.LENGTH_SHORT).show();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(MainActivity.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RegisterAPI api = retrofit.create(RegisterAPI.class);

                Call<Value> call = api.deteleKomentarPelanggan(result.getId_komentar());
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        Boolean success = response.body().getSuccess();
                        if(success){
                            Toast.makeText(v.getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(v.getContext(), "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        Toast.makeText(v.getContext(), "Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return komentarList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_isi_komentar) TextView tvIsiKomentar;
        @BindView(R.id.tv_tanggal_komentar) TextView tvTanggalKomentar;
        @BindView(R.id.tv_hapus_komentar) TextView tvHapusKomentar;
        @BindView(R.id.tv_edit_komentar) TextView tvEditKomentar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
