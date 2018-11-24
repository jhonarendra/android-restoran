package com.jhonarendra.restoran.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jhonarendra.restoran.customer.R;

/**
 * Created by Jhonarendra on 11/13/2018.
 */

public class DetailHidanganActivity extends AppCompatActivity {
    TextView tvDetNamaHidangan, tvDetDeskripsiHidangan, tvDetHargaHidangan;
    ImageView ivDetFotoHidangan;
    ImageView ivBackHome;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hidangan);

        tvDetNamaHidangan = findViewById(R.id.tv_det_nama_hidangan);
        tvDetDeskripsiHidangan = findViewById(R.id.tv_det_deskripsi_hidangan);
        tvDetHargaHidangan = findViewById(R.id.tv_det_harga_hidangan);
        ivDetFotoHidangan = findViewById(R.id.iv_det_foto_hidangan);
        ivBackHome = findViewById(R.id.iv_back_home);
        ivBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        tvDetNamaHidangan.setText(intent.getExtras().getString("nama_hidangan"));
        tvDetDeskripsiHidangan.setText(intent.getExtras().getString("deskripsi_hidangan"));
        tvDetHargaHidangan.setText(intent.getExtras().getString("harga_hidangan"));

        String address = "";
        address = MainActivity.URL+"upload/"+intent.getExtras().getString("foto_hidangan");;
        Glide.with(getApplicationContext()).load(address).into(ivDetFotoHidangan);
    }
}
