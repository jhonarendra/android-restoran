package com.jhonarendra.restoran.customer;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jhonarendra.restoran.customer.api.Result;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jhonarendra on 11/9/2018.
 */

public class RecyclerViewMenu extends RecyclerView.Adapter<RecyclerViewMenu.ViewHolder> {
    private Context context;
    private List<Result> results;

    public RecyclerViewMenu(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_all_menu, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Result result = results.get(position);
        holder.textViewNama.setText(result.getNama_hidangan());
        holder.textViewHarga.setText(result.getHarga_hidangan());

//        holder.cvAllMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, DetailHidangan.class);
//                intent.putExtra("nama_hidangan", results.get(position).getNama_hidangan());
//                intent.putExtra("deskripsi_hidangan", results.get(position).getDeskripsi_hidangan());
//                intent.putExtra("kateogori_hidangan", results.get(position).getKategori_hidangan());
//                intent.putExtra("harga_hidangan", results.get(position).getHarga_hidangan());
//                intent.putExtra("foto_hidangan", results.get(position).getFoto_hidangan());
//            }
//        });


//        String address = "http://192.168.43.102:8000/upload/"+result.getFoto_hidangan();
//        String address = "http://192.168.43.102:8000/upload/burger_cheese_burger.png";


//        try {
//            URL url = new URL(address);
//            InputStream content = (InputStream)url.getContent();
//            Drawable d = Drawable.createFromStream(content, "src");
//            holder.ivFotoHidangan.setImageDrawable(d);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



//        int resId = context.getResources().getIdentifier(result.getFoto_hidangan(), "drawable", context.getPackageName());
//        holder.ivFotoHidangan.setImageResource(resId);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_nama_hidangan) TextView textViewNama;
        @BindView(R.id.tv_harga_hidangan) TextView textViewHarga;
        @BindView(R.id.iv_foto_hidangan) ImageView ivFotoHidangan;
        @BindView(R.id.cv_all_menu) CardView cvAllMenu;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}