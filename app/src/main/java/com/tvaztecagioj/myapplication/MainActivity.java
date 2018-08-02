package com.tvaztecagioj.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity{

    EditText etusuario ,etcontraseña,etnombre;
    Button btentrar;
    TextView tvregistrate;
    int direc=0;

    FirebaseAuth mAuth;
    FirebaseUser updateUI;

    FirebaseDatabase database= FirebaseDatabase.getInstance();
    ProgressDialog progressDialog;


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
                MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(MainActivity.this)
                        .setTitle("¿Seguro de registrarse?")
                        .setDescription("El usuario va realizar un registro. Recuerda llenar bien los campos de nombre y password.")
                        .setHeaderColor(R.color.yellow_sys)
                        .setIcon(R.drawable.warning)
                        .withIconAnimation(true)
                        .withDialogAnimation(true)
                        //Generar evento de boton positivo
                        .setPositiveText("Aceptar")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                if(etusuario.getText().toString().trim().equals("")){
                                    Toast.makeText(getApplicationContext(),"Correo sin datos",Toast.LENGTH_LONG).show();
                                }else if(etcontraseña.getText().toString().trim().equals("")){
                                    Toast.makeText(getApplicationContext(),"Contraseña sin datos",Toast.LENGTH_LONG).show();
                                }else {
                                    String nombrereg = etusuario.getText().toString().trim();
                                    String contraseñareg = etcontraseña.getText().toString().trim();
                                    registrar(nombrereg, contraseñareg);
                                       }
                                dialog.dismiss();

                            }
                        })
                        .setCancelable(false)
                        //Boton negativo
                        .setNegativeText("Cancelar")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                //Cierra el dialogo
                                dialog.dismiss();
                            }
                        })
                        .build();
                dialog.show();
            }
        });


        btentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog=new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Validando Datos");
                progressDialog.setMessage("Espere");
                progressDialog.setCancelable(false);
                progressDialog.show();
                if(etusuario.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Correo sin datos",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }else if(etcontraseña.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Contraseña sin datos",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
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
                            GrabarSesion(user.getEmail());
                            verRegistro(user.getEmail());
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


public void verRegistro(final String correo){

    database.getReference().child("usuarios").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for(DataSnapshot snapshot:
                    dataSnapshot.getChildren()){
                Usuario usu=snapshot.getValue(Usuario.class);
                if(usu.getCorreo()!=null) {
                    if (usu.getCorreo().equals(correo)) {
                        direc=1;
                        break;
                    }
                }
            }
            if(direc==1){
                Intent intent = new Intent(MainActivity.this, Linstituciones.class);
                progressDialog.dismiss();
                startActivity(intent);
                finish();
            }else {
                Intent intent = new Intent(MainActivity.this, Registro.class);
                progressDialog.dismiss();
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

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

    public void GrabarSesion(String a) {
        SharedPreferences Sesion = getSharedPreferences("SesionUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = Sesion.edit();
        editor.putString("correo", a);
        editor.commit();
    }

}
