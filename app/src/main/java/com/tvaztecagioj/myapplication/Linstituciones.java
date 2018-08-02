package com.tvaztecagioj.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Linstituciones extends AppCompatActivity {
    RecyclerView rv;
    Button btinsti,btinsfunda,btDonaciones;

    List<FundacionFire> Fundacion12;

    Adapter adapter;

    @Override
    public void onBackPressed() {
        MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(Linstituciones.this)
                .setTitle("¿Seguro desea cerrar sesión?")
                .setDescription("Al aceptar la sesión se va cerrar.")
                .setHeaderColor(R.color.red_sys)
                .setIcon(R.drawable.warning)
                .withIconAnimation(true)
                .withDialogAnimation(true)
                .setPositiveText("Salir")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        CerrarSesion();
                        finish();
                        }
                    })
                .setCancelable(false)
                .setNegativeText("Cancelar")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linstituciones);

        rv=findViewById(R.id.recycler);
        btinsti=findViewById(R.id.btinstituciones);
        btinsfunda=findViewById(R.id.btinsfunda);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Fundacion12 =new ArrayList<>();

        adapter=new Adapter(Fundacion12);
        rv.setAdapter(adapter);

        btDonaciones=findViewById(R.id.btDonaciones);

        btDonaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(getApplicationContext(),Recycle_donaciones.class);
                startActivity(a);
            }
        });

        btinsti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a =new Intent(getApplicationContext(),Donacion.class);
                startActivity(a);
            }
        });
        btinsfunda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a =new Intent(getApplicationContext(),Fundacion.class);
                startActivity(a);
            }
        });

        FirebaseDatabase database= FirebaseDatabase.getInstance();
         database.getReference().child("fundaciones").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 Fundacion12.removeAll(Fundacion12);
                for(DataSnapshot snapshot:
                        dataSnapshot.getChildren()){
                    FundacionFire fundaciones=snapshot.getValue(FundacionFire.class);
                    Fundacion12.add(fundaciones);
                }
                adapter.notifyDataSetChanged();
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });
    }

    public void CerrarSesion(){
        SharedPreferences Sesion =getSharedPreferences("SesionUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=Sesion.edit();
        editor.remove("correo");
        editor.apply();
    }

}
