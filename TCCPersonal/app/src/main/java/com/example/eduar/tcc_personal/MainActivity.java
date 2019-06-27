package com.example.eduar.tcc_personal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Entrar(View view){

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void Cadastro(View view){

        Intent intent = new Intent(this, cadastro.class);
        startActivity(intent);
    }
}
