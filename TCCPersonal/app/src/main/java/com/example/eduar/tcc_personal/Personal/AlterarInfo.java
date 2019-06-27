package com.example.eduar.tcc_personal.Personal;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.eduar.tcc_personal.Modelo.Personal;
import com.example.eduar.tcc_personal.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlterarInfo extends AppCompatActivity {

    private EditText nomepersonal, cidadepersonal, emailpersonal, senhapersonal;
    Personal personal;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_info);

        nomepersonal = (EditText)findViewById(R.id.nomeeditaltera);
        cidadepersonal = (EditText)findViewById(R.id.cidadeeditaltera);
        emailpersonal = (EditText)findViewById(R.id.emaileditaltera);
        senhapersonal = (EditText)findViewById(R.id.senhaeditaltera);
        personal = new Personal();
        inicializarFirebase();
        verificaParametro();

    }

    private void verificaParametro() {
        Intent intent = getIntent();
        if (intent.getSerializableExtra("Objeto") == null) {
            personal = new Personal();
        } else {
            personal = (Personal) intent.getSerializableExtra("Objeto");
            mostrarpersonal();
        }
    }

    private void mostrarpersonal() {
        if(personal.getNome() != null) {
            nomepersonal.setText(personal.getNome().toString());
        }
        if(personal.getCidade() != null) {
            cidadepersonal.setText(personal.getCidade().toString());
        }
        if(personal.getEmail() != null) {
            emailpersonal.setText(personal.getEmail().toString());
        }
        if(personal.getSenha() != null) {
            senhapersonal.setText(personal.getSenha().toString());
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
            Personal p = new Personal();
            p.setmId(personal.getmId());
            p.setNome(nomepersonal.getText().toString().trim());
            p.setCidade(cidadepersonal.getText().toString().trim());
            p.setEmail(emailpersonal.getText().toString().trim());
            p.setSenha(senhapersonal.getText().toString().trim());

            databaseReference.child("Personal").child(personal.getmId().toString()).setValue(p);
            AlertDialog.Builder alerta = new AlertDialog.Builder(AlterarInfo.this);
            alerta.setTitle("Alterar Informações");
            alerta.setMessage("Informações alteradas.");
            alerta.show();
        }
        return true;
    }


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(AlterarInfo.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


}
