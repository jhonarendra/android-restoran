package com.jhonarendra.restoran.customer.model;

/**
 * Created by Jhonarendra on 11/15/2018.
 */

public class Komentar {
    String id_pelanggan;
    String isi_komentar;

    public Komentar(String id_pelanggan, String isi_komentar){
        this.id_pelanggan = id_pelanggan;
        this.isi_komentar = isi_komentar;
    }

    public String getId_pelanggan() {
        return id_pelanggan;
    }

    public String getIsi_komentar() {
        return isi_komentar;
    }
}
