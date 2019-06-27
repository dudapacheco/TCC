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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eduar.tcc_personal.Activitys.HistoricoEvolucao;
import com.example.eduar.tcc_personal.Aluno.CadastroAluno;
import com.example.eduar.tcc_personal.Listas.testelistar;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.Modelo.Evolucao;
import com.example.eduar.tcc_personal.Modelo.Personal;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class cadastroEvolucao extends AppCompatActivity {

    private static final String TAG = "cadastroEvolucao";

    private EditText  editData, editPeso, editCintura, editQuadril, editAbdomen, editBicepsd, editBicepse, editCoxad, editCoxae, editpeito, editsubes;
    private ImageView calendario;

    Aluno aluno;
    Evolucao e;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evolucao);
        e = new Evolucao();

        inicializarComponentes();
        inicializarFirebase();
        verificaParametro();
    }

    private void inicializarComponentes(){

        editData = (EditText)findViewById(R.id.datadoregistro);
        editPeso = (EditText)findViewById(R.id.pesoedit);
        editCintura = (EditText)findViewById(R.id.cinturaedit);
        editQuadril = (EditText)findViewById(R.id.quadriledit);
        editAbdomen = (EditText)findViewById(R.id.abdomenedit);
        editBicepsd = (EditText)findViewById(R.id.bicepsdedit);
        editBicepse = (EditText)findViewById(R.id.bicepseedit);
        editCoxad = (EditText)findViewById(R.id.coxadedit);
        editCoxae = (EditText)findViewById(R.id.coxaeedit);
        editpeito = (EditText)findViewById(R.id.peitoedit);
        editsubes = (EditText)findViewById(R.id.subescapularedit);
        calendario = findViewById(R.id.calendarioevolucao);

        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cadastroEvolucao.this, CalendarActivity.class);
                intent.putExtra("tela",4);
                intent.putExtra("Objeto",aluno);
                startActivity(intent);
            }
        });

        Intent inconting = getIntent();
        String data = inconting.getStringExtra("dataevolucao");
        aluno = (Aluno)inconting.getSerializableExtra("Objeto");
        editData.setText(data);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
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
        public boolean onOptionsItemSelected (MenuItem item){
            int id = item.getItemId();
            if (id == R.id.cadastrarmenu) {
                if (editData.getText().toString().length() == 0 || editPeso.getText().toString().length() == 0 || editCintura.getText().toString().length() == 0 || editQuadril.getText().toString().length() == 0 || editAbdomen.getText().toString().length() == 0 || editBicepsd.getText().toString().length() == 0 || editBicepse.getText().toString().length() == 0 || editCoxad.getText().toString().length() == 0 || editCoxae.getText().toString().length() == 0) {
                    Toast.makeText(cadastroEvolucao.this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                } else {

                    e.setMid(UUID.randomUUID().toString());
                    e.setData(editData.getText().toString());
                    e.setPeso(Double.parseDouble(editPeso.getText().toString()));
                    e.setCintura(Double.parseDouble(editCintura.getText().toString()));
                    e.setQuadril(Double.parseDouble(editQuadril.getText().toString()));
                    e.setAbdomen(Double.parseDouble(editAbdomen.getText().toString()));
                    e.setBicepsd(Double.parseDouble(editBicepsd.getText().toString()));
                    e.setBicepse(Double.parseDouble(editBicepse.getText().toString()));
                    e.setCoxad(Double.parseDouble(editCoxad.getText().toString()));
                    e.setCoxae(Double.parseDouble(editCoxae.getText().toString()));
                    e.setPeito(Double.parseDouble(editpeito.getText().toString()));
                    e.setSubescapular(Double.parseDouble(editsubes.getText().toString()));


                    databaseReference.child("Aluno").child(aluno.getMid().toString()).child("Evolucao").child(e.getMid()).setValue(e);
                    AlertDialog.Builder alerta = new AlertDialog.Builder(cadastroEvolucao.this);
                    alerta.setTitle("Evolução");
                    alerta.setMessage("Evolução registrada.");
                    alerta.show();
                    AbrirActivty();
                    limparCampos();
                }
            }

            return true;
        }

    private void AbrirActivty() {
        Intent intent = new Intent(cadastroEvolucao.this, HistoricoEvolucao.class);
        intent.putExtra("Aluno", aluno);
        startActivity(intent);
    }


    private void limparCampos(){
        editData.setText("");
        editPeso.setText("");
        editCintura.setText("");
        editQuadril.setText("");
        editAbdomen.setText("");
        editBicepsd.setText("");
        editBicepse.setText("");
        editCoxad.setText("");
        editCoxae.setText("");
        editpeito.setText("");
        editsubes.setText("");
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(cadastroEvolucao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

}
