package com.example.eduar.tcc_personal.Listas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eduar.tcc_personal.Activitys.HistoricoDicas;
import com.example.eduar.tcc_personal.Treino.TreinoPrincipal;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class listar_dicas extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    private EditText filtrodicas;
    ListView aliaslistview;
    List<Aluno> alunosdicas=new ArrayList<Aluno>();
    Aluno aluno;
    ArrayAdapter<Aluno> alunoArrayAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_dicas);

        aliaslistview=findViewById(R.id.listardicas);
        filtrodicas = findViewById(R.id.filtrolistadicas);
        inicializarFB();
        registerForContextMenu(aliaslistview);
        aluno = new Aluno();
        eventoEdit();

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE,1,Menu.NONE,"Novo Treino");
        menu.add(Menu.NONE,2,Menu.NONE,"Histórico");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Integer posicao=info.position;
        aluno= (Aluno) aliaslistview.getItemAtPosition(posicao);
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(listar_dicas.this, TreinoPrincipal.class);
                intent.putExtra("Objeto", aluno);
                startActivity(intent);
                break;

            case 2:
                Intent intent1 = new Intent(listar_dicas.this, HistoricoDicas.class);
                intent1.putExtra("Aluno", aluno);
                startActivity(intent1);
                break;

        }
        return super.onContextItemSelected(item);
    }

    private void eventoEdit() {

        filtrodicas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String palavra = filtrodicas.getText().toString().trim();
                pesquisarNome(palavra);
            }
        });
    }

    private void pesquisarNome(String palavra) {
        Query query;
        if(palavra.equals("")){
            query = databaseReference.child("Aluno").orderByChild("nomealuno");
        }else{
            query = databaseReference.child("Aluno").orderByChild("nomealuno").startAt(palavra).endAt(palavra+"\uf8ff");
        }


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alunosdicas.clear();
                for(DataSnapshot objsnapshot:dataSnapshot.getChildren()){
                    Aluno a = objsnapshot.getValue(Aluno.class);
                    alunosdicas.add(a);
                }

                alunoArrayAdapter = new ArrayAdapter<Aluno>(listar_dicas.this, android.R.layout.simple_list_item_1, alunosdicas);
                aliaslistview.setAdapter(alunoArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        pesquisarNome("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.pesquisarmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pesquisaritem) {
            Toast.makeText(this, "filtrar", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void inicializarFB(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

}
