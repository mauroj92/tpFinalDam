package com.example.maurojuarez.tpfinaldam;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.maurojuarez.tpfinaldam.modelo.FirebaseReferences;
import com.example.maurojuarez.tpfinaldam.modelo.Pedido;
import com.example.maurojuarez.tpfinaldam.modelo.Plato;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

public class AltaPedido extends AppCompatActivity implements View.OnClickListener  {
    private static final int PERMISSION_REQUEST_RECORD_AUDIO = 3 ;
    private static final String TAG= "tagpedido";

    private MediaRecorder recorder = null; //seteo en null el recorder
    private MediaPlayer reproductor = null;
    private File fichero; //archivo donde va a ser guardado el audio
    private String filename = "audio_temporal.3gp";
    private Boolean grabando = false; //dependiendo el estado de esta variable, el boton graba o detiene la grabacion
    private Boolean grabado = false;
    private ArrayList<Plato> listPlatos;
    private ArrayList<Integer> listaEnteros;

    private StorageReference mStorage; // referencia de storage
    private DatabaseReference refPedidos;

    private TextView tvDettallesPedido , tvPrecioTotal;
    private Button btnGuardar, btnCancelar , btnGrabar, btnReproducir;
    private EditText etNombre, etDni;
    private String idFirebase;

    private Intent intentOrigen;

    Calendar calendar = Calendar.getInstance(Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_pedido);


        btnGrabar = (Button) findViewById(R.id.btnGrabarAudio);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnReproducir = (Button) findViewById(R.id.btnReproducir);

        btnGrabar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        btnReproducir.setOnClickListener(this);


        etNombre = (EditText) findViewById(R.id.etNombre);
        etDni = (EditText) findViewById(R.id.etDni);
        tvDettallesPedido = (TextView) findViewById(R.id.tvDetallesPedido);
        tvPrecioTotal = (TextView) findViewById(R.id.tvPrecioTotal);
        btnGuardar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        btnGrabar.setOnClickListener(this);
        btnReproducir.setOnClickListener(this);
        btnReproducir.setEnabled(false);//siempre comienza en falso

        intentOrigen = getIntent();
        listPlatos = intentOrigen.getParcelableArrayListExtra("platos_con_cantidad");
        String string_detalles = "";
        double precio_final = 0;
        listaEnteros = new ArrayList<>();
        for (Plato p: listPlatos){
            string_detalles += p.getNombre() + " x" + p.getCantidad() + "\n";
            precio_final += (p.getCantidad() * p.getPrecio());
            listaEnteros.add(p.getId());
        }
        tvDettallesPedido.setText(string_detalles);
        tvPrecioTotal.setText("$" + String.valueOf(precio_final));

        mStorage = FirebaseStorage.getInstance().getReference();

        // Habilitar o no audio
        File directory = getApplicationContext().getDir("audios", Context.MODE_PRIVATE);
        if(!directory.exists())
            directory.mkdir();
        fichero = new File(directory, filename);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        refPedidos = database.getReference(FirebaseReferences.PEDIDOS_REFERENCE);// busco la ref de la base de datos
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnGrabarAudio:
                //Chequear si tengo el permiso para usar la cámara
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED) {
                    //Si no los tenia, entonces pedirlos
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.RECORD_AUDIO},
                            PERMISSION_REQUEST_RECORD_AUDIO );

                }
                else { //si tenia permiso me fijo si no estaba grabando para empezar a grabar
                    if (grabando){
                        pararAudio();
                    }else{
                        grabarAudio();
                    }
                }
                break;

            case R.id.btnCancelar:
                fichero.delete();
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.btnGuardar:


                Date currentTime = Calendar.getInstance().getTime();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String hora = String.valueOf(hour) + ":" +  String.valueOf(minute) + ":"  + String.valueOf(second);

                String nombre =  etNombre.getText().toString();
                String dni = etDni.getText().toString();
                Integer mesa = 10;
                idFirebase = refPedidos.push().getKey();//ESTA ES LA KEY QUE VA A GENERAR
                Pedido pedido = new Pedido(0,nombre, dni , mesa , hora , listaEnteros);
                refPedidos.child(idFirebase).setValue(pedido);
                if(grabado){
                    uploadAudio(); //finish de la actividad esta en el succes de upload
                }
                fichero.delete();//borro siempre el audio

                //setResult(RESULT_OK);
                //finish();
                Intent intentExito =  new Intent(getApplicationContext(), ExitoPedido.class);
                startActivity(intentExito);

                break;
            case R.id.btnReproducir:
                reproducir();
                break;

        }
    }


    private void uploadAudio(){
        Log.d("id_firebase" , idFirebase);
        StorageReference filepath = mStorage.child("audios").child(idFirebase+ ".3gp");
        Uri uri = Uri.fromFile(fichero);
        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Intent intentExito =  new Intent(getApplicationContext(), ExitoPedido.class);
                startActivity(intentExito);
            }
        });
    }

    private void grabarAudio() {
        grabando=true;
        btnGrabar.setText("PARAR");
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fichero.getAbsolutePath());
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
        } catch (IOException e) {//hubo error, no grabó
            Log.e(TAG, "prepare() failed");
            grabando=false;
        }
        recorder.start();
    }

    private void pararAudio(){
        grabando= false;
        grabado = true;//tengo un archivo para guardar en la nueve
        btnGrabar.setText("GRABAR AUDIO");
        recorder.stop();
        recorder.release();
        recorder = null;
        btnReproducir.setEnabled(true);
    }

    private void reproducir() {

        reproductor = new MediaPlayer();

        try {

            reproductor.setDataSource(String.valueOf(fichero));

            reproductor.prepare();

            reproductor.start();

        } catch (IOException e) {

            Log.e(TAG, "Fallo en reproducción");

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_RECORD_AUDIO:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {//true => tengo el permiso
                    grabarAudio();
                } else {//no tengo el permiso para grabar audio
                    //Toast.makeText(FormReclamo.this, "Pidio audio y rechazo", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

}
