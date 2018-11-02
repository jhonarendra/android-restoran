package com.jhonarendra.restoran.customer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = results.get(position);
        holder.textViewNIMMhs.setText(result.getNIM());
        holder.textViewNamaMhs.setText(result.getNamaMhs());
        holder.textViewIDMhs.setText(result.getIDMhs());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textNIMMhs) TextView textViewNIMMhs;
        @BindView(R.id.textNamaMhs) TextView textViewNamaMhs;
        @BindView(R.id.textIDMhs) TextView textViewIDMhs;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
