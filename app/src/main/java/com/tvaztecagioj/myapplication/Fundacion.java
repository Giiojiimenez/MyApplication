package com.tvaztecagioj.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fundacion extends AppCompatActivity {

    @BindView(R.id.etNombreFun)
    EditText etNombreFun;
    @BindView(R.id.etCalle)
    EditText etCalle;
    @BindView(R.id.etColonia)
    EditText etColonia;
    @BindView(R.id.etMunicipio)
    EditText etMunicipio;
    @BindView(R.id.etPostal)
    EditText etPostal;
    @BindView(R.id.etPhoneFun)
    EditText etPhoneFun;
    @BindView(R.id.etCorreoEle)
    EditText etCorreoEle;
    @BindView(R.id.etCat)
    EditText etCat;
    @BindView(R.id.etNece01)
    EditText etNece01;
    @BindView(R.id.etNece02)
    EditText etNece02;
    @BindView(R.id.etNece03)
    EditText etNece03;
    @BindView(R.id.etNece04)
    EditText etNece04;
    @BindView(R.id.etNece05)
    EditText etNece05;
    @BindView(R.id.btenviarFun)
    Button btenviarFun;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundacion);

        ButterKnife.bind(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btenviarFun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNewPost();
                if(etNombreFun.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Campo Nombre sin datos",Toast.LENGTH_LONG).show();
                }else if(etCalle.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Campo Calle sin datos",Toast.LENGTH_LONG).show();
                }else if(etColonia.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Campo Colonia sin datos",Toast.LENGTH_LONG).show();
                }else if(etPostal.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Campo Codigo Postal sin datos",Toast.LENGTH_LONG).show();
                }else if(etPhoneFun.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Campo Telefono sin datos",Toast.LENGTH_LONG).show();
                }else if(etCorreoEle.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Campo Correo sin datos",Toast.LENGTH_LONG).show();
                }else if(etCat.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Campo Categoria sin datos",Toast.LENGTH_LONG).show();
                }else if(etNece01.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Campo Necesidad 01 sin datos",Toast.LENGTH_LONG).show();
                }else if(etNece02.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Campo Necesidad 02 sin datos",Toast.LENGTH_LONG).show();
                }else {


                Toast.makeText(getApplicationContext(),"Fundacion12 Agregada Correctamente",Toast.LENGTH_LONG).show();
                etNombreFun.setText("");
                etCalle.setText("");
                etColonia.setText("");
                etMunicipio.setText("");
                etPostal.setText("");
                etPhoneFun.setText("");
                etCorreoEle.setText("");
                etCat.setText("");
                etNece01.setText("");
                etNece02.setText("");
                etNece03.setText("");
                etNece04.setText("");
                etNece05.setText("");
                }
            }
        });


    }

    private void writeNewPost() {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("fundaciones").push().getKey();
        FundacionFire post = new FundacionFire(etNombreFun.getText().toString().trim(), etCalle.getText().toString().trim(), etColonia.getText().toString().trim(),
                etMunicipio.getText().toString().trim(), etPostal.getText().toString().trim(), etPhoneFun.getText().toString().trim(),
                etCorreoEle.getText().toString().trim(), etCat.getText().toString().trim(), etNece01.getText().toString().trim(),
                etNece02.getText().toString().trim(), etNece03.getText().toString().trim(), etNece04.getText().toString().trim(),etNece05.getText().toString().trim()
                );
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/fundaciones/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }
}
