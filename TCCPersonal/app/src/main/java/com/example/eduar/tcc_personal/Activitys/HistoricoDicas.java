package com.example.eduar.tcc_personal.Activitys;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eduar.tcc_personal.Detalhes.DetalheTreino;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.Modelo.Dicas;
import com.example.eduar.tcc_personal.R;
import com.example.eduar.tcc_personal.Treino.TreinoAlterar;
import com.example.eduar.tcc_personal.navi;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoricoDicas extends AppCompatActivity{

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    ListView aliaslistview;
    List<Dicas> historico=new ArrayList<Dicas>();
    Dicas dicas;
    Aluno aluno;
    ArrayAdapter<Dicas> historicoArrayAdapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_dicas);

        aliaslistview=findViewById(R.id.historicodicas);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        inicializarFB();
        registerForContextMenu(aliaslistview);

        dicas = new Dicas();
        aluno = new Aluno();
        verificaParametro();
        eventoDatabase();

        aliaslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dicas = (Dicas) parent.getAdapter().getItem(position);
                Intent intent = new Intent(HistoricoDicas.this, DetalheTreino.class);
                intent.putExtra("Objeto", dicas);
                intent.putExtra("Objetoaluno", aluno);
                startActivity(intent);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoDicas.this, navi.class);
                startActivity(intent);
            }
        });


    }

    private void verificaParametro() {

        Intent intent = getIntent();
        if (intent.getSerializableExtra("Aluno") == null) {
            aluno = new Aluno();
        } else {
            aluno = (Aluno) intent.getSerializableExtra("Aluno");
        }
    }


        @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE,1,Menu.NONE,"Alterar Dados");
        menu.add(Menu.NONE,2,Menu.NONE,"Deletar");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Integer posicao=info.position;
        dicas= (Dicas) aliaslistview.getItemAtPosition(posicao);

        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(HistoricoDicas.this, TreinoAlterar.class);
                intent.putExtra("Objeto", dicas);
                intent.putExtra("ObjetoAluno", aluno);
                startActivity(intent);
                break;

            case 2:
                databaseReference.child("Aluno").child(aluno.getMid().toString()).child("Treino").child(dicas.getMid().toString()).removeValue();
                Toast.makeText(this, "Treino excluído.", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void eventoDatabase(){
        databaseReference.child("Aluno").child(aluno.getMid().toString()).child("Treino").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                historico.clear();
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    Dicas dicas=objSnapshot.getValue(Dicas.class);
                    historico.add(dicas);
                    Collections.sort(historico,Dicas.POR_DATA);
                }
                historicoArrayAdapter=new ArrayAdapter<Dicas> (getBaseContext(), android.R.layout.simple_list_item_1, historico);
                aliaslistview.setAdapter(historicoArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFB(){
        FirebaseApp.initializeApp(HistoricoDicas.this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

}
