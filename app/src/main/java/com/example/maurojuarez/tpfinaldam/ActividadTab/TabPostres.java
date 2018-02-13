package com.example.maurojuarez.tpfinaldam.ActividadTab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.maurojuarez.tpfinaldam.R;
import com.example.maurojuarez.tpfinaldam.adaptadores.AdapterPlatos;
import com.example.maurojuarez.tpfinaldam.modelo.Plato;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas on 13/2/2018.
 */

public class TabPostres extends Fragment {
    private static final String TAG = "TabPlatosFragment";
    private List<Plato> listaPostres;
    private ListView listViewPostres;
    private AdapterPlatos adaptardorPostres;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tap_postres,container,false);


        listaPostres = new ArrayList<>();

        listViewPostres = (ListView) view.findViewById(R.id.listaPostres);
//        adaptardorPostres = new AdapterPlatos(this, listaPostres);



        return view;
    }
}
