package com.tvaztecagioj.myapplication;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class DonacionFire {
    private String nomFun,mail,producto;
    private int cant;

    public DonacionFire() {
    }

    public DonacionFire(String nomFun, String mail, String producto, int cant) {
        this.nomFun = nomFun;
        this.mail = mail;
        this.producto = producto;
        this.cant = cant;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nomFun", nomFun);
        result.put("mail", mail);
        result.put("producto", producto);
        result.put("cant", cant);
        return result;
    }
}
