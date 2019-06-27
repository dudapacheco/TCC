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

import com.example.eduar.tcc_personal.Evolucao.Alterarevolu;
import com.example.eduar.tcc_personal.Detalhes.DetalheEvolucao;
import com.example.eduar.tcc_personal.Listas.listar_evolu;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.Modelo.Avaliacao;
import com.example.eduar.tcc_personal.Modelo.Dicas;
import com.example.eduar.tcc_personal.Modelo.Evolucao;
import com.example.eduar.tcc_personal.R;
import com.example.eduar.tcc_personal.navi;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoricoEvolucao extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    ListView aliaslistview;
    List<Evolucao> historico=new ArrayList<Evolucao>();
    Evolucao evolucao;
    ArrayAdapter<Evolucao> historicoArrayAdapter;
    Aluno aluno;
    FloatingActionButton floatingActionButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_evolucao);

        aliaslistview=findViewById(R.id.historicoevolucao);
        floatingActionButton2 = findViewById(R.id.floathistoricoevolu);
        inicializarFB();

        registerForContextMenu(aliaslistview);

        evolucao = new Evolucao();
        aluno = new Aluno();
        verificaParametro();
        evento();

        aliaslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                evolucao = (Evolucao) parent.getAdapter().getItem(position);
                Intent intent = new Intent(HistoricoEvolucao.this, DetalheEvolucao.class);
                intent.putExtra("Objeto", evolucao);
                startActivity(intent);
            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoEvolucao.this, navi.class);
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
        evolucao= (Evolucao) aliaslistview.getItemAtPosition(posicao);
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(HistoricoEvolucao.this, Alterarevolu.class);
                intent.putExtra("Objeto", evolucao);
                intent.putExtra("Aluno", aluno);
                startActivity(intent);
                break;

            case 2:
                databaseReference.child("Aluno").child(aluno.getMid().toString()).child("Evolucao").child(evolucao.getMid().toString()).removeValue();
                Toast.makeText(this, "Evolução excluída.", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void evento() {
        Query query;

        query = databaseReference.child("Aluno").child(aluno.getMid().toString()).child("Evolucao").orderByChild("data");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                historico.clear();
                for(DataSnapshot objsnapshot:dataSnapshot.getChildren()){
                    Evolucao evolucao = objsnapshot.getValue(Evolucao.class);
                    historico.add(evolucao);
                    Collections.sort(historico, Evolucao.POR_DATA);
                }

                historicoArrayAdapter = new ArrayAdapter<Evolucao>(HistoricoEvolucao.this, android.R.layout.simple_list_item_1, historico);
                aliaslistview.setAdapter(historicoArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void eventoDatabase(){

//      //   Toast.makeText(this, ""+aluno.getMid().toString(), Toast.LENGTH_SHORT).show();
//        databaseReference.child("Aluno").child(aluno.getMid().toString()).child("Evolucao").addValueEventListener((new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                historico.clear();
//                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){
//                    Evolucao evolucao=objSnapshot.getValue(Evolucao.class);
//                    historico.add(evolucao);
//                }
//                historicoArrayAdapter=new ArrayAdapter<Evolucao> (getBaseContext(), android.R.layout.simple_list_item_1, historico);
//                aliaslistview.setAdapter(historicoArrayAdapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        }));
    }

    private void inicializarFB(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }
}
