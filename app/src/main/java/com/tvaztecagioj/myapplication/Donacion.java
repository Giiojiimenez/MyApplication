package com.tvaztecagioj.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class Donacion extends AppCompatActivity {

    @BindView(R.id.etNomFun)
    EditText etNomFun;
    @BindView(R.id.etMailDon)
    EditText etMailDon;
    @BindView(R.id.etProduc)
    EditText etProduc;
    @BindView(R.id.etCantidadDon)
    EditText etCantidadDon;
    @BindView(R.id.btregistrarDon)
    Button btregistrarDon;

    String correo;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donacion);

        ButterKnife.bind(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        CargarSesion();
        btregistrarDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(Donacion.this)
                        .setTitle("¿Seguro de registrar una donación?")
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
                                if(etNomFun.getText().toString().trim().equals("")){
                                    Toast.makeText(getApplicationContext(),"Campo Nombre sin datos",Toast.LENGTH_LONG).show();
                                }else if(etMailDon.getText().toString().trim().equals("")){
                                    Toast.makeText(getApplicationContext(),"Campo Mail sin datos",Toast.LENGTH_LONG).show();
                                }else if(etProduc.getText().toString().trim().equals("")){
                                    Toast.makeText(getApplicationContext(),"Campo Producto sin datos",Toast.LENGTH_LONG).show();
                                }else if(etCantidadDon.getText().toString().trim().equals("")){
                                    Toast.makeText(getApplicationContext(),"Campo cantidad sin datos",Toast.LENGTH_LONG).show();
                                }else {
                                    writeNewPost();
                                    Toast.makeText(getApplicationContext(), "Donacion Agregada Correctamente", Toast.LENGTH_LONG).show();
                                    etNomFun.setText("");
                                    etMailDon.setText("");
                                    etProduc.setText("");
                                    etCantidadDon.setText("");

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

    private void writeNewPost() {

        String key = mDatabase.child("donaciones").push().getKey();
        DonacionFire post = new DonacionFire(etNomFun.getText().toString().trim(), etMailDon.getText().toString().trim(), etProduc.getText().toString().trim(),
                Integer.parseInt(etCantidadDon.getText().toString().trim()),correo);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/donaciones/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }

    public void CargarSesion(){
        SharedPreferences Sesion=getSharedPreferences("SesionUsuario", Context.MODE_PRIVATE);
        correo=Sesion.getString("correo","").toString();
    }

}
