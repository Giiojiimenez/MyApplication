package com.tvaztecagioj.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Linstituciones extends AppCompatActivity {
    RecyclerView rv;
    Button btinsti;
    List<Fundaciones> Fundacion;

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linstituciones);

        rv=findViewById(R.id.recycler);
        btinsti=findViewById(R.id.btinstituciones);

        rv.setLayoutManager(new LinearLayoutManager(this));

        Fundacion=new ArrayList<>();

        adapter=new Adapter(Fundacion);
        rv.setAdapter(adapter);

        FirebaseDatabase database= FirebaseDatabase.getInstance();
         database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 Fundacion.removeAll(Fundacion);
                for(DataSnapshot snapshot:
                        dataSnapshot.getChildren()){
                    Fundaciones fundaciones=snapshot.getValue(Fundaciones.class);
                    Fundacion.add(fundaciones);
                }
                adapter.notifyDataSetChanged();
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });
    }
}
