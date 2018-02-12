package com.example.maurojuarez.tpfinaldam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.maurojuarez.tpfinaldam.adaptadores.AdapterPedido;
import com.example.maurojuarez.tpfinaldam.modelo.Pedido;

import java.util.ArrayList;

public class ListaPedidos extends AppCompatActivity implements View.OnClickListener {
    Button btnNuevoPlato;
    ListView lvwPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);

        btnNuevoPlato = (Button) findViewById(R.id.btnNuevoPlato);
        btnNuevoPlato.setOnClickListener(this);

        lvwPedidos = (ListView) findViewById(R.id.lvwPedidos);

        ArrayList<Pedido> pedidos = new ArrayList<>();

        //AdapterPedido adapterPedido = new AdapterPedido(getApplicationContext(), Array.asList());
        //lvwPedidos.setAdapter(adapterPedido);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNuevoPlato:
                //Ir a la actividade de alta de plato
                Intent intentPlato = new Intent(this, AltaPlatos.class);
                startActivity(intentPlato);
                break;
        }
    }
}
