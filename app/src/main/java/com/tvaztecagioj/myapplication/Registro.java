package com.tvaztecagioj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity{

     EditText etnombre,etapellido,etcontraseñar,etdireccion,etedad,ettelefono;
     Button btregistrar,btromitir;

     private DatabaseReference mDatabase;

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

        mDatabase = FirebaseDatabase.getInstance().getReference();

        btromitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(getApplicationContext(),Linstituciones.class);
                startActivity(a);
            }
        });

        btregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    writeNewPost();
                    etnombre.setText("");
                    etapellido.setText("");
                    etcontraseñar.setText("");
                    etdireccion.setText("");
                    etedad.setText("");
                    ettelefono.setText("");
                    Intent a=new Intent(getApplicationContext(),Linstituciones.class);
                    startActivity(a);
                }
            }
        });
    }

    private void writeNewPost() {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("usuarios").push().getKey();
        Usuario post = new Usuario(etnombre.getText().toString().trim(), etapellido.getText().toString().trim(), etcontraseñar.getText().toString().trim(),
                etdireccion.getText().toString().trim(),ettelefono.getText().toString().trim(),Integer.parseInt(etedad.getText().toString().trim()));
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/usuarios/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }

}
