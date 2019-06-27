package com.example.eduar.tcc_personal.Detalhes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.Modelo.Avaliacao;
import com.example.eduar.tcc_personal.Modelo.Evolucao;
import com.example.eduar.tcc_personal.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetalheEvolucao extends AppCompatActivity {
    private TextView mostradata, mostrapeso, mostracintura, mostraquaril, mostraabdomen, mostrabicepsd, mostrabicepse, mostracoxad, mostracoxae, mostrapeito, mostrasube;
    Evolucao evolucao;
    Aluno aluno;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_evolucao);

        mostradata = findViewById(R.id.mostradataevolucao);
        mostrapeso = findViewById(R.id.mostrapeso);
        mostracintura = findViewById(R.id.mostracintura);
        mostraquaril = findViewById(R.id.mostraquadril);
        mostraabdomen = findViewById(R.id.mostraabdomen);
        mostrabicepsd = findViewById(R.id.mostrabicepsd);
        mostrabicepse = findViewById(R.id.mostrabicepse);
        mostracoxad = findViewById(R.id.mostracoxad);
        mostracoxae = findViewById(R.id.mostracoxae);
        mostrapeito = findViewById(R.id.mostrapeito);
        mostrasube = findViewById(R.id.mostrasubes);
        evolucao= new Evolucao();

        inicializarFirebase();
        verificaParametro();
        mostraraluno();
    }

    private void verificaParametro() {

        Intent intent = getIntent();
        if (intent.getSerializableExtra("Objeto") == null) {
            evolucao = new Evolucao();
        } else {
            evolucao= (Evolucao) intent.getSerializableExtra("Objeto");
            mostraraluno();
        }
    }

    private void mostraraluno() {
        mostradata.setText(evolucao.getData().toString());
        mostrapeso.setText(evolucao.getPeso().toString());
        mostracintura.setText(evolucao.getCintura().toString());
        mostraquaril.setText(evolucao.getQuadril().toString());
        mostraabdomen.setText(evolucao.getAbdomen().toString());
        mostrabicepsd.setText(evolucao.getBicepsd().toString());
        mostrabicepse.setText(evolucao.getBicepse().toString());
        mostracoxad.setText(evolucao.getCoxad().toString());
        mostracoxae.setText(evolucao.getCoxae().toString());
        mostrapeito.setText(evolucao.getPeito().toString());
        mostrasube.setText(evolucao.getSubescapular().toString());
    }


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(DetalheEvolucao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}
