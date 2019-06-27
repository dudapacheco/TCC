package com.example.eduar.tcc_personal.Modelo;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Comparator;

public class Dicas implements Serializable, Comparable<Dicas> {

    public Dicas() {

    }

    public static final Comparator<Dicas> POR_DATA = new Comparator<Dicas>() {
        public int compare(Dicas a1, Dicas a2) {
            return a1.compareTo(a2);
        }
    };


        private String mid;
        private String data;
        private String supino;
        private String repeticoessupino;
        private String pesosupino;
        private String seriesupino;
        private String levantamento;
        private String pesoLevantamento;
        private String repeticoeslevantamento;
        private String serielevantamento;
        private String agachamento;
        private String repeticoesagachamento;
        private String pesoagachamentosissy;
        private String serieagachamento;
        private String agachamentopeso;
        private String repeticoesagachamentopeso;
        private String pesoagachamentopeso;
        private String serieagachamentopeso;
        private String legpress45;
        private String pesoleg45;
        private String repeticoes45;
        private String serie45;
        private String abdominais;
        private String pesoabdominal;
        private String repeticoesabdominal;
        private String serieabdo;
        private String flexao;
        private String repeticoesflexao;
        private String serieflexao;
        private String barrafixa;
        private String repeticoesbarra;
        private String seriebarra;
        private String cordanaval;
        private String repeticoescordanaval;
        private String seriecordanaval;
        private String legpress90;
        private String pesoleg90;
        private String repeticoesleg90;
        private String serie90;
        private String corda;
        private String repeticoescorda;
        private String seriecorda;
        private String voador;
        private String repeticoesvoador;
        private String pesovoador;
        private String serievoador;
        private String indicedeoxi;
        private String pressao;
        private String statusdotreino;


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

        public String getSupino() {
            return supino;
        }

        public void setSupino(String supino) {
            this.supino = supino;
        }

        public String getRepeticoessupino() {
            return repeticoessupino;
        }

        public void setRepeticoessupino(String repeticoessupino) {
            this.repeticoessupino = repeticoessupino;
        }

        public String getPesosupino() {
            return pesosupino;
        }

        public void setPesosupino(String pesosupino) {
            this.pesosupino = pesosupino;
        }

        public String getSeriesupino() {
            return seriesupino;
        }

        public void setSeriesupino(String seriesupino) {
            this.seriesupino = seriesupino;
        }

        public String getLevantamento() {
            return levantamento;
        }

        public void setLevantamento(String levantamento) {
            this.levantamento = levantamento;
        }

        public String getPesoLevantamento() {
            return pesoLevantamento;
        }

        public void setPesoLevantamento(String pesoLevantamento) {
            this.pesoLevantamento = pesoLevantamento;
        }

        public String getRepeticoeslevantamento() {
            return repeticoeslevantamento;
        }

        public void setRepeticoeslevantamento(String repeticoeslevantamento) {
            this.repeticoeslevantamento = repeticoeslevantamento;
        }

        public String getSerielevantamento() {
            return serielevantamento;
        }

        public void setSerielevantamento(String serielevantamento) {
            this.serielevantamento = serielevantamento;
        }

        public String getAgachamento() {
            return agachamento;
        }

        public void setAgachamento(String agachamento) {
            this.agachamento = agachamento;
        }

        public String getRepeticoesagachamento() {
            return repeticoesagachamento;
        }

        public void setRepeticoesagachamento(String repeticoesagachamento) {
            this.repeticoesagachamento = repeticoesagachamento;
        }

        public String getPesoagachamentosissy() {
            return pesoagachamentosissy;
        }

        public void setPesoagachamentosissy(String pesoagachamentosissy) {
            this.pesoagachamentosissy = pesoagachamentosissy;
        }

        public String getSerieagachamento() {
            return serieagachamento;
        }

        public void setSerieagachamento(String serieagachamento) {
            this.serieagachamento = serieagachamento;
        }

        public String getAgachamentopeso() {
            return agachamentopeso;
        }

        public void setAgachamentopeso(String agachamentopeso) {
            this.agachamentopeso = agachamentopeso;
        }

        public String getRepeticoesagachamentopeso() {
            return repeticoesagachamentopeso;
        }

        public void setRepeticoesagachamentopeso(String repeticoesagachamentopeso) {
            this.repeticoesagachamentopeso = repeticoesagachamentopeso;
        }

        public String getPesoagachamentopeso() {
            return pesoagachamentopeso;
        }

        public void setPesoagachamentopeso(String pesoagachamentopeso) {
            this.pesoagachamentopeso = pesoagachamentopeso;
        }

        public String getSerieagachamentopeso() {
            return serieagachamentopeso;
        }

        public void setSerieagachamentopeso(String serieagachamentopeso) {
            this.serieagachamentopeso = serieagachamentopeso;
        }

        public String getLegpress45() {
            return legpress45;
        }

        public void setLegpress45(String legpress45) {
            this.legpress45 = legpress45;
        }

        public String getPesoleg45() {
            return pesoleg45;
        }

        public void setPesoleg45(String pesoleg45) {
            this.pesoleg45 = pesoleg45;
        }

        public String getRepeticoes45() {
            return repeticoes45;
        }

        public void setRepeticoes45(String repeticoes45) {
            this.repeticoes45 = repeticoes45;
        }

        public String getSerie45() {
            return serie45;
        }

        public void setSerie45(String serie45) {
            this.serie45 = serie45;
        }

        public String getAbdominais() {
            return abdominais;
        }

        public void setAbdominais(String abdominais) {
            this.abdominais = abdominais;
        }

        public String getPesoabdominal() {
            return pesoabdominal;
        }

        public void setPesoabdominal(String pesoabdominal) {
            this.pesoabdominal = pesoabdominal;
        }

        public String getRepeticoesabdominal() {
            return repeticoesabdominal;
        }

        public void setRepeticoesabdominal(String repeticoesabdominal) {
            this.repeticoesabdominal = repeticoesabdominal;
        }

        public String getSerieabdo() {
            return serieabdo;
        }

        public void setSerieabdo(String serieabdo) {
            this.serieabdo = serieabdo;
        }

        public String getFlexao() {
            return flexao;
        }

        public void setFlexao(String flexao) {
            this.flexao = flexao;
        }

        public String getRepeticoesflexao() {
            return repeticoesflexao;
        }

        public void setRepeticoesflexao(String repeticoesflexao) {
            this.repeticoesflexao = repeticoesflexao;
        }

        public String getSerieflexao() {
            return serieflexao;
        }

        public void setSerieflexao(String serieflexao) {
            this.serieflexao = serieflexao;
        }

        public String getBarrafixa() {
            return barrafixa;
        }

        public void setBarrafixa(String barrafixa) {
            this.barrafixa = barrafixa;
        }

        public String getRepeticoesbarra() {
            return repeticoesbarra;
        }

        public void setRepeticoesbarra(String repeticoesbarra) {
            this.repeticoesbarra = repeticoesbarra;
        }

        public String getSeriebarra() {
            return seriebarra;
        }

        public void setSeriebarra(String seriebarra) {
            this.seriebarra = seriebarra;
        }

        public String getCordanaval() {
            return cordanaval;
        }

        public void setCordanaval(String cordanaval) {
            this.cordanaval = cordanaval;
        }

        public String getRepeticoescordanaval() {
            return repeticoescordanaval;
        }

        public void setRepeticoescordanaval(String repeticoescordanaval) {
            this.repeticoescordanaval = repeticoescordanaval;
        }

        public String getSeriecordanaval() {
            return seriecordanaval;
        }

        public void setSeriecordanaval(String seriecordanaval) {
            this.seriecordanaval = seriecordanaval;
        }

        public String getLegpress90() {
            return legpress90;
        }

        public void setLegpress90(String legpress90) {
            this.legpress90 = legpress90;
        }

        public String getPesoleg90() {
            return pesoleg90;
        }

        public void setPesoleg90(String pesoleg90) {
            this.pesoleg90 = pesoleg90;
        }

        public String getRepeticoesleg90() {
            return repeticoesleg90;
        }

        public void setRepeticoesleg90(String repeticoesleg90) {
            this.repeticoesleg90 = repeticoesleg90;
        }

        public String getSerie90() {
            return serie90;
        }

        public void setSerie90(String serie90) {
            this.serie90 = serie90;
        }

        public String getCorda() {
            return corda;
        }

        public void setCorda(String corda) {
            this.corda = corda;
        }

        public String getRepeticoescorda() {
            return repeticoescorda;
        }

        public void setRepeticoescorda(String repeticoescorda) {
            this.repeticoescorda = repeticoescorda;
        }

        public String getSeriecorda() {
            return seriecorda;
        }

        public void setSeriecorda(String seriecorda) {
            this.seriecorda = seriecorda;
        }

        public String getVoador() {
            return voador;
        }

        public void setVoador(String voador) {
            this.voador = voador;
        }

        public String getRepeticoesvoador() {
            return repeticoesvoador;
        }

        public void setRepeticoesvoador(String repeticoesvoador) {
            this.repeticoesvoador = repeticoesvoador;
        }

        public String getPesovoador() {
            return pesovoador;
        }

        public void setPesovoador(String pesovoador) {
            this.pesovoador = pesovoador;
        }

        public String getSerievoador() {
            return serievoador;
        }

        public void setSerievoador(String serievoador) {
            this.serievoador = serievoador;
        }

        public String getStatusdotreino() {
            return statusdotreino;
        }

        public void setStatusdotreino(String statusdotreino) {
            this.statusdotreino = statusdotreino;
        }

        public String getIndicedeoxi() {
            return indicedeoxi;
        }

        public void setIndicedeoxi(String indicedeoxi) {
            this.indicedeoxi = indicedeoxi;
        }

        public String getPressao() {
            return pressao;
        }

        public void setPressao(String pressao) {
            this.pressao = pressao;
        }

        @Override
        public String toString() {
            return data;
        }

        public String paraLista(){
            return  abdominais + "\n" + "Séries: " + serieabdo + "\n" + "Repetições: " + repeticoesabdominal + "\n" + "Peso: " + pesoabdominal + "\n"+ "\n" +
                    agachamento + "\n" + "Séries: " + serieagachamento + "\n" + "Repetições: " + repeticoesagachamento + "\n" + "Peso: " + pesoagachamentosissy + "\n"+ "\n"+
                    agachamentopeso + "\n" + "Séries: " + serieagachamentopeso + "\n" + "Repetições: " + repeticoesagachamentopeso + "\n" + "Peso: " + pesoagachamentopeso + "\n"+ "\n"+
                    barrafixa  + "\n" + "Séries: " + seriebarra + "\n" + "Repetições: " + repeticoesbarra +  "\n" + "\n"+
                    corda + "\n" + "Séries: " + seriecorda + "\n" + "Repetições: " + repeticoescorda + "\n" + "\n"+
                    cordanaval + "\n" + "Séries: " + seriecordanaval + "\n" + "Repetições: " + repeticoescordanaval + "\n" + "\n"+
                    flexao + "\n" + "Séries: " + serieflexao + "\n" + "Repetições: " + repeticoesflexao + "\n" + "\n"+
                    legpress45 + "\n" + "Séries: " + serie45 + "\n" + "Repetições: " + repeticoes45 + "\n" + "Peso: " + pesoleg45 + "\n"+ "\n"+
                    legpress90 + "\n" + "Séries: " + serie90 + "\n" + "Repetições: " + repeticoesleg90 + "\n" + "Peso: " + pesoleg90 + "\n"+"\n"+
                    levantamento + "\n" + "Séries: " + serielevantamento + "\n" + "Repetições: " + repeticoeslevantamento + "\n" + "Peso: " + pesoLevantamento + "\n"+"\n"+
                    supino + "\n" + "Séries: " + seriesupino + "\n" + "Repetições: " + repeticoessupino + "\n" + "Peso: " + pesosupino + "\n"+"\n"+
                    voador + "\n" + "Séries: " + serievoador + "\n" + "Repetições: " + repeticoesvoador + "\n" + "Peso: " + pesovoador;


        }

    @Override
    public int compareTo(@NonNull Dicas o) {
        int compare = data.compareTo(o.data);
        return compare * (-1);
    }
}

