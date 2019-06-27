package com.example.eduar.tcc_personal.Treino;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.eduar.tcc_personal.Activitys.HistoricoDicas;
import com.example.eduar.tcc_personal.CalendarActivity;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.Modelo.Dicas;
import com.example.eduar.tcc_personal.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TreinoAlterar extends AppCompatActivity {

    private static final String TAG = "treinoalterar";

    private EditText datatreino;
    private EditText indicedeo;
    private EditText pressao;
    private RadioGroup radioGroup;
    private RadioButton aerobico;
    private RadioButton anaerobico;
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
        setContentView(R.layout.activity_treino_alterar);

        dicas = new Dicas();
        aluno = new Aluno();

        datatreino = findViewById(R.id.datatreinoalterar);
        indicedeo = findViewById(R.id.indiceoxigenioalterar);
        pressao = findViewById(R.id.pressaoalterar);
        radioGroup = findViewById(R.id.radiogroupalterar);
        aerobico = findViewById(R.id.aerobicoradioalterar);
        anaerobico = findViewById(R.id.anaerobicoradioalterar);
        calendario = findViewById(R.id.calendariofragmentalterar);
        supino = findViewById(R.id.supinoalterar);
        levantamento = findViewById(R.id.levantamentoalterar);
        barra = findViewById(R.id.barrafixaalterar);
        voador = findViewById(R.id.voadoralterar);
        abdominal = findViewById(R.id.abdominalalterar);
        flexao = findViewById(R.id.flexaoalterar);
        repeticaosupino = findViewById(R.id.repeticaosupinoalterar);
        repeticaolevantamento = findViewById(R.id.repeticaolevantamentoalterar);
        repeticaobarra = findViewById(R.id.repeticaobarraalterar);
        repeticaovoador = findViewById(R.id.repeticaovoadoralterar);
        repeticaoabdo = findViewById(R.id.repeticaoabdominalalterar);
        repeticaoflexao = findViewById(R.id.repeticaoflexaoalterar);
        pesosupino = findViewById(R.id.pesosupinoalterar);
        pesolevantamento = findViewById(R.id.pesolevantamentoalterar);
        pesovoador = findViewById(R.id.pesovoadoralterar);
        pesobdo = findViewById(R.id.pesoabdominalalterar);
        seriesupino = findViewById(R.id.seriesupinoalterar);
        serielevantamento = findViewById(R.id.serieslevantamentoalterar);
        seriebarra = findViewById(R.id.seriesbarraalterar);
        serievoador = findViewById(R.id.serievoadoralterar);
        serieabdo = findViewById(R.id.seriesabdominalalterar);
        serieflexao = findViewById(R.id.seriesflexaoalterar);
        agachamento = findViewById(R.id.agachamentoalterar);
        agachamentopeso = findViewById(R.id.agachamentopesoalterar);
        corda = findViewById(R.id.cordaalterar);
        leg45 = findViewById(R.id.leg45alterar);
        leg90 = findViewById(R.id.leg90alterar);
        cordanaval = findViewById(R.id.cordanavalalterar);
        repeticaoagacha = findViewById(R.id.repeticaosissyalterar);
        repeticaoagachapeso = findViewById(R.id.repeticaoagachamentopesoalterar);
        repeticaocorda = findViewById(R.id.repeticaocordaalterar);
        repeticaoleg45 = findViewById(R.id.repeticaoleg45alterar);
        repeticaoleg90 = findViewById(R.id.repeticaoleg90alterar);
        repeticaocordanaval = findViewById(R.id.repeticaocordanavalalterar);
        pesoagacha = findViewById(R.id.pesosissyalterar);
        pesoagachapeso = findViewById(R.id.pesoagachamentopesoalterar);
        pesoleg45 = findViewById(R.id.pesoleg45alterar);
        pesoleg90 = findViewById(R.id.pesoleg90alterar);
        serieagacha = findViewById(R.id.seriesissyalterar);
        serieagachapeso = findViewById(R.id.seriesagachamentopesoalterar);
        seriecorda = findViewById(R.id.seriescordaalterar);
        serieleg45 = findViewById(R.id.serieleg45alterar);
        serieleg90 = findViewById(R.id.seriesleg90alterar);
        seriecordanaval = findViewById(R.id.seriescordanavalalterar);

        inicializarFirebase();
        verificaParametro();

//        calendario.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(TreinoAlterar.this, CalendarActivity.class);
//                intent.putExtra("tela", 6);
//                intent.putExtra("Objeto",dicas);
//                startActivity(intent);
//            }
//        });
//        Intent inconting = getIntent();
//        String data = inconting.getStringExtra("datatreinoalterar");
//        dicas = (Dicas)inconting.getSerializableExtra("Objeto");
//        datatreino.setText(data);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.alterar_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void verificaParametro() {
        Intent intent = getIntent();
        if (intent.getSerializableExtra("Objeto") == null) {
            dicas = new Dicas();
        } else {
            dicas = (Dicas) intent.getSerializableExtra("Objeto");
            if (intent.getSerializableExtra("ObjetoAluno") == null) {
                aluno = new Aluno();
            } else {
                aluno = (Aluno) intent.getSerializableExtra("ObjetoAluno");
                mostrartreino();
            }

        }
    }

    private void mostrartreino() {
        if(dicas.getData() != null) {
            datatreino.setText(dicas.getData().toString());
        }
        if(dicas.getIndicedeoxi() != null) {
            indicedeo.setText(dicas.getIndicedeoxi().toString());
        }
        if(dicas.getPressao() != null) {
            pressao.setText(dicas.getPressao().toString());
        }
        //RADIO BUTTON
        if(dicas.getStatusdotreino()!=null) {
            if (dicas.getStatusdotreino().equals("Aeróbico")) {
                aerobico.setChecked(true);
            } else {
                anaerobico.setChecked(true);
            }
        }

        //um

        if(dicas.getSeriesupino()!= null) {
            seriesupino.setText(dicas.getSeriesupino().toString());
        }
        if(dicas.getRepeticoessupino() != null) {
            repeticaosupino.setText(dicas.getRepeticoessupino().toString());
        }
        if(dicas.getPesosupino() != null) {
            pesosupino.setText(dicas.getPesosupino().toString());
        }
        if(dicas.getSerielevantamento() != null) {
            serielevantamento.setText(dicas.getSerielevantamento().toString());
        }
        if(dicas.getRepeticoeslevantamento() != null) {
            repeticaolevantamento.setText(dicas.getRepeticoeslevantamento().toString());
        }
        if(dicas.getPesoLevantamento() != null) {
            pesolevantamento.setText(dicas.getPesoLevantamento().toString());
        }
        if(dicas.getSeriebarra() != null) {
            seriebarra.setText(dicas.getSeriebarra().toString());
        }
        if(dicas.getRepeticoesbarra() != null) {
            repeticaobarra.setText(dicas.getRepeticoesbarra().toString());
        }
        if(dicas.getSerievoador() != null) {
            serievoador.setText(dicas.getSerievoador().toString());
        }
        if(dicas.getRepeticoesvoador() != null) {
            repeticaovoador.setText(dicas.getRepeticoesvoador().toString());
        }
        if(dicas.getPesovoador() != null) {
            pesovoador.setText(dicas.getPesovoador().toString());
        }
        if(dicas.getSerieabdo() != null) {
            serieabdo.setText(dicas.getSerieabdo().toString());
        }
        if(dicas.getRepeticoesabdominal() != null) {
            repeticaoabdo.setText(dicas.getRepeticoesabdominal().toString());
        }
        if(dicas.getPesoabdominal() != null) {
            pesobdo.setText(dicas.getPesoabdominal().toString());
        }
        if(dicas.getSerieflexao() != null) {
            serieflexao.setText(dicas.getSerieflexao().toString());
        }
        if(dicas.getRepeticoesflexao() != null) {
            repeticaoflexao.setText(dicas.getRepeticoesflexao().toString());
        }
        //CHECKBOX
        if(dicas.getSupino()!=null) {
            if (dicas.getSupino().equals("Supino")) {
                supino.setChecked(true);
            }
        }
        if(dicas.getLevantamento()!=null) {
            if (dicas.getLevantamento().equals("Levantamento")) {
                levantamento.setChecked(true);
            }
        }
        if(dicas.getBarrafixa()!=null) {
            if (dicas.getBarrafixa().equals("Barra")) {
                barra.setChecked(true);
            }
        }
        if(dicas.getVoador()!=null) {
            if (dicas.getVoador().equals("Voador")) {
                voador.setChecked(true);
            }
        }
        if(dicas.getAbdominais()!=null) {
            if (dicas.getAbdominais().equals("Abdominal")) {
                abdominal.setChecked(true);
            }
        }
        if(dicas.getFlexao()!=null) {
            if (dicas.getFlexao().equals("Flexão")) {
                flexao.setChecked(true);
            }
        }

        //Dois

        if(dicas.getSerieagachamento() != null) {
            serieagacha.setText(dicas.getSerieagachamento().toString());
        }
        if(dicas.getSerieagachamentopeso() != null) {
            serieagachapeso.setText(dicas.getSerieagachamentopeso().toString());
        }
        if(dicas.getSeriecorda() != null) {
            seriecorda.setText(dicas.getSeriecorda().toString());
        }
        if(dicas.getSerie45() != null) {
            serieleg45.setText(dicas.getSerie45().toString());
        }
        if(dicas.getSerie90() != null) {
            serieleg90.setText(dicas.getSerie90().toString());
        }
        if(dicas.getSeriecordanaval() != null) {
            seriecordanaval.setText(dicas.getSeriecordanaval().toString());
        }
        if(dicas.getRepeticoesagachamento() != null) {
            repeticaoagacha.setText(dicas.getRepeticoesagachamento().toString());
        }
        if(dicas.getRepeticoesagachamentopeso() != null) {
            repeticaoagachapeso.setText(dicas.getRepeticoesagachamentopeso().toString());
        }
        if(dicas.getRepeticoescorda() != null) {
            repeticaocorda.setText(dicas.getRepeticoescorda().toString());
        }
        if(dicas.getRepeticoes45() != null) {
            repeticaoleg45.setText(dicas.getRepeticoes45().toString());
        }
        if(dicas.getRepeticoesleg90() != null) {
            repeticaoleg90.setText(dicas.getRepeticoesleg90().toString());
        }
        if(dicas.getRepeticoescordanaval() != null) {
            repeticaocordanaval.setText(dicas.getRepeticoescordanaval().toString());
        }
        if(dicas.getPesoagachamentosissy() != null) {
            pesoagacha.setText(dicas.getPesoagachamentosissy().toString());
        }
        if(dicas.getPesoagachamentopeso() != null) {
            pesoagachapeso.setText(dicas.getPesoagachamentopeso().toString());
        }
        if(dicas.getPesoleg45() != null) {
            pesoleg45.setText(dicas.getPesoleg45().toString());
        }
        if(dicas.getPesoleg90() != null) {
            pesoleg90.setText(dicas.getPesoleg90().toString());
        }
        //CHECK BOX
        if(dicas.getAgachamento()!=null) {
            if (dicas.getAgachamento().equals("Agachamento Sissy")) {
                agachamento.setChecked(true);
            }
        }
        if(dicas.getAgachamentopeso()!=null){
            if (dicas.getAgachamentopeso().equals("Agachamento Peso")) {
                agachamentopeso.setChecked(true);
            }
        }
        if(dicas.getCorda()!=null) {
            if (dicas.getCorda().equals("Corda")) {
                corda.setChecked(true);
            }
        }
        if(dicas.getLegpress45()!=null) {
            if (dicas.getLegpress45().equals("Leg Press 45")) {
                leg45.setChecked(true);
            }
        }
        if(dicas.getLegpress90()!=null) {
            if (dicas.getLegpress90().equals("Leg Press 90")) {
                leg90.setChecked(true);
            }
        }
        if(dicas.getCordanaval()!=null) {
            if (dicas.getCordanaval().equals("Corda Naval")) {
                cordanaval.setChecked(true);
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itemalterar) {
            Dicas d = new Dicas();
            d.setMid(dicas.getMid());
            d.setData(datatreino.getText().toString().trim());
            d.setIndicedeoxi(indicedeo.getText().toString().trim());
            d.setPressao(pressao.getText().toString().trim());
            d.setSeriesupino(seriesupino.getText().toString().trim());
            d.setSerielevantamento(serielevantamento.getText().toString().trim());
            d.setSeriebarra(seriebarra.getText().toString().trim());
            d.setSerievoador(serievoador.getText().toString().trim());
            d.setSerieabdo(serieabdo.getText().toString().trim());
            d.setSerieflexao(serieflexao.getText().toString().trim());
            d.setPesosupino(pesosupino.getText().toString().trim());
            d.setPesoLevantamento(pesolevantamento.getText().toString().trim());
            d.setPesovoador(pesovoador.getText().toString().trim());
            d.setPesoabdominal(pesobdo.getText().toString().trim());
            d.setRepeticoessupino(repeticaosupino.getText().toString().trim());
            d.setRepeticoeslevantamento(repeticaolevantamento.getText().toString().trim());
            d.setRepeticoesbarra(repeticaobarra.getText().toString().trim());
            d.setRepeticoesvoador(repeticaovoador.getText().toString().trim());
            d.setRepeticoesabdominal(repeticaoabdo.getText().toString().trim());
            d.setRepeticoesflexao(repeticaoflexao.getText().toString().trim());
            d.setSerieagachamento(serieagacha.getText().toString().trim());
            d.setSerieagachamentopeso(serieagachapeso.getText().toString().trim());
            d.setSeriecorda(seriecorda.getText().toString().trim());
            d.setSerie45(serieleg45.getText().toString().trim());
            d.setSerie90(serieleg90.getText().toString().trim());
            d.setSeriecordanaval(seriecordanaval.getText().toString().trim());
            d.setPesoagachamentosissy(pesoagacha.getText().toString().trim());
            d.setPesoagachamentopeso(pesoagachapeso.getText().toString().trim());
            d.setPesoleg45(pesoleg45.getText().toString().trim());
            d.setPesoleg90(pesoleg90.getText().toString().trim());
            d.setRepeticoesagachamento(repeticaoagacha.getText().toString().trim());
            d.setRepeticoesagachamentopeso(repeticaoagachapeso.getText().toString().trim());
            d.setRepeticoescorda(repeticaocorda.getText().toString().trim());
            d.setRepeticoes45(repeticaoleg45.getText().toString().trim());
            d.setRepeticoesleg90(repeticaoleg90.getText().toString().trim());
            d.setRepeticoescordanaval(repeticaocordanaval.getText().toString().trim());

            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.aerobicoradioalterar:
                    d.setStatusdotreino(aerobico.getText().toString().trim());
                    break;

                case R.id.anaerobicoradioalterar:
                    d.setStatusdotreino(anaerobico.getText().toString().trim());
                    break;
            }

            //chek1

            if(supino.isChecked()){
                d.setSupino(supino.getText().toString().trim());
            }
            if(levantamento.isChecked()){
                d.setLevantamento(levantamento.getText().toString().trim());
            }
            if(barra.isChecked()){
                d.setBarrafixa(barra.getText().toString().trim());
            }
            if(voador.isChecked()){
                d.setVoador(voador.getText().toString().trim());
            }
            if(abdominal.isChecked()){
                d.setAbdominais(abdominal.getText().toString().trim());
            }
            if(flexao.isChecked()){
                d.setFlexao(flexao.getText().toString().trim());
            }

            //Check2

            if(agachamento.isChecked()){
                d.setAgachamento(agachamento.getText().toString().trim());
            }
            if(agachamentopeso.isChecked()){
                d.setAgachamentopeso(agachamentopeso.getText().toString().trim());
            }
            if(corda.isChecked()){
                d.setCorda(corda.getText().toString().trim());
            }
            if(leg45.isChecked()){
                d.setLegpress45(leg45.getText().toString().trim());
            }
            if(leg90.isChecked()){
                d.setLegpress90(leg90.getText().toString().trim());
            }
            if(cordanaval.isChecked()){
                d.setCordanaval(cordanaval.getText().toString().trim());
            }

            databaseReference.child("Aluno").child(aluno.getMid().toString()).child("Treino").child(dicas.getMid().toString()).setValue(d);
            AlertDialog.Builder alerta = new AlertDialog.Builder(TreinoAlterar.this);
            alerta.setTitle("Alterar Informações");
            alerta.setMessage("Informações alteradas.");
            alerta.show();
            AbrirActivity();
        }
        return true;
    }

    private void AbrirActivity() {
        Intent intent = new Intent(TreinoAlterar.this, HistoricoDicas.class);
        intent.putExtra("Aluno", aluno);
        startActivity(intent);
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(TreinoAlterar.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}
