package com.example.eduar.tcc_personal;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eduar.tcc_personal.ConexaoBanco.Conexao;
import com.example.eduar.tcc_personal.Modelo.Personal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class cadastro extends AppCompatActivity {

    private EditText editNome, editCidade,editEmail, editSenha;
    private Button btnCadastrar;
    private FirebaseAuth auth;
    Personal p ;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        p = new Personal();

        inicializarComponentes();
        inicializarFirebase();
        eventosClick();

    }

    private void inicializarComponentes(){

        editNome = (EditText)findViewById(R.id.nomeeditext);
        editCidade = (EditText)findViewById(R.id.cidadeeditext);
        editEmail = (EditText)findViewById(R.id.emaileditext);
        editSenha = (EditText)findViewById(R.id.senhaeditext);
        btnCadastrar = (Button)findViewById(R.id.cadastro);
    }

    private void eventosClick(){

            btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( editNome.getText().toString().length() == 0 || editCidade.getText().toString().length() == 0 || editEmail.getText().toString().length() == 0 || editSenha.getText().toString().length() == 0){
                        Toast.makeText(cadastro.this, "Preencha todos os campos." + editNome.getText().toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        String email = editEmail.getText().toString().trim();
                        String senha = editSenha.getText().toString().trim();
                        p.setmId(UUID.randomUUID().toString());
                        p.setNome(editNome.getText().toString());
                        p.setCidade(editCidade.getText().toString());
                        p.setEmail(editEmail.getText().toString());
                        p.setSenha(editSenha.getText().toString());

                        criarUsuario(email, senha);

                        databaseReference.child("Personal").child(p.getmId()).setValue(p);
                        AlertDialog.Builder alerta = new AlertDialog.Builder(cadastro.this);
                        alerta.setTitle("FitLife");
                        alerta.setMessage("Você está cadastrado. Obrigado!");
                        alerta.show();
                    }
                }
            });
    }

    private void criarUsuario(String email, String senha) {
        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(cadastro.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(cadastro.this, navi.class);
                    intent.putExtra("Objeto", p);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(cadastro.this, "Erro no cadastro.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(cadastro.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
    @Override
    protected void onStart() {
        super.onStart();

        auth = Conexao.getFirebaseAuth();
    }





}
