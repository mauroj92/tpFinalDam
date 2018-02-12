package com.example.maurojuarez.tpfinaldam;

import android.icu.util.ULocale;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.maurojuarez.tpfinaldam.modelo.FirebaseReferences;
import com.example.maurojuarez.tpfinaldam.modelo.Plato;
import com.example.maurojuarez.tpfinaldam.modelo.TipoPlato;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AltaPlatos extends AppCompatActivity implements View.OnClickListener {
    private Spinner spTipo;
    private Button btnGuardar;
    private Button btnCancelar;
    private EditText etPrecio;
    private EditText etNombre;
    private EditText etDescripcion;
    private String bandera = "CREAR";
    private DatabaseReference refPlatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_plato_layout);

        //seteo variables de la vista
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        etNombre = (EditText) findViewById(R.id.etNombrePlato);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        spTipo = (Spinner) findViewById(R.id.spTipo);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, TipoPlato.PLATOS_MOCK);
        spTipo.setAdapter(dataAdapter);

        btnGuardar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        refPlatos = database.getReference(FirebaseReferences.PLATOS_REFERENCE);// busco la ref de la base de datos

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnGuardar:
                if(bandera.equals("CREAR")){//entonces estoy creando
                    Log.d("CREAR" , "esta creando");
                    Integer id = 18;
                    String nombre = etNombre.getText().toString();
                    String detalle = etDescripcion.getText().toString();
                    Double precio = Double.valueOf(etPrecio.getText().toString());
                    TipoPlato tipo  = (TipoPlato) spTipo.getSelectedItem();
                    Plato nuevo_plato = new Plato(id,nombre,detalle, tipo.getId(),precio);
                    refPlatos.push().setValue(nuevo_plato);
                }else{//modificando

                }
            break;
            case R.id.btnCancelar:
            break;
        }
    }
}
