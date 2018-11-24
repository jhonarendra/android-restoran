package com.jhonarendra.restoran.customer.model;

import com.jhonarendra.restoran.customer.R;

import java.sql.Blob;

/**
 * Created by Jhonarendra on 11/2/2018.
 */

public class Hidangan {
    public static final String TABLE_NAME = "hidangan";

    public static final String COLUMN_ID = "id_hidangan";
    public static final String COLUMN_NAMA = "nama_hidangan";
    public static final String COLUMN_DESKRIPSI = "deskripsi_hidangan";
    public static final String COLUMN_KATEGORI = "kategori_hidangan";
    public static final String COLUMN_FOTO = "foto_hidangan";
    public static final String COLUMN_HARGA = "harga_hidangan";

    public static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAMA + " TEXT, "
            + COLUMN_DESKRIPSI + " TEXT, "
            + COLUMN_KATEGORI + " TEXT, "
            + COLUMN_FOTO + " TEXT, "
            + COLUMN_HARGA + " TEXT"
        + ")";

    String id_hidangan;
    String nama_hidangan;
    String deskripsi_hidangan;
    String kategori_hidangan;
    String foto_hidangan;
    String harga_hidangan;


    public String getId_hidangan() {
        return id_hidangan;
    }

    public void setId_hidangan(String id_hidangan) {
        this.id_hidangan = id_hidangan;
    }

    public String getNama_hidangan() {
        return nama_hidangan;
    }

    public void setNama_hidangan(String nama_hidangan) {
        this.nama_hidangan = nama_hidangan;
    }

    public String getHarga_hidangan() {
        return harga_hidangan;
    }

    public void setHarga_hidangan(String harga_hidangan) {
        this.harga_hidangan = harga_hidangan;
    }

    public String getFoto_hidangan() {
        return foto_hidangan;
    }

    public void setFoto_hidangan(String foto_hidangan) {
        this.foto_hidangan = foto_hidangan;
    }

    public String getKategori_hidangan() {
        return kategori_hidangan;
    }

    public void setKategori_hidangan(String kategori_hidangan) {
        this.kategori_hidangan = kategori_hidangan;
    }

    public String getDeskripsi_hidangan() {
        return deskripsi_hidangan;
    }

    public void setDeskripsi_hidangan(String deskripsi_hidangan) {
        this.deskripsi_hidangan = deskripsi_hidangan;
    }
}
