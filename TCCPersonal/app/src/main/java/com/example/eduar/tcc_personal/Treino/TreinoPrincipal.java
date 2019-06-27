package com.example.eduar.tcc_personal.Treino;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.eduar.tcc_personal.Activitys.HistoricoDicas;
import com.example.eduar.tcc_personal.Activitys.HistoricoEvolucao;
import com.example.eduar.tcc_personal.CalendarActivity;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.Modelo.Dicas;
import com.example.eduar.tcc_personal.R;
import com.example.eduar.tcc_personal.cadastroEvolucao;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class TreinoPrincipal extends AppCompatActivity {

    private static final String TAG = "treino";

    private EditText datatreino;
    private EditText indicedeo;
    private EditText pressao;
    private RadioGroup radioGroup;
    private RadioButton aerobico, anaerobico;
    private ImageView calendario;
    private CheckBox supino, levantamento, barra, voador, abdominal, flexao;
    private EditText seriesupino, serielevantamento, seriebarra, serievoador, serieabdo, serieflexao;
    private EditText repeticaosupino, repeticaolevantamento, repeticaobarra, repeticaovoador, repeticaoabdo, repeticaoflexao;
    private EditText pesosupino, pesolevantamento, pesovoador, pesobdo;
    private CheckBox agachamento, agachamentopeso, corda, leg45, leg90, cordanaval;
    private EditText serieagacha, serieagachapeso, seriecorda, serieleg45, serieleg90, seriecordanaval;
    private EditText repeticaoagacha, repeticaoagachapeso, repeticaocorda, repeticaoleg45, repeticaoleg90, repeticaocordanaval;
    private EditText pesoagacha, pesoagachapeso, pesoleg45, pesoleg90;

    Dicas dicas;
    Aluno aluno;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treino_principal);

        dicas = new Dicas();
        aluno = new Aluno();

        datatreino = findViewById(R.id.datatreino);
        indicedeo = findViewById(R.id.indiceoxigenio);
        pressao = findViewById(R.id.pressao);
        radioGroup = findViewById(R.id.radiogroup);
        aerobico = findViewById(R.id.aerobicoradio);
        anaerobico = findViewById(R.id.anaerobicoradio);
        calendario = findViewById(R.id.calendariofragment);
        supino = findViewById(R.id.supino);
        levantamento = findViewById(R.id.levantamento);
        barra = findViewById(R.id.barrafixa);
        voador = findViewById(R.id.voador);
        abdominal = findViewById(R.id.abdominal);
        flexao = findViewById(R.id.flexao);
        repeticaosupino = findViewById(R.id.repeticaosupino);
        repeticaolevantamento = findViewById(R.id.repeticaolevantamento);
        repeticaobarra = findViewById(R.id.repeticaobarra);
        repeticaovoador = findViewById(R.id.repeticaovoador);
        repeticaoabdo = findViewById(R.id.repeticaoabdominal);
        repeticaoflexao = findViewById(R.id.repeticaoflexao);
        pesosupino = findViewById(R.id.pesosupino);
        pesolevantamento = findViewById(R.id.pesolevantamento);
        pesovoador = findViewById(R.id.pesovoador);
        pesobdo = findViewById(R.id.pesoabdominal);
        seriesupino = findViewById(R.id.seriesupino);
        serielevantamento = findViewById(R.id.serieslevantamento);
        seriebarra = findViewById(R.id.seriesbarra);
        serievoador = findViewById(R.id.serievoador);
        serieabdo = findViewById(R.id.seriesabdominal);
        serieflexao = findViewById(R.id.seriesflexao);
        agachamento = findViewById(R.id.agachamento);
        agachamentopeso = findViewById(R.id.agachamentopeso);
        corda = findViewById(R.id.corda);
        leg45 = findViewById(R.id.leg45);
        leg90 = findViewById(R.id.leg90);
        cordanaval = findViewById(R.id.cordanaval);
        repeticaoagacha = findViewById(R.id.repeticaosissy);
        repeticaoagachapeso = findViewById(R.id.repeticaoagachamentopeso);
        repeticaocorda = findViewById(R.id.repeticaocorda);
        repeticaoleg45 = findViewById(R.id.repeticaoleg45);
        repeticaoleg90 = findViewById(R.id.repeticaoleg90);
        repeticaocordanaval = findViewById(R.id.repeticaocordanaval);
        pesoagacha = findViewById(R.id.pesosissy);
        pesoagachapeso = findViewById(R.id.pesoagachamentopeso);
        pesoleg45 = findViewById(R.id.pesoleg45);
        pesoleg90 = findViewById(R.id.pesoleg90);
        serieagacha = findViewById(R.id.seriesissy);
        serieagachapeso = findViewById(R.id.seriesagachamentopeso);
        seriecorda = findViewById(R.id.seriescorda);
        serieleg45 = findViewById(R.id.serieleg45);
        serieleg90 = findViewById(R.id.seriesleg90);
        seriecordanaval = findViewById(R.id.seriescordanaval);

        inicializarFirebase();
        verificaParametro();

        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TreinoPrincipal.this, CalendarActivity.class);
                intent.putExtra("tela", 5);
                intent.putExtra("Objeto",aluno);
                startActivity(intent);
            }
        });
        Intent inconting = getIntent();
        String data = inconting.getStringExtra("datatreino");
        aluno = (Aluno)inconting.getSerializableExtra("Objeto");
        datatreino.setText(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void verificaParametro() {
        Intent intent1 = getIntent();

        if (intent1.getSerializableExtra("Objeto") == null) {
            aluno = new Aluno();
        } else {
            aluno = (Aluno) intent1.getSerializableExtra("Objeto");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.cadastrarmenu) {
            //datatreino.getText().toString().length() == 0 ||
            if ( indicedeo.getText().toString().length() == 0 || pressao.getText().toString().length() == 0) {
                Toast.makeText(TreinoPrincipal.this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            } else {
                dicas.setMid(UUID.randomUUID().toString());
                dicas.setData(datatreino.getText().toString());
                dicas.setIndicedeoxi(indicedeo.getText().toString());
                dicas.setPressao(pressao.getText().toString());
                dicas.setPesosupino(pesosupino.getText().toString());
                dicas.setPesoLevantamento(pesolevantamento.getText().toString());
                dicas.setPesovoador(pesovoador.getText().toString());
                dicas.setPesoabdominal(pesobdo.getText().toString());
                dicas.setRepeticoessupino(repeticaosupino.getText().toString());
                dicas.setRepeticoeslevantamento(repeticaolevantamento.getText().toString());
                dicas.setRepeticoesbarra(repeticaobarra.getText().toString());
                dicas.setRepeticoesvoador(repeticaovoador.getText().toString());
                dicas.setRepeticoesabdominal(repeticaoabdo.getText().toString());
                dicas.setRepeticoesflexao(repeticaoflexao.getText().toString());
                dicas.setSeriesupino(seriesupino.getText().toString());
                dicas.setSerielevantamento(serielevantamento.getText().toString());
                dicas.setSeriebarra(seriebarra.getText().toString());
                dicas.setSerievoador(serievoador.getText().toString());
                dicas.setSerieabdo(serieabdo.getText().toString());
                dicas.setSerieflexao(serieflexao.getText().toString());
                dicas.setPesoagachamentosissy(pesoagacha.getText().toString());
                dicas.setPesoagachamentopeso(pesoagachapeso.getText().toString());
                dicas.setPesoleg45(pesoleg45.getText().toString());
                dicas.setPesoleg90(pesoleg90.getText().toString());
                dicas.setRepeticoesagachamento(repeticaoagacha.getText().toString());
                dicas.setRepeticoesagachamentopeso(repeticaoagachapeso.getText().toString());
                dicas.setRepeticoescorda(repeticaocorda.getText().toString());
                dicas.setRepeticoes45(repeticaoleg45.getText().toString());
                dicas.setRepeticoesleg90(repeticaoleg90.getText().toString());
                dicas.setRepeticoescordanaval(repeticaocordanaval.getText().toString());
                dicas.setSerieagachamento(serieagacha.getText().toString());
                dicas.setSerieagachamentopeso(serieagachapeso.getText().toString());
                dicas.setSeriecorda(seriecorda.getText().toString());
                dicas.setSerie45(serieleg45.getText().toString());
                dicas.setSerie90(serieleg90.getText().toString());
                dicas.setSeriecordanaval(seriecordanaval.getText().toString());

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.aerobicoradio:
                        dicas.setStatusdotreino(aerobico.getText().toString());
                        break;

                    case R.id.anaerobicoradio:
                        dicas.setStatusdotreino(anaerobico.getText().toString());
                        break;

                }
                //Check1
                if(supino.isChecked()){
                    dicas.setSupino(supino.getText().toString());
                }
                if(levantamento.isChecked()){
                    dicas.setLevantamento(levantamento.getText().toString());
                }
                if(barra.isChecked()){
                    dicas.setBarrafixa(barra.getText().toString());
                }
                if(voador.isChecked()){
                    dicas.setVoador(voador.getText().toString());
                }
                if(abdominal.isChecked()){
                    dicas.setAbdominais(abdominal.getText().toString());
                }
                if(flexao.isChecked()){
                    dicas.setFlexao(flexao.getText().toString());
                }

                //Check2

                if(agachamento.isChecked()){
                    dicas.setAgachamento(agachamento.getText().toString());
                }
                if(agachamentopeso.isChecked()){
                    dicas.setAgachamentopeso(agachamentopeso.getText().toString());
                }
                if(corda.isChecked()){
                    dicas.setCorda(corda.getText().toString());
                }
                if(leg45.isChecked()){
                    dicas.setLegpress45(leg45.getText().toString());
                }
                if(leg90.isChecked()){
                    dicas.setLegpress90(leg90.getText().toString());
                }
                if(cordanaval.isChecked()){
                    dicas.setCordanaval(cordanaval.getText().toString());
                }

                databaseReference.child("Aluno").child(aluno.getMid().toString()).child("Treino").child(dicas.getMid().toString()).setValue(dicas);
                AlertDialog.Builder alerta = new AlertDialog.Builder(TreinoPrincipal.this);
                alerta.setTitle("Treino");
                alerta.setMessage("Treino registrado.");
                alerta.show();
                AbrirActivity();
            }
        }

        return true;

    }

    private void AbrirActivity() {
        Intent intent = new Intent(TreinoPrincipal.this, HistoricoDicas.class);
        intent.putExtra("Aluno", aluno);
        startActivity(intent);
    }


    private void inicializarFirebase () {
        FirebaseApp.initializeApp(TreinoPrincipal.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

}
