package com.example.eduar.tcc_personal.Marcar;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.eduar.tcc_personal.Listas.ListarHorarios;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.Modelo.MarcarTreino;
import com.example.eduar.tcc_personal.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AlterarMarcacao extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private EditText datatreino, horario, local;
    private ImageView calendario;
    private Spinner spinneralunos;
    List<Aluno> alunos=new ArrayList<Aluno>();
    Aluno aluno;
    MarcarTreino marcarTreino;
    ArrayAdapter<Aluno> alunoArrayAdapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_marcacao);

        aluno = new Aluno();
        marcarTreino = new MarcarTreino();

        datatreino = (EditText)findViewById(R.id.dataalterarmarcar);
        horario = (EditText)findViewById(R.id.horariodotreinoaltera);
        local = findViewById(R.id.localdotreinoaltera);
        spinneralunos = findViewById(R.id.spinneralunosalterar);
        calendario = findViewById(R.id.calendariotreinoaltera);
        spinneralunos.setOnItemSelectedListener(this);
        inicializarFirebase();
        verificaParametro();
        eventoDatabase();
    }

    private void verificaParametro() {

        Intent intent = getIntent();
        if (intent.getSerializableExtra("Objeto") == null) {
            marcarTreino = new MarcarTreino();
        } else {
            marcarTreino = (MarcarTreino)intent.getSerializableExtra("Objeto");
            if (intent.getSerializableExtra("Aluno") == null) {
                aluno = new Aluno();
            } else {
                aluno = (Aluno) intent.getSerializableExtra("Aluno");
                mostraraluno();
            }

        }
    }


    private void eventoDatabase() {
        databaseReference.child("Aluno").addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alunos.clear();
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    Aluno aluno=objSnapshot.getValue(Aluno.class);
                    alunos.add(aluno);
                }
                alunoArrayAdapter=new ArrayAdapter<Aluno> (getBaseContext(), android.R.layout.simple_list_item_1, alunos);
                spinneralunos.setAdapter(alunoArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }));
    }


    private void mostraraluno() {
        if(marcarTreino.getDatatreino() != null) {
            datatreino.setText(marcarTreino.getDatatreino().toString());
        }
        if(marcarTreino.getHorariotreino() != null) {
            horario.setText(marcarTreino.getHorariotreino().toString());
        }
        if(marcarTreino.getLocal() != null) {
            local.setText(marcarTreino.getLocal().toString());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.alterar_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itemalterar) {
            MarcarTreino mt = new MarcarTreino();
            mt.setMid(marcarTreino.getMid());
            mt.setDatatreino(datatreino.getText().toString().trim());
            mt.setHorariotreino(horario.getText().toString().trim());
            mt.setAluno(aluno);
            mt.setLocal(local.getText().toString().trim());

            databaseReference.child("Marcar").child(marcarTreino.getMid().toString()).setValue(mt);
            AlertDialog.Builder alerta = new AlertDialog.Builder(AlterarMarcacao.this);
            alerta.setTitle("Alterar Informações");
            alerta.setMessage("Informações alteradas.");
            alerta.show();
            AbrirActivity();

            NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                    .setDefaults(NotificationCompat.DEFAULT_ALL).setSmallIcon(R.mipmap.logolindo).setContentTitle("FitLife")
                    .setContentText("Treino remarcado! " + " " + mt.getDatatreino() + " " + "às" + " " + mt.getHorariotreino());
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, builder.build());
        }
        return true;
    }

    private void AbrirActivity() {
        Intent intent = new Intent(AlterarMarcacao.this, ListarHorarios.class);
        startActivity(intent);
    }


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(AlterarMarcacao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    @Override

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        aluno = (Aluno)parent.getAdapter().getItem(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}