package com.example.eduar.tcc_personal.Detalhes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.Modelo.Avaliacao;
import com.example.eduar.tcc_personal.Modelo.Dicas;
import com.example.eduar.tcc_personal.Modelo.Evolucao;
import com.example.eduar.tcc_personal.Modelo.MarcarTreino;
import com.example.eduar.tcc_personal.R;
import com.example.eduar.tcc_personal.cadastro;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DetalheAvaliacao extends AppCompatActivity {

    private TextView mostradata, mostrastatus, mostraobservacao;
    Avaliacao avaliacao;
    Aluno aluno;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_avaliacao);

        mostradata = findViewById(R.id.mostradataavaliacao);
        mostrastatus = findViewById(R.id.mostrastatus);
        mostraobservacao = findViewById(R.id.mostraobservacaodaavaliacao);
        avaliacao = new Avaliacao();

        inicializarFirebase();
        verificaParametro();
        mostraraluno();
    }
    private void verificaParametro() {

        Intent intent = getIntent();
        if (intent.getSerializableExtra("Objeto") == null) {
            avaliacao = new Avaliacao();
        } else {
            avaliacao = (Avaliacao) intent.getSerializableExtra("Objeto");
            mostraraluno();
        }
    }

    private void mostraraluno() {
        mostradata.setText(avaliacao.getData().toString());
        mostraobservacao.setText(avaliacao.getTexto().toString());

        //RADIO BUTTON
        if(avaliacao.getExcelente() != null)
        {
            mostrastatus.setText(avaliacao.getExcelente().toString());
        }
        else if(avaliacao.getBom() != null)
        {
            mostrastatus.setText(avaliacao.getBom().toString());
        }
        else if(avaliacao.getRuim() != null)
        {
            mostrastatus.setText(avaliacao.getRuim().toString());
        }
    }


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(DetalheAvaliacao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}
