package com.jhonarendra.restoran.customer.api;

import com.jhonarendra.restoran.customer.model.Hidangan;
import com.jhonarendra.restoran.customer.model.Komentar;
import com.jhonarendra.restoran.customer.model.Pelanggan;

import java.util.List;

/**
 * Created by Jhonarendra on 11/2/2018.
 */

public class Value {
    Boolean success;
    List<Hidangan> hidangan;
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
    public List<Hidangan> getHidangan() {
        return hidangan;
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
