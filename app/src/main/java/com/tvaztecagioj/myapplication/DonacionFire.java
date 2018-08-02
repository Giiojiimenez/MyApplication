package com.tvaztecagioj.myapplication;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class DonacionFire {
    private String nomFun,mail,producto,correo;
    private int cant;

    public DonacionFire() {
    }

    public DonacionFire(String nomFun, String mail, String producto, int cant,String correo) {
        this.nomFun = nomFun;
        this.mail = mail;
        this.producto = producto;
        this.cant = cant;
        this.correo=correo;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nomFun", nomFun);
        result.put("mail", mail);
        result.put("producto", producto);
        result.put("cant", cant);
        result.put("correo", correo);
        return result;
    }

    public String getNomFun() {
        return nomFun;
    }

    public void setNomFun(String nomFun) {
        this.nomFun = nomFun;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
}
