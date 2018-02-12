package com.example.maurojuarez.tpfinaldam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListaPedidos extends AppCompatActivity implements View.OnClickListener {
    Button btnNuevoPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);

        btnNuevoPlato = (Button) findViewById(R.id.btnNuevoPlato);
        btnNuevoPlato.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNuevoPlato:
                Intent intentPlato = new Intent(this, AltaPlatos.class);
                startActivity(intentPlato);
                break;
        }
    }
}
