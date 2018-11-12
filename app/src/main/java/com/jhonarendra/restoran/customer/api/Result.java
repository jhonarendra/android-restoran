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

//    public Result(String id_hidangan, String nama_hidangan, String deskripsi_hidangan, String kategori_hidangan, String foto_hidangan, String harga_hidangan){
//        this.id_hidangan = id_hidangan;
//        this.nama_hidangan = nama_hidangan;
//        this.deskripsi_hidangan = deskripsi_hidangan;
//        this.kategori_hidangan = kategori_hidangan;
//        this.foto_hidangan = foto_hidangan;
//        this.harga_hidangan = harga_hidangan;
//    }

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
