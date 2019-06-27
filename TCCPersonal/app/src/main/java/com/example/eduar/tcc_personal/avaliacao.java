package com.example.eduar.tcc_personal;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eduar.tcc_personal.Activitys.HistoricoAvalia;
import com.example.eduar.tcc_personal.Activitys.HistoricoEvolucao;
import com.example.eduar.tcc_personal.Aluno.CadastroAluno;
import com.example.eduar.tcc_personal.Listas.listar_avalia;
import com.example.eduar.tcc_personal.Listas.testelistar;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.Modelo.Avaliacao;
import com.example.eduar.tcc_personal.Modelo.Evolucao;
import com.example.eduar.tcc_personal.Modelo.Personal;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class avaliacao extends AppCompatActivity {

    private static final String TAG = "avaliacao";

    private EditText editAvalia, editData;
    private FirebaseAuth auth;
    private RadioGroup radioGroup;
    private RadioButton excelente, bom, ruim;
    private ImageView calendario;
    Aluno aluno;
    Avaliacao a;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        a=new Avaliacao();

        inicializarComponentes();
        inicializarFirebase();
        verificaParametro();
    }

    private void inicializarComponentes(){

        editAvalia = (EditText)findViewById(R.id.observacoes);
        editData = (EditText)findViewById(R.id.datinhaedit);
        radioGroup = (RadioGroup)findViewById(R.id.radiogrup);
        excelente = (RadioButton)findViewById(R.id.excelente);
        bom = (RadioButton)findViewById(R.id.bom);
        ruim = (RadioButton)findViewById(R.id.ruim);
        calendario = findViewById(R.id.calendarioavaliacao);

        Intent inconting = getIntent();
        String data = inconting.getStringExtra("dataavaliacao");
        aluno = (Aluno)inconting.getSerializableExtra("Objeto");
        editData.setText(data);

        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(avaliacao.this, CalendarActivity.class);
                intent.putExtra("tela",3);
                intent.putExtra("Objeto",aluno);
                startActivity(intent);
            }
        });

    }

    private void verificaParametro() {

        Intent intent = getIntent();
        if (intent.getSerializableExtra("Objeto") == null) {
            aluno = new Aluno();
        } else {
            aluno = (Aluno) intent.getSerializableExtra("Objeto");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id==R.id.cadastrarmenu){
            if ( editData.getText().toString().length() == 0 || editAvalia.getText().toString().length() == 0){
                Toast.makeText(avaliacao.this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            } else {

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.excelente:
                        a.setExcelente(excelente.getText().toString());
                        break;

                    case R.id.bom:
                        a.setBom(bom.getText().toString());
                        break;

                    case R.id.ruim:
                        a.setRuim(ruim.getText().toString());
                        break;
                }
                a.setMid(UUID.randomUUID().toString());
                a.setData(editData.getText().toString());
                a.setTexto(editAvalia.getText().toString());

                databaseReference.child("Aluno").child(aluno.getMid().toString()).child("Avaliacao").child(a.getMid().toString()).setValue(a);
                AlertDialog.Builder alerta = new AlertDialog.Builder(avaliacao.this);
                alerta.setTitle("Avaliação");
                alerta.setMessage("Avaliação registrada.");
                alerta.show();
                AbrirActivity();
            }
        }
        return true;
    }

    private void AbrirActivity() {
        Intent intent = new Intent(avaliacao.this, HistoricoAvalia.class);
        intent.putExtra("Aluno", aluno);
        startActivity(intent);
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(avaliacao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


}
