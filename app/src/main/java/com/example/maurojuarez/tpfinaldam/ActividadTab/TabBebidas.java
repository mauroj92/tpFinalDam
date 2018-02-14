package com.example.maurojuarez.tpfinaldam.ActividadTab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.maurojuarez.tpfinaldam.R;
import com.example.maurojuarez.tpfinaldam.adaptadores.AdapterBebidas;

import com.example.maurojuarez.tpfinaldam.modelo.FirebaseReferences;
import com.example.maurojuarez.tpfinaldam.modelo.Plato;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas on 13/2/2018.
 */

public class TabBebidas extends Fragment{

    private static final String TAG = "TabPlatosFragment";
    private List<Plato> listaBebidas;
    private ListView listViewBebidas;
    private AdapterBebidas adaptardorBebidas;
    private DatabaseReference refPlatos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tap_bebidas,container,false);

        listaBebidas = new ArrayList<>();

        listViewBebidas = (ListView) view.findViewById(R.id.listaBebidas);

        adaptardorBebidas = new AdapterBebidas(getActivity().getApplicationContext(), listaBebidas);
        listViewBebidas.setAdapter(adaptardorBebidas);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        refPlatos = database.getReference(FirebaseReferences.PLATOS_REFERENCE);// busco la ref de la base de datos
        refPlatos.orderByChild("tipo").equalTo(2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                    Plato unPlato = snapShot.getValue(Plato.class);
                    Log.d("datos_bebida" , unPlato.toString());
                    listaBebidas.add(unPlato);
                }

                adaptardorBebidas.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    public List<Plato> getListaBebidas(){
        return listaBebidas;
    }
}
