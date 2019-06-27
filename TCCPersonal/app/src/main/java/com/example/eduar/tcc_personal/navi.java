package com.example.eduar.tcc_personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eduar.tcc_personal.Detalhes.DetalheTreino;
import com.example.eduar.tcc_personal.Listas.ListarHorarios;
import com.example.eduar.tcc_personal.Listas.listar_avalia;
import com.example.eduar.tcc_personal.Listas.listar_dicas;
import com.example.eduar.tcc_personal.Listas.listar_evolu;
import com.example.eduar.tcc_personal.Listas.testelistar;
import com.example.eduar.tcc_personal.Modelo.Aluno;
import com.example.eduar.tcc_personal.Modelo.Personal;
import com.example.eduar.tcc_personal.Personal.AlterarInfo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class navi extends AppCompatActivity
        implements Observer<Personal>, NavigationView.OnNavigationItemSelectedListener, AdapterView.OnClickListener {
    private ImageView aliasCadastroaluno, aliastreino, aliasevolu, aliasavalia;
    Personal personal;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<Personal> personalList=new ArrayList<Personal>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);
        personal = new Personal();

        aliasCadastroaluno = (ImageView)findViewById(R.id.cadastroAluno);
        aliastreino = (ImageView)findViewById(R.id.treino);
        aliasevolu = (ImageView)findViewById(R.id.evolucao);
        aliasavalia = (ImageView)findViewById(R.id.avaliacao);

        aliasCadastroaluno.setOnClickListener(this);
        aliastreino.setOnClickListener(this);
        aliasavalia.setOnClickListener(this);
        aliasevolu.setOnClickListener(this);

        inicializarFirebase();


        verificaParametro();


                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void verificaParametro() {
        Intent intent = getIntent();
        if (intent.getStringExtra("id") == null) {
            personal = new Personal();
        } else {
            Query query;
            String idx = intent.getStringExtra("id");
            query = databaseReference.child("Personal").child(idx).orderByChild("mId").startAt(idx).endAt(idx+"\uf8ff");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    personalList.clear();
                    for(DataSnapshot objsnapshot:dataSnapshot.getChildren()){
                        Personal p = objsnapshot.getValue(Personal.class);
                        personalList.add(p);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.marcarotreino){
            Intent intent = new Intent(this, ListarHorarios.class);
            startActivity(intent);
        }


        if (id == R.id.alterarinfo) {

            Intent intent = new Intent(this, AlterarInfo.class);
            startActivity(intent);

            // Handle the camera action
        } else if (id == R.id.sair) {

            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(this, Login.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cadastroAluno: {

                Intent intent = new Intent(this, testelistar.class);
                startActivity(intent);
                break;

            }
            case R.id.evolucao: {

                Intent intent = new Intent(this, listar_evolu.class);
                startActivity(intent);
                break;

            }
            case R.id.avaliacao: {

                Intent intent = new Intent(this, listar_avalia.class);
                startActivity(intent);
                break;

            }
            case R.id.treino: {

                Intent intent = new Intent(this, listar_dicas.class);
                startActivity(intent);
                break;
            }
        }


    }
    private void inicializarFirebase(){
        FirebaseApp.initializeApp(navi.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    @Override
    public void onEvent(Personal event) {
        Personal p = event;
        Toast.makeText(this, ""+p.toString(), Toast.LENGTH_SHORT).show();
    }
}


