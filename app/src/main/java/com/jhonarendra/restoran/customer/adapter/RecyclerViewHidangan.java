package com.jhonarendra.restoran.customer.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jhonarendra.restoran.customer.R;
import com.jhonarendra.restoran.customer.activity.DetailHidanganActivity;
import com.jhonarendra.restoran.customer.activity.MainActivity;
import com.jhonarendra.restoran.customer.model.Hidangan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jhonarendra on 11/9/2018.
 */

public class RecyclerViewHidangan extends RecyclerView.Adapter<RecyclerViewHidangan.ViewHolder> {
    private Context context;
    private List<Hidangan> hidangans;

    public RecyclerViewHidangan(Context context, List<Hidangan> hidangans) {
        this.context = context;
        this.hidangans = hidangans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_hidangan, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Hidangan hidangan = hidangans.get(position);
        holder.textViewNama.setText(hidangan.getNama_hidangan());
        holder.textViewHarga.setText(hidangan.getHarga_hidangan());
        String address = "";
        address = MainActivity.URL+"upload/"+ hidangan.getFoto_hidangan();
        Glide.with(context).load(address).into(holder.ivFotoHidangan);
        holder.llAllMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailHidanganActivity.class);
                intent.putExtra("nama_hidangan", hidangans.get(position).getNama_hidangan());
                intent.putExtra("deskripsi_hidangan", hidangans.get(position).getDeskripsi_hidangan());
                intent.putExtra("kateogori_hidangan", hidangans.get(position).getKategori_hidangan());
                intent.putExtra("harga_hidangan", hidangans.get(position).getHarga_hidangan());
                intent.putExtra("foto_hidangan", hidangans.get(position).getFoto_hidangan());

                context.startActivity(intent);
            }
        });

//        int resId = context.getResources().getIdentifier(hidangan.getFoto_hidangan(), "drawable", context.getPackageName());
//        holder.ivFotoHidangan.setImageResource(resId);
    }

    @Override
    public int getItemCount() {
        return hidangans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_nama_hidangan) TextView textViewNama;
        @BindView(R.id.tv_harga_hidangan) TextView textViewHarga;
        @BindView(R.id.iv_foto_hidangan) ImageView ivFotoHidangan;
        @BindView(R.id.ll_all_menu)
        LinearLayout llAllMenu;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
