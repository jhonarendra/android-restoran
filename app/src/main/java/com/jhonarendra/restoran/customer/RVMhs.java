package com.jhonarendra.restoran.customer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jhonarendra.restoran.customer.api.Result;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.BindView;

/**
 * Created by Jhonarendra on 11/2/2018.
 */

public class RVMhs extends RecyclerView.Adapter<RVMhs.ViewHolder> {
    private Context context;
    private List<Result> results;

    public RVMhs(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_mhs, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Result result = results.get(position);
        holder.textViewNama.setText(result.getNama_hidangan());
        holder.textViewHarga.setText(result.getHarga_hidangan());

        String address = "";
        address = Main2Activity.URL+"upload/"+result.getFoto_hidangan();
        Glide.with(context).load(address).into(holder.ivFotoHidangan);

        holder.cvMenuKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailHidangan.class);
                intent.putExtra("nama_hidangan", results.get(position).getNama_hidangan());
                intent.putExtra("deskripsi_hidangan", results.get(position).getDeskripsi_hidangan());
                intent.putExtra("kateogori_hidangan", results.get(position).getKategori_hidangan());
                intent.putExtra("harga_hidangan", results.get(position).getHarga_hidangan());
                intent.putExtra("foto_hidangan", results.get(position).getFoto_hidangan());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_nama_hidangan) TextView textViewNama;
        @BindView(R.id.tv_harga_hidangan) TextView textViewHarga;
        @BindView(R.id.iv_fotohidangan) ImageView ivFotoHidangan;
        @BindView(R.id.cv_menu_kategori) CardView cvMenuKategori;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
