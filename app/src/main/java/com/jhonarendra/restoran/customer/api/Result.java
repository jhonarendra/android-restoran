package com.jhonarendra.restoran.customer.api;

import com.jhonarendra.restoran.customer.R;

/**
 * Created by Jhonarendra on 11/2/2018.
 */

public class Result {
    String id_hidangan;
    String nama_hidangan;
    String deskripsi_hidangan;
    String kategori_hidangan;
    String foto_hidangan;
    String harga_hidangan;



    public String getId_hidangan() {
        return id_hidangan;
    }

    public String getNama_hidangan() {
        return nama_hidangan;
    }

    public String getHarga_hidangan() {
        return harga_hidangan;
    }

    public String getFoto_hidangan() {
        return foto_hidangan;
    }

    public String getKategori_hidangan() {
        return kategori_hidangan;
    }

    public String getDeskripsi_hidangan() {
        return deskripsi_hidangan;
    }

}
