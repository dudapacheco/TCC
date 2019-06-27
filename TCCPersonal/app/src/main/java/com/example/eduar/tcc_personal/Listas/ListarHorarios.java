package com.example.eduar.tcc_personal.Listas;

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

import com.example.eduar.tcc_personal.Marcar.AlterarMarcacao;
import com.example.eduar.tcc_personal.Marcar.CadastrarTreino;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.Modelo.Dicas;
import com.example.eduar.tcc_personal.Modelo.MarcarTreino;
import com.example.eduar.tcc_personal.R;
import com.example.eduar.tcc_personal.navi;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListarHorarios extends AppCompatActivity {
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    ListView aliaslistview;
    List<MarcarTreino> horarios =new ArrayList<MarcarTreino>();
    Aluno aluno;
    MarcarTreino horario;
    ArrayAdapter<MarcarTreino> horarioArrayAdapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_horarios);

        aliaslistview = findViewById(R.id.listamarcar);
        floatingActionButton = findViewById(R.id.floatlistahorario);
        inicializarFB();
        registerForContextMenu(aliaslistview);
        horario=new MarcarTreino();
        aluno = new Aluno();
        verificaParametro();
        eventoDatabase();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarHorarios.this, navi.class);
                startActivity(intent);
            }
        });

    }

    private void verificaParametro() {
        Intent intent = getIntent();
        if (intent.getSerializableExtra("Aluno") == null) {
            aluno = new Aluno();
        } else {
            aluno= (Aluno) intent.getSerializableExtra("Aluno");
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.add(Menu.NONE, 1, Menu.NONE, "Adicionar Treino");
        menu.add(Menu.NONE,2,Menu.NONE,"Alterar Dados");
        menu.add(Menu.NONE,3,Menu.NONE,"Deletar");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // adicionada do outro codigo ajustado do prof. pegando objeto alunos.
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Integer posicao=info.position;
        horario= (MarcarTreino) aliaslistview.getItemAtPosition(posicao);

        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(ListarHorarios.this, CadastrarTreino.class);
                startActivity(intent);
                break;

            case 2:
                Intent intent1 = new Intent(ListarHorarios.this, AlterarMarcacao.class);
                intent1.putExtra("Objeto", horario);
                intent1.putExtra("Aluno", aluno);
                startActivity(intent1);
                break;

            case 3:
                databaseReference.child("Marcar").child(horario.getMid().toString()).removeValue();
                Toast.makeText(this, "Treino excluído.", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void eventoDatabase(){
        databaseReference.child("Marcar").addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                horarios.clear();
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    MarcarTreino horario=objSnapshot.getValue(MarcarTreino.class);
                    horarios.add(horario);
                    Collections.sort(horarios, MarcarTreino.POR_DATA);
                }
                horarioArrayAdapter=new ArrayAdapter<MarcarTreino> (getBaseContext(), android.R.layout.simple_list_item_1, horarios);
                aliaslistview.setAdapter(horarioArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }));
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
            Intent intent = new Intent(ListarHorarios.this, CadastrarTreino.class);
            startActivity(intent);
        }
        return true;
    }


    private void inicializarFB(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }
}
