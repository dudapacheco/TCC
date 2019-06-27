package com.example.eduar.tcc_personal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

public class tela1 extends AppCompatActivity implements AdapterView.OnClickListener {

    private ImageView aliasagenda, aliasdicas, aliasevolu, aliasavalia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);

        aliasagenda = (ImageView)findViewById(R.id.agenda);
        aliasdicas = (ImageView)findViewById(R.id.cadastrodicas);
        aliasevolu = (ImageView)findViewById(R.id.evolucao);
        aliasavalia = (ImageView)findViewById(R.id.cadastraravalia);


        aliasagenda.setOnClickListener(this);
        aliasdicas.setOnClickListener(this);
        aliasavalia.setOnClickListener(this);
        aliasevolu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.agenda: {

                Intent intent = new Intent(this, agenda.class);
                startActivity(intent);
                break;

            }
            case R.id.evolucao: {

                Intent intent = new Intent(this, cadastroEvolucao.class);
                startActivity(intent);
                break;

            }
            case R.id.cadastraravalia: {

                Intent intent = new Intent(this, avaliacao.class);
                startActivity(intent);
                break;

            }
            case R.id.cadastrodicas: {

                Intent intent = new Intent(this, CadastrarDicas.class);
                startActivity(intent);
                break;
            }
        }

    }
}
