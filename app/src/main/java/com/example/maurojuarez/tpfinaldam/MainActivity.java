package com.example.maurojuarez.tpfinaldam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.maurojuarez.tpfinaldam.ActividadTab.ListaPlatosTabs;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAdmin, btnComensal;
    EditText etxCodigoAcceso;

    private final static String CODIGO_ACCESO = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxCodigoAcceso = (EditText) findViewById(R.id.etxCodigoAcceso);
        btnAdmin = (Button) findViewById(R.id.btnAdmin);
        btnComensal = (Button) findViewById(R.id.btnComensal);

        btnAdmin.setOnClickListener(this);
        btnComensal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdmin:
                comprobarCodigoAcceso();
                break;
            case R.id.btnComensal:
                Intent intentPlatos = new Intent(this, ListaPlatosTabs.class);
                startActivity(intentPlatos);
                break;
        }
    }

    /* Comprobar que la persona que intenta ingresar sepa el código de acceso */
    public void comprobarCodigoAcceso(){
        if (etxCodigoAcceso.getText().toString().equals(CODIGO_ACCESO)){
            Intent intentPedidos = new Intent(this, ListaPedidos.class);
            startActivity(intentPedidos);
        }
        else {
            etxCodigoAcceso.setText("");
        }
    }
}
