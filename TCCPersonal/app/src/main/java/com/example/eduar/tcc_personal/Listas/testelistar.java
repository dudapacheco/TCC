package com.example.eduar.tcc_personal.Listas;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eduar.tcc_personal.Aluno.AlterarInfoAluno;
import com.example.eduar.tcc_personal.Aluno.CadastroAluno;
import com.example.eduar.tcc_personal.Detalhes.DetalheAluno;
import com.example.eduar.tcc_personal.Marcar.CadastrarTreino;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.R;
import com.example.eduar.tcc_personal.cadastroEvolucao;
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
import java.util.List;
import java.util.UUID;

public class testelistar extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private ListView aliaslistview;
    private EditText filtroaluno;
    List<Aluno> alunos=new ArrayList<Aluno>();
    Aluno aluno;
    ArrayAdapter<Aluno> alunoArrayAdapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testelistar);

        aliaslistview=findViewById(R.id.listateste);
        filtroaluno = findViewById(R.id.filtrolistaaluno);
        floatingActionButton = findViewById(R.id.floattestelistar);
        inicializarFB();
        eventoEdit();
        registerForContextMenu(aliaslistview);

        aliaslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                aluno = (Aluno) parent.getAdapter().getItem(position);
                Intent intent = new Intent(testelistar.this, DetalheAluno.class);
                intent.putExtra("Objeto", aluno);
                startActivity(intent);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(testelistar.this, navi.class);
                startActivity(intent);
            }
        });
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
            Intent intent = new Intent(testelistar.this, CadastroAluno.class);
            startActivity(intent);
        }
        return true;
    }

    private void eventoEdit() {

        filtroaluno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String palavra = filtroaluno.getText().toString().trim();
                pesquisarNome(palavra);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void pesquisarNome(String palavra) {
        Query query;
        if(palavra.equals("")){
            query = databaseReference.child("Aluno").orderByChild("nomealuno");
        }else{
            query = databaseReference.child("Aluno").orderByChild("nomealuno").startAt(palavra).endAt(palavra+"\uf8ff");
                    //.endAt(palavra+"\uf8ff");
        }


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alunos.clear();
                for(DataSnapshot objsnapshot:dataSnapshot.getChildren()){
                    Aluno a = objsnapshot.getValue(Aluno.class);
                    alunos.add(a);
                }

                alunoArrayAdapter = new ArrayAdapter<Aluno>(testelistar.this, android.R.layout.simple_list_item_1, alunos);
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.add(Menu.NONE, 1, Menu.NONE, "Adicionar Aluno");
        menu.add(Menu.NONE,2,Menu.NONE,"Alterar Dados");
        menu.add(Menu.NONE,3,Menu.NONE,"Deletar");

        super.onCreateContextMenu(menu, v, menuInfo);
        }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // adicionada do outro codigo ajustado do prof. pegando objeto alunos.
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Integer posicao=info.position;
        aluno= (Aluno) aliaslistview.getItemAtPosition(posicao);

        switch (item.getItemId()) {

            case 1:
                Intent intent = new Intent(testelistar.this, CadastroAluno.class);
                startActivity(intent);
                break;

            case 2:
                Intent intent1 = new Intent(testelistar.this, AlterarInfoAluno.class);
                intent1.putExtra("Objeto", aluno);
                startActivity(intent1);
                break;

            case 3:
                databaseReference.child("Aluno").child(aluno.getMid().toString()).removeValue();
                Toast.makeText(this, "Aluno excluído.", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void inicializarFB(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }
}
