package com.example.maurojuarez.tpfinaldam.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Nicolas on 12/2/2018.
 */

public class Pedido implements Parcelable{
    private Integer id;
    private String nombre;
    private String dni;
    private Integer mesa;
    private String hora;
    private List<Integer> platos; //lita con ids

    public Pedido(Integer id, String nombre, String dni, List<Integer> platos) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.platos = platos;
    }

    protected Pedido(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        dni = in.readString();
        in.readList(platos,null);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nombre);
        parcel.writeString(dni);
        parcel.writeList(platos);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", platos=" + platos +
                '}';
    }


    public static final Creator<Pedido> CREATOR = new Creator<Pedido>() {
        @Override
        public Pedido createFromParcel(Parcel in) {
            return new Pedido(in);
        }

        @Override
        public Pedido[] newArray(int size) {
            return new Pedido[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Integer> getPlatos() {
        return platos;
    }

    public void setPlatos(List<Integer> platos) {
        this.platos = platos;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
