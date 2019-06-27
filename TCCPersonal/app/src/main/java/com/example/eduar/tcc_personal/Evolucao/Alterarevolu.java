package com.example.eduar.tcc_personal.Evolucao;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.eduar.tcc_personal.Activitys.HistoricoEvolucao;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.Modelo.Evolucao;
import com.example.eduar.tcc_personal.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Alterarevolu extends AppCompatActivity {

    private EditText dataaltera, pesoaltera, cinturaaltera, quadrilaltera, abdomenaltera, bicepsdaltera, bicepsealtera, coxadaltera, coxaealtera, peitoaltera, subaltera;
    Aluno aluno;
    Evolucao evolucao;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterarevolu);

        dataaltera = findViewById(R.id.dataalterarevolu);
        pesoaltera = findViewById(R.id.pesoaltera);
        cinturaaltera = findViewById(R.id.cinturaaltera);
        quadrilaltera = findViewById(R.id.quadrilaltera);
        abdomenaltera = findViewById(R.id.abdomenaltera);
        bicepsdaltera = findViewById(R.id.bicepsdaltera);
        bicepsealtera = findViewById(R.id.bicepsealtera);
        coxadaltera = findViewById(R.id.coxadaltera);
        coxaealtera = findViewById(R.id.coxaealtera);
        peitoaltera = findViewById(R.id.peitoaltera);
        subaltera = findViewById(R.id.subesaltera);

        aluno = new Aluno();
        evolucao = new Evolucao();

        inicializarFirebase();
        verificaParametro();
    }

    private void verificaParametro() {

        Intent intent = getIntent();
        if (intent.getSerializableExtra("Objeto") == null) {
            evolucao = new Evolucao();
        } else {
            evolucao = (Evolucao) intent.getSerializableExtra("Objeto");
            if (intent.getSerializableExtra("Aluno") == null) {
                aluno = new Aluno();
            } else {
                aluno = (Aluno) intent.getSerializableExtra("Aluno");
                mostrarevolucao();
            }

        }
    }

    private void mostrarevolucao() {
        dataaltera.setText(evolucao.getData().toString());
        pesoaltera.setText(evolucao.getPeso().toString());
        cinturaaltera.setText(evolucao.getCintura().toString());
        quadrilaltera.setText(evolucao.getQuadril().toString());
        abdomenaltera.setText(evolucao.getAbdomen().toString());
        bicepsdaltera.setText(evolucao.getBicepsd().toString());
        bicepsealtera.setText(evolucao.getBicepse().toString());
        coxadaltera.setText(evolucao.getCoxad().toString());
        coxaealtera.setText(evolucao.getCoxae().toString());
        peitoaltera.setText(evolucao.getPeito().toString());
        subaltera.setText(evolucao.getSubescapular().toString());
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
            Evolucao e = new Evolucao();
            e.setMid(evolucao.getMid());
            e.setData(dataaltera.getText().toString().trim());
            e.setPeso(Double.parseDouble(pesoaltera.getText().toString().trim()));
            e.setCintura(Double.parseDouble(cinturaaltera.getText().toString().trim()));
            e.setQuadril(Double.parseDouble(quadrilaltera.getText().toString().trim()));
            e.setAbdomen(Double.parseDouble(abdomenaltera.getText().toString().trim()));
            e.setBicepsd(Double.parseDouble(bicepsdaltera.getText().toString().trim()));
            e.setBicepse(Double.parseDouble(bicepsealtera.getText().toString().trim()));
            e.setCoxad(Double.parseDouble(coxadaltera.getText().toString().trim()));
            e.setCoxae(Double.parseDouble(coxaealtera.getText().toString().trim()));
            e.setPeito(Double.parseDouble(peitoaltera.getText().toString().trim()));
            e.setSubescapular(Double.parseDouble(subaltera.getText().toString().trim()));

            databaseReference.child("Aluno").child(aluno.getMid().toString()).child("Evolucao").child(evolucao.getMid().toString()).setValue(e);
            AlertDialog.Builder alerta = new AlertDialog.Builder(Alterarevolu.this);
            alerta.setTitle("Alterar Informações");
            alerta.setMessage("Informações alteradas.");
            alerta.show();
            AbrirActivity();
        }
        return true;
    }

    private void AbrirActivity() {
        Intent intent = new Intent(Alterarevolu.this, HistoricoEvolucao.class);
        intent.putExtra("Aluno", aluno);
        startActivity(intent);
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(Alterarevolu.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

}
