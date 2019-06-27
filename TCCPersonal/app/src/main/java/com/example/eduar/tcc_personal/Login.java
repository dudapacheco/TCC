package com.example.eduar.tcc_personal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eduar.tcc_personal.Activitys.ResetSenha;
import com.example.eduar.tcc_personal.ConexaoBanco.Conexao;
import com.example.eduar.tcc_personal.Modelo.Personal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button btnLogar;
    private TextView txtcadastre, txtreset;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Personal personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        personal = new Personal();

        inicializaComponentes();
        eventoClicks();

    }


    public void inicializaComponentes(){

        editEmail = (EditText)findViewById(R.id.emaillogin);
        editSenha = (EditText)findViewById(R.id.senhalogin);
        btnLogar = (Button)findViewById(R.id.botaoentrarlogin);
        txtcadastre = (TextView)findViewById(R.id.Cadastresepelologin);
        txtreset = (TextView)findViewById(R.id.resetarsenha);
        progressBar = (ProgressBar)findViewById(R.id.progressBarLogin);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void eventoClicks(){

        txtcadastre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), cadastro.class);
                startActivity(intent);
            }
        });

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                login(email,senha);
            }
        });

        txtreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ResetSenha.class);
                startActivity(i);
            }
        });
    }

    private void login(String email, String senha){
        progressBar.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    // pega id auth;
                    // navega realtime ate personal == id auth;
                    //instancia personal
                    String uiduser = auth.getUid();
                    //String iguais = databaseReference.child("Personal").child(personal.getmId());
                    Intent intent = new Intent(Login.this, navi.class);
                    intent.putExtra("id", uiduser);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login.this, "Email e/ou senha incorretos.", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        auth = Conexao.getFirebaseAuth();
    }


}
