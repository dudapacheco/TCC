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

import com.example.eduar.tcc_personal.Avaliacao.Alteraravalia;
import com.example.eduar.tcc_personal.Detalhes.DetalheAvaliacao;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoricoAvalia extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    ListView aliaslistview;
    List<Avaliacao> historicoavaliacoes=new ArrayList<Avaliacao>();
    Avaliacao avaliacao;
    Aluno aluno;
    ArrayAdapter<Avaliacao> avaliacaoArrayAdapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_avalia);

        aliaslistview=findViewById(R.id.historicoavaliacao);
        floatingActionButton = findViewById(R.id.floathistoricoavalia);
        inicializarFB();
        registerForContextMenu(aliaslistview);

        avaliacao = new Avaliacao();
        aluno = new Aluno();
        verificaParametro();
        eventoDatabase();

        aliaslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                avaliacao = (Avaliacao) parent.getAdapter().getItem(position);
                Intent intent = new Intent(HistoricoAvalia.this, DetalheAvaliacao.class);
                intent.putExtra("Objeto", avaliacao);
                startActivity(intent);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoAvalia.this, navi.class);
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
        avaliacao= (Avaliacao) aliaslistview.getItemAtPosition(posicao);
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(HistoricoAvalia.this, Alteraravalia.class);
                intent.putExtra("Objeto", avaliacao);
                intent.putExtra("ObjetoAluno", aluno);
                startActivity(intent);
                break;

            case 2:
                databaseReference.child("Aluno").child(aluno.getMid().toString()).child("Avaliacao").child(avaliacao.getMid().toString()).removeValue();
                Toast.makeText(this, "Avaliação excluída.", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void eventoDatabase(){
        databaseReference.child("Aluno").child(aluno.getMid()).child("Avaliacao").addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                historicoavaliacoes.clear();
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    Avaliacao avaliacao=objSnapshot.getValue(Avaliacao.class);
                    historicoavaliacoes.add(avaliacao);
                    Collections.sort(historicoavaliacoes, Avaliacao.POR_DATA);
                }
                avaliacaoArrayAdapter=new ArrayAdapter<Avaliacao> (getBaseContext(), android.R.layout.simple_list_item_1, historicoavaliacoes);
                aliaslistview.setAdapter(avaliacaoArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }));
    }

    private void inicializarFB(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }
}
