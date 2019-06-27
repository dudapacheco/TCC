package com.example.eduar.tcc_personal.Aluno;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eduar.tcc_personal.CalendarActivity;
import com.example.eduar.tcc_personal.Listas.testelistar;
import com.example.eduar.tcc_personal.Marcar.CadastrarTreino;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.R;
import com.example.eduar.tcc_personal.cadastro;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class CadastroAluno extends AppCompatActivity {

    private static final String TAG = "CadastroAluno";

    private EditText editNome, edittelefone,editEmail, editObjetivo, editDataIngresso;
    private ImageView calendario;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);
        inicializarComponentes();
        inicializarFirebase();

    }

    private void inicializarComponentes(){

        editNome = (EditText)findViewById(R.id.nomeAlunoeditext);
        edittelefone = (EditText)findViewById(R.id.telefoneeditext);
        editEmail = (EditText)findViewById(R.id.emailalunoeditext);
        editObjetivo = (EditText)findViewById(R.id.objetivoeditext);
        editDataIngresso = (EditText)findViewById(R.id.datadeingresso);
        calendario = findViewById(R.id.calendarionoaluno);

        Intent inconting = getIntent();
        String data = inconting.getStringExtra("dataaluno");
        editDataIngresso.setText(data);

        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroAluno.this, CalendarActivity.class);
                intent.putExtra("tela",1);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
         if (id == R.id.cadastrarmenu) {
            if (editNome.getText().toString().length() == 0 || edittelefone.getText().toString().length() == 0 || editEmail.getText().toString().length() == 0 || editObjetivo.getText().toString().length() == 0 || editDataIngresso.getText().toString().length() == 0) {
                Toast.makeText(CadastroAluno.this, "Preencha todos os campos." + editNome.getText().toString(), Toast.LENGTH_SHORT).show();
            } else {
                Aluno a = new Aluno();
                a.setMid(UUID.randomUUID().toString());
                a.setNomealuno(editNome.getText().toString());
                a.setTelefone(edittelefone.getText().toString());
                a.setEmailaluno(editEmail.getText().toString());
                a.setObjetivo(editObjetivo.getText().toString());
                a.setDatadeingresso(editDataIngresso.getText().toString());

                databaseReference.child("Aluno").child(a.getMid()).setValue(a);
                AlertDialog.Builder alerta = new AlertDialog.Builder(CadastroAluno.this);
                alerta.setTitle("Meus Alunos");
                alerta.setMessage("Aluno registrado.");
                alerta.show();
                AbrirActivity();
                limparCampos();
            }

        }
        return true;
    }

    private void AbrirActivity() {
        Intent intent = new Intent(CadastroAluno.this, testelistar.class);
        startActivity(intent);
    }

    private void limparCampos(){
        editNome.setText("");
        edittelefone.setText("");
        editEmail.setText("");
        editObjetivo.setText("");
        editDataIngresso.setText("");
    }


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(CadastroAluno.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}
