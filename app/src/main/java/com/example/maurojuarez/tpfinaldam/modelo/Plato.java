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
    private String descripcion;
    private Integer tipo;
    private Double precio;

    public Plato(Integer id, String nombre, String descripcion, Integer tipo, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;

        this.tipo = tipo;
        this.precio = precio;
    }

    public Plato(Parcel in){
        id = in.readInt();
        nombre = in.readString();
        descripcion = in.readString();
        tipo = in.readInt();
        precio = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nombre);
        parcel.writeString(descripcion);
        parcel.writeInt(tipo);
        parcel.writeDouble(precio);

    }

    @Override
    public String toString() {
        return "Plato{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +

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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }


    public Integer getTipo() {
        return tipo;
    }

    public Double getPrecio() {
        return precio;
    }
}
