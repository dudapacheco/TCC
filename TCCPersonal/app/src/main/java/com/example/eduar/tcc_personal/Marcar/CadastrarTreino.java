package com.example.eduar.tcc_personal.Marcar;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eduar.tcc_personal.Aluno.CadastroAluno;
import com.example.eduar.tcc_personal.CalendarActivity;
import com.example.eduar.tcc_personal.Listas.ListarHorarios;
import com.example.eduar.tcc_personal.Listas.testelistar;
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
import java.util.UUID;

public class CadastrarTreino extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final String TAG = "CadastrarTreino";

    private ImageView calendario;
    private EditText datatreino, horario, local;
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
        setContentView(R.layout.activity_cadastrar_treino);
        marcarTreino=new MarcarTreino();

        inicializarComponentes();
        inicializarFirebase();
        verificaParametro();
        eventoDatabase();


    }

    private void verificaParametro() {

        Intent intent = getIntent();
        if (intent.getSerializableExtra("Objeto") == null) {
            aluno = new Aluno();
        } else {
            aluno = (Aluno) intent.getSerializableExtra("Objeto");
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

    private void inicializarComponentes() {

        datatreino = (EditText) findViewById(R.id.datamarcartreino);
        horario = (EditText) findViewById(R.id.horariotreino);
        spinneralunos = (Spinner) findViewById(R.id.spinneralunos);
        local = findViewById(R.id.localdotreino);
        calendario = findViewById(R.id.calendariotreino);

        spinneralunos.setOnItemSelectedListener(this);

        Intent inconting = getIntent();
        String data = inconting.getStringExtra("data");
        aluno = (Aluno)inconting.getSerializableExtra("Objeto");
        datatreino.setText(data);

        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastrarTreino.this, CalendarActivity.class);
                intent.putExtra("tela",2);
                intent.putExtra("Objeto",aluno);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cadastrarmenu) {
            if (datatreino.getText().toString().length() == 0 || horario.getText().toString().length() == 0 || local.getText().toString().length() == 0){
                Toast.makeText(CadastrarTreino.this, "Preencha todos os campos." , Toast.LENGTH_SHORT).show();
            } else {
                marcarTreino.setMid(UUID.randomUUID().toString());
                marcarTreino.setDatatreino(datatreino.getText().toString());
                marcarTreino.setAluno(aluno);
                marcarTreino.setHorariotreino(horario.getText().toString());
                marcarTreino.setLocal(local.getText().toString());


                databaseReference.child("Marcar").child(marcarTreino.getMid().toString()).setValue(marcarTreino);
                AlertDialog.Builder alerta = new AlertDialog.Builder(CadastrarTreino.this);
                alerta.setTitle("Meus Alunos");
                alerta.setMessage("Treino marcado");
                alerta.show();
                AbrirActivity();

                NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setDefaults(NotificationCompat.DEFAULT_ALL).setSmallIcon(R.mipmap.logolindo).setContentTitle("FitLife")
                        .setContentText("Treino marcado! " + " " + marcarTreino.getDatatreino() + " " + "às" + " " + marcarTreino.getHorariotreino());
                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1, builder.build());
            }

        }
        return true;
    }

    private void AbrirActivity() {
        Intent intent = new Intent(CadastrarTreino.this, ListarHorarios.class);
        intent.putExtra("Aluno", aluno);
        startActivity(intent);
    }


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(CadastrarTreino.this);
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
