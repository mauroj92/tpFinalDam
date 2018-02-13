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

public class TabBebidas extends Fragment{

    private static final String TAG = "TabPlatosFragment";
    private List<Plato> listaBebidas;
    private ListView listViewBebidas;
    private AdapterPlatos adaptardorBebidas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tap_bebidas,container,false);

        listaBebidas = new ArrayList<>();

        listViewBebidas = (ListView) view.findViewById(R.id.listaBebidas);
//        adaptardorBebidas = new AdapterPlatos(this, listaPostres);

        return view;
    }
}
