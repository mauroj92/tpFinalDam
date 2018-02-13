package com.example.maurojuarez.tpfinaldam.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Nicolas on 12/2/2018.
 */

public class Plato implements Parcelable {
    private Integer id;
    private String nombre;
    private String detalle;
    private Integer tipo;
    private Double precio;
    private Integer cantidad;

    public Plato(){

    }

    public Plato(Integer id, String nombre, String detalle, Integer tipo, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.detalle = detalle;
        this.tipo = tipo;
        this.precio = precio;
    }

    public Plato(Parcel in){
        id = in.readInt();
        nombre = in.readString();
        detalle = in.readString();
        tipo = in.readInt();
        precio = in.readDouble();
    }

    public static final Creator<Plato> CREATOR = new Creator<Plato>() {
        @Override
        public Plato createFromParcel(Parcel in) {
            return new Plato(in);
        }

        @Override
        public Plato[] newArray(int size) {
            return new Plato[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nombre);
        parcel.writeString(detalle);
        parcel.writeInt(tipo);
        parcel.writeDouble(precio);

    }

    @Override
    public String toString() {
        return "Plato{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", detalle='" + detalle + '\'' +
                ", tipo=" + tipo +
                ", precio=" + precio +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }


    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDetalle() {
        return detalle;
    }


    public Integer getTipo() {
        return tipo;
    }

    public Double getPrecio() {
        return precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
