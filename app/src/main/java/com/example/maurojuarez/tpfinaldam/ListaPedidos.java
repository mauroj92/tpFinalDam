package com.example.maurojuarez.tpfinaldam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maurojuarez.tpfinaldam.adaptadores.AdapterPedido;
import com.example.maurojuarez.tpfinaldam.modelo.FirebaseReferences;
import com.example.maurojuarez.tpfinaldam.modelo.Pedido;
import com.example.maurojuarez.tpfinaldam.modelo.Plato;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaPedidos extends AppCompatActivity implements View.OnClickListener {
    final static int REQUEST_ALTA_PLATO = 1;
    private Button btnNuevoPlato;
    private ListView lvwPedidos;
    private List<Pedido> listaPedidos;
    private AdapterPedido adapterPedido;
    private DatabaseReference refPedidos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);

        btnNuevoPlato = (Button) findViewById(R.id.btnNuevoPlato);
        btnNuevoPlato.setOnClickListener(this);

        lvwPedidos = (ListView) findViewById(R.id.lvwPedidos);

        listaPedidos = new ArrayList<>();

        adapterPedido = new AdapterPedido(getApplicationContext(), listaPedidos);
        lvwPedidos.setAdapter(adapterPedido);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        refPedidos = database.getReference(FirebaseReferences.PEDIDOS_REFERENCE);// busco la ref de la base de datos
        refPedidos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapShot: dataSnapshot.getChildren()) {
                    Pedido unPedido = snapShot.getValue(Pedido.class);
                    listaPedidos.add(unPedido);
                }

                adapterPedido.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNuevoPlato:
                //Ir a la actividade de alta de plato
                Intent intentPlato = new Intent(this, AltaPlatos.class);
                startActivityForResult(intentPlato, REQUEST_ALTA_PLATO);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_ALTA_PLATO){
            switch (resultCode) {
                case RESULT_CANCELED:

                    break;
                case RESULT_OK:
                    Toast.makeText(ListaPedidos.this, "El plato fue creado exitosamente", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}
