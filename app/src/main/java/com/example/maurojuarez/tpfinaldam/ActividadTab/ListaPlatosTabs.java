package com.example.maurojuarez.tpfinaldam.ActividadTab;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.maurojuarez.tpfinaldam.AltaPedido;
import com.example.maurojuarez.tpfinaldam.R;
import com.example.maurojuarez.tpfinaldam.modelo.Plato;

import java.util.ArrayList;
import java.util.List;

public class ListaPlatosTabs extends AppCompatActivity {
    private static final int REQUEST_ALTA_PEDIDO= 1;
    private TabPlatos tabp;
    private TabPostres tabPostres;
    private TabBebidas tabBebidas;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos_tabs);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);

        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Plato> conCantidad = new ArrayList<>();
                List<Plato> platos = tabp.getListaPlatos();
                for (Plato p: platos){
                    if(p.getCantidad() > 0){
                        conCantidad.add(p);
                    }
                }
                List<Plato> postres = tabPostres.getListaPostres();
                for (Plato p: postres){
                    if(p.getCantidad() > 0){
                        conCantidad.add(p);
                    }
                }
                List<Plato> bebidas = tabBebidas.getListaBebidas();
                for (Plato p: bebidas){
                    if(p.getCantidad() > 0){
                        conCantidad.add(p);
                    }
                }

                Intent intent = new Intent(ListaPlatosTabs.this, AltaPedido.class);
                intent.putExtra("platos_con_cantidad" , conCantidad);
                startActivityForResult(intent, REQUEST_ALTA_PEDIDO);
            }
        });

    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        tabp = new TabPlatos();
        adapter.addFragment(tabp ,"Platos");
        tabBebidas = new TabBebidas();
        adapter.addFragment(tabBebidas , "Bebidas");
        tabPostres = new TabPostres();
        adapter.addFragment(tabPostres , "Postres");
        viewPager.setAdapter(adapter);
    }


}
