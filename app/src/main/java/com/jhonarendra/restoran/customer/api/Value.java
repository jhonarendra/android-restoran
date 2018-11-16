package com.jhonarendra.restoran.customer.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhonarendra on 11/2/2018.
 */

public class Value {
    Boolean success;
    List<Result> result;
    List<Komentar> komentar;
//    Pelanggan pelanggan;
    List<Pelanggan> pelanggan;
    String message;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
    public List<Result> getResult() {
        return result;
    }
    public List<Komentar> getKomentar(){
        return komentar;
    }

    public List<Pelanggan> getPelanggan() {
        return pelanggan;
    }
    //    public Pelanggan getPelanggan() {
//        return pelanggan;
//    }
}
