package com.example.eduar.tcc_personal.Detalhes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.Modelo.Evolucao;
import com.example.eduar.tcc_personal.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetalheAluno extends AppCompatActivity {

    private TextView mostradataingresso, mostranome, mostratelefone, mostraemail, mostraobjetivo;
    Aluno aluno;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe__aluno);

        mostradataingresso = findViewById(R.id.mostradataalunodetalhe);
        mostranome = findViewById(R.id.mostraalunodetalhe);
        mostratelefone = findViewById(R.id.mostratelefonedetalhe);
        mostraemail = findViewById(R.id.mostraemaildetalhe);
        mostraobjetivo = findViewById(R.id.mostraobjetivodetalhe);
        aluno= new Aluno();

        inicializarFirebase();
        verificaParametro();
        mostraraluno();
    }

    private void verificaParametro() {

        Intent intent = getIntent();
        if (intent.getSerializableExtra("Objeto") == null) {
            aluno = new Aluno();
        } else {
            aluno= (Aluno) intent.getSerializableExtra("Objeto");
        }
    }

    private void mostraraluno() {
        mostradataingresso.setText(aluno.getDatadeingresso().toString());
        mostranome.setText(aluno.getNomealuno().toString());
        mostratelefone.setText(aluno.getTelefone().toString());
        mostraemail.setText(aluno.getEmailaluno().toString());
        mostraobjetivo.setText(aluno.getObjetivo().toString());
    }


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(DetalheAluno.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}
