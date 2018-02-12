package com.example.maurojuarez.tpfinaldam.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas on 12/2/2018.
 */

public class TipoPlato implements Parcelable {
    private Integer id;
    private String descripcion;


    public TipoPlato(Integer id, String desc){
        this.id = id;
        this.descripcion = desc;
    }

    protected TipoPlato(Parcel in) {
        id = in.readInt();
        descripcion = in.readString();
    }

    public static final Parcelable.Creator<TipoPlato> CREATOR = new Parcelable.Creator<TipoPlato>() {
        @Override
        public TipoPlato createFromParcel(Parcel in) {
            return new TipoPlato(in);
        }

        @Override
        public TipoPlato[] newArray(int size) {
            return new TipoPlato[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public static final TipoPlato[] PLATOS_MOCK= new TipoPlato[]{
            new TipoPlato(1,"Plato"),
            new TipoPlato(2,"Bebida"),
            new TipoPlato(3,"Postre")
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(descripcion);

    }

    @Override
    public String toString() {
        return getDescripcion();
    }
}
