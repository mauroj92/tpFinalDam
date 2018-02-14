package com.example.maurojuarez.tpfinaldam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ExitoPedido extends AppCompatActivity {
    TextView tvwCronometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exito_pedido);

        tvwCronometro = (TextView) findViewById(R.id.tvwCronometro);

        Cronometro cronometro = new Cronometro("Nombre del cronómetro", tvwCronometro);
        new Thread(cronometro).start();
    }
}
