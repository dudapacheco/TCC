package com.example.eduar.tcc_personal.Modelo;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Comparator;

public class Avaliacao implements Serializable, Comparable<Avaliacao>  {

    public static final Comparator<Avaliacao> POR_DATA = new Comparator<Avaliacao>() {
        public int compare(Avaliacao a1, Avaliacao a2) {
            return a1.compareTo(a2);
        }
    };

    private String mid;
    private String data;
    private String texto;
    private String excelente;
    private String bom;
    private String Ruim;

    public Avaliacao(){

    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getExcelente() {
        return excelente;
    }

    public void setExcelente(String excelente) {
        this.excelente = excelente;
    }

    public String getBom() {
        return bom;
    }

    public void setBom(String bom) {
        this.bom = bom;
    }

    public String getRuim() {
        return Ruim;
    }

    public void setRuim(String ruim) {
        Ruim = ruim;
    }

    @Override
    public String toString() {
        return data;
    }

    @Override
    public int compareTo(@NonNull Avaliacao o) {
        int compare = data.compareTo(o.data);
        return compare * (-1);
    }
}

