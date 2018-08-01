package com.tvaztecagioj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Linstituciones extends AppCompatActivity {
    RecyclerView rv;
    Button btinsti,btinsfunda;

    List<FundacionFire> Fundacion12;

    Adapter adapter;

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
}
