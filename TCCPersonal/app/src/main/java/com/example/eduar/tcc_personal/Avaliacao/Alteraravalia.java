package com.example.eduar.tcc_personal.Avaliacao;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.eduar.tcc_personal.Activitys.HistoricoAvalia;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.Modelo.Avaliacao;
import com.example.eduar.tcc_personal.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Alteraravalia extends AppCompatActivity {

    private EditText observaaltera, dataaltera;
    private FirebaseAuth auth;
    private RadioGroup radioGroup;
    private RadioButton excelentealtera, bomaltera, ruimaltera;
    Aluno aluno;
    Avaliacao avaliacao;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteraravalia);

        dataaltera = findViewById(R.id.dataalteraravalia);
        observaaltera = findViewById(R.id.observacoesalterar);
        radioGroup = findViewById(R.id.radiogrupalterar);
        excelentealtera = findViewById(R.id.excelentealtera);
        bomaltera = findViewById(R.id.bomaltera);
        ruimaltera = findViewById(R.id.ruimaltera);

        aluno = new Aluno();
        avaliacao = new Avaliacao();

        inicializarFirebase();
        verificaParametro();
    }
    private void verificaParametro() {

        Intent intent = getIntent();
        if (intent.getSerializableExtra("Objeto") == null) {
            avaliacao = new Avaliacao();
        }
        else {
            avaliacao = (Avaliacao) intent.getSerializableExtra("Objeto");
            if (intent.getSerializableExtra("ObjetoAluno") == null) {
                aluno = new Aluno();
            }
            else {
                aluno = (Aluno) intent.getSerializableExtra("ObjetoAluno");
                mostraravaliacao();
            }

        }

    }

    private void mostraravaliacao() {
        dataaltera.setText(avaliacao.getData().toString());
        observaaltera.setText(avaliacao.getTexto().toString());
        radioGroup.clearCheck();
        if (avaliacao.getExcelente()!=null){
            excelentealtera.setChecked(true);
        }
        else
        if (avaliacao.getBom()!=null){
            bomaltera.setChecked(true);
        }
        else
        if (avaliacao.getRuim()!=null){
            ruimaltera.setChecked(true);
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
            Avaliacao a = new Avaliacao();
            a.setMid(avaliacao.getMid());
            a.setData(dataaltera.getText().toString().trim());
            a.setTexto(observaaltera.getText().toString().trim());
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.excelentealtera:
                    a.setExcelente(excelentealtera.getText().toString().trim());
                    break;

                case R.id.bomaltera:
                    a.setBom(bomaltera.getText().toString().trim());
                    break;

                case R.id.ruimaltera:
                    a.setRuim(ruimaltera.getText().toString().trim());
                    break;
            }
            databaseReference.child("Aluno").child(aluno.getMid().toString()).child("Avaliacao").child(avaliacao.getMid().toString()).setValue(a);
            AlertDialog.Builder alerta = new AlertDialog.Builder(Alteraravalia.this);
            alerta.setTitle("Alterar Informações");
            alerta.setMessage("Informações alteradas.");
            alerta.show();
            AbrirActivity();
        }
        return true;
    }
    private void AbrirActivity() {
        Intent intent = new Intent(Alteraravalia.this, HistoricoAvalia.class);
        intent.putExtra("Aluno", aluno);
        startActivity(intent);
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(Alteraravalia.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

}
