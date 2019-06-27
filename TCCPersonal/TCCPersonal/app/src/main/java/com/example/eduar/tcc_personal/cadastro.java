package com.example.eduar.tcc_personal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cadastro extends AppCompatActivity {

    private Button botaozinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        botaozinho = (Button)findViewById(R.id.botaocadastrar);

    }
    public void Cadastrarse(View view){

        Intent intent = new Intent(this, navi.class);
        startActivity(intent);
    }
}
