package com.tvaztecagioj.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Recycle_donaciones extends AppCompatActivity {

    RecyclerView rv;

    List<DonacionFire> dona;
    String correo;
    AdapterDonacion adapter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_donaciones);

        rv=findViewById(R.id.recycledona);
        rv.setLayoutManager(new LinearLayoutManager(this));

        progressDialog=new ProgressDialog(Recycle_donaciones.this);
        progressDialog.setTitle("Validando Datos");
        progressDialog.setMessage("Espere");
        progressDialog.setCancelable(false);
        progressDialog.show();


        dona=new ArrayList<>();
        CargarSesion();
        adapter=new AdapterDonacion(dona);

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        database.getReference().child("donaciones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dona.removeAll(dona);
                for(DataSnapshot snapshot:
                        dataSnapshot.getChildren()){
                    DonacionFire d=snapshot.getValue(DonacionFire.class);
                    if(d.getCorreo()!=null) {
                        if (d.getCorreo().equals(correo)){
                            dona.add(d);
                        }
                    }
                }
                if(adapter.getItemCount()==0){
                    progressDialog.dismiss();
                    MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(Recycle_donaciones.this)
                            .setTitle("El usuario no tiene donaciones")
                            .setDescription("Para visualizar sus donaciones primero debe realizar una como minimo.")
                            .setHeaderColor(R.color.red_sys)
                            .setIcon(R.drawable.warning)
                            .withIconAnimation(true)
                            .withDialogAnimation(true)
                            .setPositiveText("Regresar")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    onBackPressed();
                                }
                            })
                            .setCancelable(false)
                            .build();
                    dialog.show();
                }
                progressDialog.dismiss();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        rv.setAdapter(adapter);



    }

    public void CargarSesion(){
        SharedPreferences Sesion=getSharedPreferences("SesionUsuario", Context.MODE_PRIVATE);
        correo=Sesion.getString("correo","").toString();
    }
}
