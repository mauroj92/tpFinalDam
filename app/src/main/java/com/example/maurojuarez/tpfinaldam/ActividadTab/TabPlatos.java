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
import android.widget.TextView;

import com.example.maurojuarez.tpfinaldam.R;
import com.example.maurojuarez.tpfinaldam.adaptadores.AdapterPlatos;
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

public class TabPlatos extends Fragment {
    private static final String TAG = "TabPlatosFragment";
    private List<Plato> listaPlatos;
    private ListView listViewPlatos;
    private AdapterPlatos adaptardorPlatos;
    private TextView tv;

    private DatabaseReference refPlatos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tap_platos,container,false);

        listaPlatos = new ArrayList<>();

        listViewPlatos = (ListView) view.findViewById(R.id.listaPlatos);


        adaptardorPlatos = new AdapterPlatos(getActivity().getApplicationContext(), listaPlatos);
        listViewPlatos.setAdapter(adaptardorPlatos);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        refPlatos = database.getReference(FirebaseReferences.PLATOS_REFERENCE);// busco la ref de la base de datos
        refPlatos.orderByChild("tipo").equalTo(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                    Plato unPlato = snapShot.getValue(Plato.class);
                    Log.d("datos_plato" , unPlato.toString());
                    listaPlatos.add(unPlato);
                }

                adaptardorPlatos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }
}
