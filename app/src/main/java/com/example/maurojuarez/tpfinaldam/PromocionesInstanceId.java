package com.example.maurojuarez.tpfinaldam;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Mauro Juarez on 13 feb 2018.
 */

public class PromocionesInstanceId extends FirebaseInstanceIdService {
    public static final String TAG_TOKEN = "Token";
    public PromocionesInstanceId() {}

    @Override
    public  void onTokenRefresh(){
        // obtiene el token que lo identifica
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG_TOKEN, "Refreshed token"+refreshedToken);
        guardarToken(refreshedToken);
    }

    private void guardarToken(String tkn) {
        //guardarlo en un archivo
        // o en el servidor con un POST asociando un
        // nombre de usuario ficticio y hardcoded
    }
}
