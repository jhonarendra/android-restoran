package com.jhonarendra.restoran.customer.model;

/**
 * Created by Jhonarendra on 11/15/2018.
 */

public class Komentar {
    public static final String TABLE_NAME = "komentar";

    public static final String COLUMN_ID = "id_komentar";
    public static final String COLUMN_KOMENTAR = "isi_komentar";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_KOMENTAR + " TEXT"
                    + ")";

    String id_pelanggan;
    String isi_komentar;

    public Komentar(String id_pelanggan, String isi_komentar){
        this.id_pelanggan = id_pelanggan;
        this.isi_komentar = isi_komentar;
    }
    public Komentar(){

    }

    public String getId_pelanggan() {
        return id_pelanggan;
    }

    public String getIsi_komentar() {
        return isi_komentar;
    }

    public void setId_pelanggan(String id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public void setIsi_komentar(String isi_komentar) {
        this.isi_komentar = isi_komentar;
    }
}
