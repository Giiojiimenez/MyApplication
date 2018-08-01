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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity{

    EditText etusuario ,etcontraseña,etnombre;
    Button btentrar;
    TextView tvregistrate;

    FirebaseAuth mAuth;
    FirebaseUser updateUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        etusuario=findViewById(R.id.etusuario);
        etnombre=findViewById(R.id.etnombre);
        etcontraseña=findViewById(R.id.etcontraseña);
        btentrar=findViewById(R.id.btentrar);
        tvregistrate=findViewById(R.id.tvregistrate);

        tvregistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etusuario.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Correo sin datos",Toast.LENGTH_LONG).show();
                }else if(etcontraseña.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Contraseña sin datos",Toast.LENGTH_LONG).show();
                }else {
                    String nombrereg = etusuario.getText().toString().trim();
                    String contraseñareg = etcontraseña.getText().toString().trim();
                    registrar(nombrereg, contraseñareg);
                }
            }
        });
        btentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etusuario.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Correo sin datos",Toast.LENGTH_LONG).show();
                }else if(etcontraseña.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Contraseña sin datos",Toast.LENGTH_LONG).show();
                }else {
                    String nombre = etusuario.getText().toString().trim();
                    String contraseña = etcontraseña.getText().toString().trim();
                    iniciarSesion(nombre, contraseña);
                }
            }
        });

    }

    private void iniciarSesion(String nombre,String contraseña){
        mAuth.signInWithEmailAndPassword(nombre, contraseña)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Toast.makeText(getApplicationContext(),"Inicio Correctamente",Toast.LENGTH_LONG).show();
                            Intent intent =new Intent(MainActivity.this,Registro.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(),"Credenciales erroneas",Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }

                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        updateUI=user;
    }

    private void registrar(String nombre,String contraseña){
        mAuth.createUserWithEmailAndPassword(nombre, contraseña)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Usuario Creado Correctamente",Toast.LENGTH_LONG).show();
                            etusuario.setText("");
                            etcontraseña.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(),"Error al crear el Usuario",Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
    }
}
