package com.example.eduar.tcc_personal.Detalhes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eduar.tcc_personal.Marcar.CadastrarTreino;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.Modelo.Avaliacao;
import com.example.eduar.tcc_personal.Modelo.Dicas;
import com.example.eduar.tcc_personal.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetalheTreino extends AppCompatActivity{

    TextView mostradata, mostraind, mostrapressao, mostrastatus;
    ListView listaexercicio;
    List<String> informacoes=new ArrayList<String>();
    ArrayAdapter<String> infoArrayAdapter;
    Dicas dicas;
    Aluno aluno;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_treino);

        mostradata = findViewById(R.id.mostradatinhaavalia);
        mostraind = findViewById(R.id.indiceoxidetalhe);
        mostrapressao = findViewById(R.id.pressaodetalhe2);
        mostrastatus = findViewById(R.id.tipodetreinodetalhe);
        listaexercicio = findViewById(R.id.listadosexercicios);
        dicas=new Dicas();
        registerForContextMenu(listaexercicio);

        inicializarFirebase();
        verificaParametro();
        eventoDatabase();
        mostraraluno();

    }

    private void verificaParametro() {

        Intent intent = getIntent();
        if (intent.getSerializableExtra("Objeto") == null) {
            Log.i("dicas", "nulo");
            dicas = new Dicas();
        } else {
            dicas = (Dicas) intent.getSerializableExtra("Objeto");
            Log.i("dicas", "não nulo");
            if(intent.getSerializableExtra("Objetoaluno") == null){
                aluno = new Aluno();
            }
            else{
                aluno = (Aluno)intent.getSerializableExtra("Objetoaluno");
                mostraraluno();
            }
        }
    }

    private void mostraraluno() {
        if(dicas.getData() != null) {
            mostradata.setText(dicas.getData().toString());
        }
        if(dicas.getIndicedeoxi() != null) {
            mostraind.setText(dicas.getIndicedeoxi().toString());
        }
        if(dicas.getPressao() != null) {
            mostrapressao.setText(dicas.getPressao().toString());
        }
        //radio button
        if (dicas.getStatusdotreino() != null) {
            if (dicas.getStatusdotreino().toString().equals("Aeróbico")) {
                mostrastatus.setText(dicas.getStatusdotreino().toString());
            } else if(dicas.getStatusdotreino().toString().equals("Anaeróbico")) {
                mostrastatus.setText(dicas.getStatusdotreino().toString());
            }
        }
    }

    private void eventoDatabase(){
        databaseReference.child("Aluno").child(aluno.getMid().toString()).child("Treino").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                informacoes.clear();
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    dicas=objSnapshot.getValue(Dicas.class);
                    informacoes.add(dicas.paraLista());
                }
                infoArrayAdapter=new ArrayAdapter<String> (getBaseContext(), android.R.layout.simple_list_item_1, informacoes);
                listaexercicio.setAdapter(infoArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(DetalheTreino.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}
