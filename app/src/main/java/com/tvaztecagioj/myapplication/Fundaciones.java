package com.tvaztecagioj.myapplication;

public class Fundaciones {
    int idfundacion;
    String calle;
    int codigopostal;
    String colonia;
    String correoelec;
    String municipio;
    String nombreFun;
    int numero;
    int telefono;
    String categoria;

    public Fundaciones() {
    }

    public Fundaciones(int idfundacion, String calle, int codigopostal, String colonia, String correoelec, String municipio, String nombreFun, int numero, int telefono,String categoria) {
        this.idfundacion = idfundacion;
        this.calle = calle;
        this.codigopostal = codigopostal;
        this.colonia = colonia;
        this.correoelec = correoelec;
        this.municipio = municipio;
        this.nombreFun = nombreFun;
        this.numero = numero;
        this.telefono = telefono;
        this.categoria=categoria;

    }

    public int getIdfundacion() {
        return idfundacion;
    }

    public void setIdfundacion(int idfundacion) {
        this.idfundacion = idfundacion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(int codigopostal) {
        this.codigopostal = codigopostal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCorreoelec() {
        return correoelec;
    }

    public void setCorreoelec(String correoelec) {
        this.correoelec = correoelec;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNombreFun() {
        return nombreFun;
    }

    public void setNombreFun(String nombreFun) {
        this.nombreFun = nombreFun;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
