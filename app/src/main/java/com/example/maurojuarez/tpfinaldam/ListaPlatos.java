package com.example.maurojuarez.tpfinaldam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.maurojuarez.tpfinaldam.modelo.FirebaseReferences;
import com.example.maurojuarez.tpfinaldam.modelo.Plato;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ListaPlatos extends AppCompatActivity {

    private DatabaseReference refPlatos;
    private List<Plato> listaPlatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_plato);

        //declarar adapter

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        refPlatos = database.getReference(FirebaseReferences.PLATOS_REFERENCE);// busco la ref de la base de datos
        refPlatos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                    Plato unPlato = snapShot.getValue(Plato.class);
                    listaPlatos.add(unPlato);
                }

            //adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
