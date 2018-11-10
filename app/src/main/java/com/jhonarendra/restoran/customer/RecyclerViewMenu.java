package com.jhonarendra.restoran.customer;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jhonarendra.restoran.customer.api.Result;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = results.get(position);
        holder.textViewNama.setText(result.getNama_hidangan());
        holder.textViewHarga.setText(result.getHarga_hidangan());

        int resId = context.getResources().getIdentifier(result.getFoto_hidangan(), "drawable", context.getPackageName());
        holder.ivFotoHidangan.setImageResource(resId);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_nama_hidangan) TextView textViewNama;
        @BindView(R.id.tv_harga_hidangan) TextView textViewHarga;
        @BindView(R.id.iv_foto_hidangan) ImageView ivFotoHidangan;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
