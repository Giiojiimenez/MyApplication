package com.tvaztecagioj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity implements View.OnClickListener {

     EditText etnombre,etapellido,etcontraseñar,etdireccion,etedad,ettelefono;
     Button btregistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etnombre=findViewById(R.id.etnombre);
        etapellido=findViewById(R.id.etapellido);
        etcontraseñar=findViewById(R.id.etcontraseñar);
        etdireccion=findViewById(R.id.etdireccion);
        etedad=findViewById(R.id.etedad);
        ettelefono=findViewById(R.id.ettelefono);

        btregistrar.setOnClickListener(this);
    }
    private void registrar(String nombre,String contraseña){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(nombre,contraseña);
    }

    @Override
    public void onClick(View view) {
        Intent intent =new Intent(Registro.this,Linstituciones.class);
        startActivity(intent);
    }
}
