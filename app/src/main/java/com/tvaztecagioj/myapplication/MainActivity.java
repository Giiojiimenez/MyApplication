package com.tvaztecagioj.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etusuario ,etcontraseña,etnombre;
    Button btentrar;
    TextView tvregistrate;

    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etusuario=findViewById(R.id.etusuario);
        etnombre=findViewById(R.id.etnombre);
        etcontraseña=findViewById(R.id.etcontraseña);
        btentrar=findViewById(R.id.btentrar);
        tvregistrate=findViewById(R.id.tvregistrate);

        tvregistrate.setOnClickListener(this);
        btentrar.setOnClickListener(this);

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user =firebaseAuth.getCurrentUser();
                if(user!=null){
                    Log.i("SESION","sesion iniciada con email: "+user.getEmail());
                }else{
                    Log.i("SESSION","session cerrada");
                }
            }
        };
    }
    
    private void iniciarSesion(String nombre,String contraseña){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(nombre,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.i("SESSION","SESSION INICIADA CORRECTAMENTE");
                }else{
                    Log.e("SESSION",task.getException().getMessage()+"");
                }
            }
        });

    }
    private void registrar(String nombre,String contraseña){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(nombre,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.i("SESSION","USUARIO CREADO CORRECTAMENTE");
                }else{
                Log.e("SESSION",task.getException().getMessage()+"");
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btentrar:
                String nombre =etusuario.getText().toString();
                String contraseña=etcontraseña.getText().toString();
                iniciarSesion(nombre,contraseña);
                Intent intent =new Intent(MainActivity.this,Registro.class);
                startActivity(intent);
                break;

            case R.id.tvregistrate:

                String nombrereg =etusuario.getText().toString();
                String contraseñareg=etcontraseña.getText().toString();
                registrar(nombrereg,contraseñareg);

                break;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
    }
}
