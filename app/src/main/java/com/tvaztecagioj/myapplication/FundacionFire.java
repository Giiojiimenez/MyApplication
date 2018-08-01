package com.tvaztecagioj.myapplication;

import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class FundacionFire {

    private String etNombreFun,etCalle,etColonia,etMunicipio,etPostal,etPhoneFun,etCorreoEle,etCat,etNece01,etNece02,etNece03,etNece04,etNece05;

    public FundacionFire() {
    }

    public FundacionFire(String etNombreFun, String etCalle, String etColonia,
                         String etMunicipio, String etPostal, String etPhoneFun,
                         String etCorreoEle, String etCat, String etNece01, String etNece02,
                         String etNece03, String etNece04, String etNece05) {
        this.etNombreFun = etNombreFun;
        this.etCalle = etCalle;
        this.etColonia = etColonia;
        this.etMunicipio = etMunicipio;
        this.etPostal = etPostal;
        this.etPhoneFun = etPhoneFun;
        this.etCorreoEle = etCorreoEle;
        this.etCat = etCat;
        this.etNece01 = etNece01;
        this.etNece02 = etNece02;
        this.etNece03 = etNece03;
        this.etNece04 = etNece04;
        this.etNece05 = etNece05;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("etNombreFun", etNombreFun);
        result.put("etCalle", etCalle);
        result.put("etColonia", etColonia);
        result.put("etMunicipio", etMunicipio);
        result.put("etPostal", etPostal);
        result.put("etPhoneFun", etPhoneFun);
        result.put("etCorreoEle", etCorreoEle);
        result.put("etCat", etCat);
        result.put("etNece01", etNece01);
        result.put("etNece02", etNece02);
        result.put("etNece03", etNece03);
        result.put("etNece04", etNece04);
        result.put("etNece05", etNece05);
        return result;
    }

    public String getEtNombreFun() {
        return etNombreFun;
    }

    public void setEtNombreFun(String etNombreFun) {
        this.etNombreFun = etNombreFun;
    }

    public String getEtCalle() {
        return etCalle;
    }

    public void setEtCalle(String etCalle) {
        this.etCalle = etCalle;
    }

    public String getEtColonia() {
        return etColonia;
    }

    public void setEtColonia(String etColonia) {
        this.etColonia = etColonia;
    }

    public String getEtMunicipio() {
        return etMunicipio;
    }

    public void setEtMunicipio(String etMunicipio) {
        this.etMunicipio = etMunicipio;
    }

    public String getEtPostal() {
        return etPostal;
    }

    public void setEtPostal(String etPostal) {
        this.etPostal = etPostal;
    }

    public String getEtPhoneFun() {
        return etPhoneFun;
    }

    public void setEtPhoneFun(String etPhoneFun) {
        this.etPhoneFun = etPhoneFun;
    }

    public String getEtCorreoEle() {
        return etCorreoEle;
    }

    public void setEtCorreoEle(String etCorreoEle) {
        this.etCorreoEle = etCorreoEle;
    }

    public String getEtCat() {
        return etCat;
    }

    public void setEtCat(String etCat) {
        this.etCat = etCat;
    }

    public String getEtNece01() {
        return etNece01;
    }

    public void setEtNece01(String etNece01) {
        this.etNece01 = etNece01;
    }

    public String getEtNece02() {
        return etNece02;
    }

    public void setEtNece02(String etNece02) {
        this.etNece02 = etNece02;
    }

    public String getEtNece03() {
        return etNece03;
    }

    public void setEtNece03(String etNece03) {
        this.etNece03 = etNece03;
    }

    public String getEtNece04() {
        return etNece04;
    }

    public void setEtNece04(String etNece04) {
        this.etNece04 = etNece04;
    }

    public String getEtNece05() {
        return etNece05;
    }

    public void setEtNece05(String etNece05) {
        this.etNece05 = etNece05;
    }
}
