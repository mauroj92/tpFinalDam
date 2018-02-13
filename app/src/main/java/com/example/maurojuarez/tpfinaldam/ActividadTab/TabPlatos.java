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

public class TabPlatos extends Fragment {
    private static final String TAG = "TabPlatosFragment";
    private List<Plato> listaPlatos;
    private ListView listViewPlatos;
    private AdapterPlatos adaptardorPlatos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tap_platos,container,false);

        listaPlatos = new ArrayList<>();

        listViewPlatos = (ListView) view.findViewById(R.id.listaPlatos);
//        adaptardorPostres = new AdapterPlatos(this, listaPostres);

        return view;
    }
}
