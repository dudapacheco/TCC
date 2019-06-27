package com.example.eduar.tcc_personal.Modelo;

import com.example.eduar.tcc_personal.ConexaoBanco.Conexao;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

public class Aluno implements Serializable{

    private static final long serialVersionUID = 1L;
    private String mid;
    private String nomealuno;
    private String telefone;
    private String emailaluno;
    private String objetivo;
    private String datadeingresso;
    Evolucao evolucao;
    Avaliacao avaliacao;
    Dicas dicas;

    public Aluno(){

    }
    public boolean salvar(){

        /*
        + Comentarios
            + id_postagem
                + id_comentario
                    comentario
        * */
        DatabaseReference alunoRef = Conexao.getFirebase()
                .child("Aluno")
                .child( getMid() );

        String chaveAluno = alunoRef.push().getKey();
        setNomealuno( chaveAluno );
        alunoRef.child( getNomealuno() ).setValue( this );

        return true;
    }


    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getNomealuno() {
        return nomealuno;
    }

    public void setNomealuno(String nomealuno) {
        this.nomealuno = nomealuno;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmailaluno() {
        return emailaluno;
    }

    public void setEmailaluno(String emailaluno) {
        this.emailaluno = emailaluno;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDatadeingresso() {
        return datadeingresso;
    }

    public void setDatadeingresso(String datadeingresso) {
        this.datadeingresso = datadeingresso;
    }

    public Evolucao getEvolucao() {
        return evolucao;
    }

    public void setEvolucao(Evolucao evolucao) {
        this.evolucao = evolucao;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Dicas getDicas() {
        return dicas;
    }

    public void setDicas(Dicas dicas) {
        this.dicas = dicas;
    }

    @Override
    public String toString() {
        return  nomealuno;
    }
}
