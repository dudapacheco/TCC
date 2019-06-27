package com.example.eduar.tcc_personal.Activitys;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.eduar.tcc_personal.ConexaoBanco.Conexao;
import com.example.eduar.tcc_personal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetSenha extends AppCompatActivity {

    private EditText editResetarEmail;
    private Button btnresetar;
    private ProgressBar progressBar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_senha);

        inicializarComponentes();
        eventoClick();
    }

    private void eventoClick() {

        btnresetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editResetarEmail.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplication(), "Digite o seu Email.", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                resetSenha(email);
            }
        });
    }

    private void resetSenha(String email) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(ResetSenha.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ResetSenha.this, "Email enviado.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(ResetSenha.this, "Falha no envio.", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    
    private void inicializarComponentes(){
        editResetarEmail = (EditText)findViewById(R.id.editEmailReset);
        btnresetar = (Button)findViewById(R.id.resetarsenha);
        progressBar = (ProgressBar)findViewById(R.id.progressBarReset);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
