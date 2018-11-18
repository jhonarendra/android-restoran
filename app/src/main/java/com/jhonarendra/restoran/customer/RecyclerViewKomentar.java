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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jhonarendra.restoran.customer.api.Komentar;
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

public class RecyclerViewKomentar extends RecyclerView.Adapter<RecyclerViewKomentar.ViewHolder> {
    private Context context;
    private List<Komentar> komentarList;

    public RecyclerViewKomentar(Context context, List<Komentar> komentarList) {
        this.context = context;
        this.komentarList = komentarList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_list_komentar, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Komentar result = komentarList.get(position);
        holder.tvIsiKomentar.setText(result.getIsi_komentar());
    }

    @Override
    public int getItemCount() {
        return komentarList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_isi_komentar) TextView tvIsiKomentar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
