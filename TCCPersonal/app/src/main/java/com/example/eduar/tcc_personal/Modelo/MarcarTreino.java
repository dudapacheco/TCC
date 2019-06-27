package com.example.eduar.tcc_personal.Modelo;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Comparator;

public class MarcarTreino implements Serializable, Comparable<MarcarTreino>{

    public static final Comparator<MarcarTreino> POR_DATA = new Comparator<MarcarTreino>() {
        public int compare(MarcarTreino a1, MarcarTreino a2) {
            return a1.compareTo(a2);
        }
    };


    private String mid;
    private String datatreino;
    private String horariotreino;
    private String local;
    private Aluno aluno;

    public MarcarTreino(){

    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getDatatreino() {
        return datatreino;
    }

    public void setDatatreino(String datatreino) {
        this.datatreino = datatreino;
    }

    public String getHorariotreino() {
        return horariotreino;
    }

    public void setHorariotreino(String horariotreino) {
        this.horariotreino = horariotreino;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Data: " + datatreino + " " + "-" + " " + horariotreino + "\n" + local + " " + "-" + " " + aluno.getNomealuno();
    }

    @Override
    public int compareTo(@NonNull MarcarTreino o) {
        int compare = datatreino.compareTo(o.datatreino);
        return compare * (-1);
    }
}
