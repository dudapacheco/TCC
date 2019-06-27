
package com.example.eduar.tcc_personal.Aluno;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.eduar.tcc_personal.Listas.testelistar;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlterarInfoAluno extends AppCompatActivity{

    private EditText nomealterar, telefonealterar, emailalterar, objetivoalterar, dataingressoalterar;
    Aluno aluno;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_info_aluno);

        nomealterar = (EditText)findViewById(R.id.nomealunoaltera);
        telefonealterar = (EditText)findViewById(R.id.telefonealunoaltera);
        emailalterar = (EditText)findViewById(R.id.emailalunoaltera);
        objetivoalterar = (EditText)findViewById(R.id.objetivoaltera);
        dataingressoalterar = (EditText)findViewById(R.id.datadeingressoaltera);
        aluno=new Aluno();
        inicializarFirebase();
        verificaParametro();


    }

    private void verificaParametro() {

        Intent intent = getIntent();
        if (intent.getSerializableExtra("Objeto") == null) {
            aluno = new Aluno();
        } else {
            aluno = (Aluno) intent.getSerializableExtra("Objeto");
            mostraraluno();
        }
    }

    private void mostraraluno() {
        nomealterar.setText(aluno.getNomealuno().toString());
        telefonealterar.setText(aluno.getTelefone().toString());
        emailalterar.setText(aluno.getEmailaluno().toString());
        objetivoalterar.setText(aluno.getObjetivo().toString());
        dataingressoalterar.setText(aluno.getDatadeingresso().toString());
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
            Aluno a = new Aluno();
            a.setMid(aluno.getMid());
            a.setNomealuno(nomealterar.getText().toString().trim());
            a.setTelefone(telefonealterar.getText().toString().trim());
            a.setEmailaluno(emailalterar.getText().toString().trim());
            a.setObjetivo(objetivoalterar.getText().toString().trim());
            a.setDatadeingresso(dataingressoalterar.getText().toString().trim());

            databaseReference.child("Aluno").child(aluno.getMid().toString()).setValue(a);
            AlertDialog.Builder alerta = new AlertDialog.Builder(AlterarInfoAluno.this);
            alerta.setTitle("Alterar Informações");
            alerta.setMessage("Informações alteradas.");
            alerta.show();
            AbrirActivity();
        }
        return true;
    }
    private void AbrirActivity() {
        Intent intent = new Intent(AlterarInfoAluno.this, testelistar.class);
        startActivity(intent);
    }


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(AlterarInfoAluno.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


}