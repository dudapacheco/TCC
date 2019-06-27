package com.example.eduar.tcc_personal.Modelo;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Comparator;

public class Evolucao implements Serializable, Comparable<Evolucao> {

    public static final Comparator<Evolucao> POR_DATA = new Comparator<Evolucao>() {
        public int compare(Evolucao a1, Evolucao a2) {
            return a1.compareTo(a2);
        }
    };

    private String mid;
    private String data;
    private Double peso;
    private Double cintura;
    private Double abdomen;
    private Double quadril;
    private Double bicepsd;
    private Double bicepse;
    private Double coxad;
    private Double coxae;
    private Double peito;
    private Double subescapular;

    public Evolucao(){

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

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getCintura() {
        return cintura;
    }

    public void setCintura(Double cintura) {
        this.cintura = cintura;
    }

    public Double getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(Double abdomen) {
        this.abdomen = abdomen;
    }

    public Double getQuadril() {
        return quadril;
    }

    public void setQuadril(Double quadril) {
        this.quadril = quadril;
    }

    public Double getBicepsd() {
        return bicepsd;
    }

    public void setBicepsd(Double bicepsd) {
        this.bicepsd = bicepsd;
    }

    public Double getBicepse() {
        return bicepse;
    }

    public void setBicepse(Double bicepse) {
        this.bicepse = bicepse;
    }

    public Double getCoxad() {
        return coxad;
    }

    public void setCoxad(Double coxad) {
        this.coxad = coxad;
    }

    public Double getCoxae() {
        return coxae;
    }

    public void setCoxae(Double coxae) {
        this.coxae = coxae;
    }

    public Double getPeito() {
        return peito;
    }

    public void setPeito(Double peito) {
        this.peito = peito;
    }

    public Double getSubescapular() {
        return subescapular;
    }

    public void setSubescapular(Double subescapular) {
        this.subescapular = subescapular;
    }

    @Override
    public String toString() {
        return data;
    }

    @Override
    public int compareTo(@NonNull Evolucao o) {
        int compare = data.compareTo(o.data);
        return compare * (-1);
    }
}
