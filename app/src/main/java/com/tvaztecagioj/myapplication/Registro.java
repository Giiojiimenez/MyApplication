package com.tvaztecagioj.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity{

     EditText etnombre,etapellido,etcontraseñar,etdireccion,etedad,ettelefono;
     Button btregistrar,btromitir;

     private DatabaseReference mDatabase;

    FirebaseUser updateUI;

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
        btregistrar=findViewById(R.id.btregistrar);
        btromitir=findViewById(R.id.btromitir);

        updateUI=FirebaseAuth.getInstance().getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        btromitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(getApplicationContext(),Linstituciones.class);
                startActivity(a);
                finish();
            }
        });

        btregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(Registro.this)
                        .setTitle("¿Seguro de registrar un usuario?")
                        .setDescription("El usuario va realizar un registro.")
                        .setHeaderColor(R.color.yellow_sys)
                        .setIcon(R.drawable.warning)
                        .withIconAnimation(true)
                        .withDialogAnimation(true)
                        //Generar evento de boton positivo
                        .setPositiveText("Aceptar")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                if(etnombre.getText().toString().trim().equals("")){
                                    Toast.makeText(getApplicationContext(),"Campo Nombre sin datos",Toast.LENGTH_LONG).show();
                                }else if(etapellido.getText().toString().trim().equals("")){
                                    Toast.makeText(getApplicationContext(),"Campo Apellido sin datos",Toast.LENGTH_LONG).show();
                                }else if(etcontraseñar.getText().toString().trim().equals("")){
                                    Toast.makeText(getApplicationContext(),"Campo Contraseña sin datos",Toast.LENGTH_LONG).show();
                                }else if(etdireccion.getText().toString().trim().equals("")){
                                    Toast.makeText(getApplicationContext(),"Campo Direccion sin datos",Toast.LENGTH_LONG).show();
                                }else if(etedad.getText().toString().trim().equals("")){
                                    Toast.makeText(getApplicationContext(),"Campo Edad sin datos",Toast.LENGTH_LONG).show();
                                }else if(ettelefono.getText().toString().trim().equals("")){
                                    Toast.makeText(getApplicationContext(),"Campo Telefono sin datos",Toast.LENGTH_LONG).show();
                                }else {
                                    writeNewPost(updateUI.getEmail());
                                    etnombre.setText("");
                                    etapellido.setText("");
                                    etcontraseñar.setText("");
                                    etdireccion.setText("");
                                    etedad.setText("");
                                    ettelefono.setText("");
                                    Intent a=new Intent(getApplicationContext(),Linstituciones.class);
                                    startActivity(a);
                                    finish();
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
    }

    private void writeNewPost(String a) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("usuarios").push().getKey();
        Usuario post = new Usuario(etnombre.getText().toString().trim(), etapellido.getText().toString().trim(), etcontraseñar.getText().toString().trim(),
                etdireccion.getText().toString().trim(),ettelefono.getText().toString().trim(),Integer.parseInt(etedad.getText().toString().trim()),a);
        Map<String, Object> postValues = post.toMap(a);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/usuarios/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }

}
