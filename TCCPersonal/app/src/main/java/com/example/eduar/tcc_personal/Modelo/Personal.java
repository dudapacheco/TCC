package com.example.eduar.tcc_personal.Modelo;

import java.io.Serializable;

public class Personal implements Serializable {

    private String mId;
    private String nome;
    private String horariomanha;
    private String horariotardenoite;
    private String cidade;
    private String uf;
    private String email;
    private String senha;
    Aluno aluno;

    public Personal(){

    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHorariomanha() {
        return horariomanha;
    }

    public void setHorariomanha(String horariomanha) {
        this.horariomanha = horariomanha;
    }

    public String getHorariotardenoite() {
        return horariotardenoite;
    }

    public void setHorariotardenoite(String horariotardenoite) {
        this.horariotardenoite = horariotardenoite;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return nome;
    }
}
