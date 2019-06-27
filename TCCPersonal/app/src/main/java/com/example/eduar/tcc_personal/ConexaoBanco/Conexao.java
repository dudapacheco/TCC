package com.example.eduar.tcc_personal.ConexaoBanco;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Conexao {

    private static FirebaseAuth firebaseAuth;
    private static FirebaseAuth.AuthStateListener authStateListener;
    private static FirebaseUser firebaseUser;
    public static DatabaseReference referenciaFirebase;

    private Conexao(){

    }
    public static DatabaseReference getFirebase() {

        if(referenciaFirebase == null){
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();

        }
        return referenciaFirebase;

    }


    public static FirebaseAuth getFirebaseAuth(){

        if(firebaseAuth == null){
            inicializarFirebaseAuth();
        }
        return firebaseAuth;
    }

    private static void inicializarFirebaseAuth(){
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    firebaseUser = user;
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public static FirebaseUser getFirebaseUser(){
        return firebaseUser;
    }

    public static void logOut(){
        firebaseAuth.signOut();
    }
}
