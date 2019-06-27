package com.example.eduar.tcc_personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;

import com.example.eduar.tcc_personal.Modelo.Dicas;
import com.example.eduar.tcc_personal.Treino.TreinoAlterar;
import com.example.eduar.tcc_personal.Treino.TreinoPrincipal;
import com.example.eduar.tcc_personal.Aluno.CadastroAluno;
import com.example.eduar.tcc_personal.Marcar.CadastrarTreino;
import com.example.eduar.tcc_personal.Modelo.Aluno;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";
    private CalendarView mcalendarView;
    Aluno aluno;
    Dicas dicas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
        aluno=new Aluno();
        dicas = new Dicas();
        Intent intent = getIntent();
        final Integer tela = intent.getIntExtra("tela",0);
        aluno=(Aluno)intent.getSerializableExtra("Objeto");

        mcalendarView = findViewById(R.id.calendarView);

        mcalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String data = dayOfMonth + "/" + String.format("%02d",(month + 1)) + "/" + year;
                Log.d(TAG, "SELECIONE A DATA:" + data);
                if(tela==1) {
                    Intent intent1 = new Intent(CalendarActivity.this, CadastroAluno.class);
                    intent1.putExtra("dataaluno", data);
                    startActivity(intent1);
                }else if(tela==2){
                    Intent intent = new Intent(CalendarActivity.this, CadastrarTreino.class);
                    intent.putExtra("data", data);
                    intent.putExtra("Objeto", aluno);
                    startActivity(intent);
                }else if(tela==3) {
                    Intent intent2 = new Intent(CalendarActivity.this, avaliacao.class);
                    intent2.putExtra("dataavaliacao", data);
                    intent2.putExtra("Objeto", aluno);
                    startActivity(intent2);
                }else if(tela==4) {
                    Intent intent3 = new Intent(CalendarActivity.this, cadastroEvolucao.class);
                    intent3.putExtra("dataevolucao", data);
                    intent3.putExtra("Objeto", aluno);
                    startActivity(intent3);
                }
                else if(tela==5) {
                    Intent intent4 = new Intent(CalendarActivity.this, TreinoPrincipal.class);
                    intent4.putExtra("datatreino", data);
                    intent4.putExtra("Objeto", aluno);
                    startActivity(intent4);
                }
//                else if(tela==6) {
//                    Intent intent5 = new Intent(CalendarActivity.this, TreinoAlterar.class);
//                    intent5.putExtra("datatreinoalterar", data);
//                    intent5.putExtra("Objeto", dicas);
//                    startActivity(intent5);
//                }
            }
        });
    }
}
