package com.example.maurojuarez.tpfinaldam.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.maurojuarez.tpfinaldam.ActividadTab.TabPostres;
import com.example.maurojuarez.tpfinaldam.R;
import com.example.maurojuarez.tpfinaldam.modelo.Pedido;
import com.example.maurojuarez.tpfinaldam.modelo.Plato;

import java.util.List;

/**
 * Created by Nicolas on 13/2/2018.
 */

public class AdapterPlatos extends ArrayAdapter<Plato> {
    LayoutInflater inflater;
    List<Plato> platos;


    public AdapterPlatos(@NonNull Context context, List<Plato> platos) {
        super(context, R.layout.row_plato, platos);
        inflater = LayoutInflater.from(context);
        this.platos = platos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        if (row == null){
            row = inflater.inflate(R.layout.row_plato, parent, false);
        }

        ViewHolder holder = (ViewHolder) row.getTag();

        if (holder == null) {
            holder = new ViewHolder(row);
            row.setTag(holder);
        }

        holder.nombre.setText(this.getItem(position).getNombre());
        holder.cantidad.setText("$0");
        holder.precio.setText(this.getItem(position).getPrecio().toString());
        holder.detalles.setText(this.getItem(position).getDetalle());

        return row ;
    }

    class ViewHolder {
        TextView nombre;
        TextView cantidad;
        TextView precio;
        TextView detalles;
        TextView btnMas;
        TextView btnMenos;


        public ViewHolder(View base) {
            this.nombre = (TextView) base.findViewById(R.id.tvNombre);
            this.cantidad = (TextView) base.findViewById(R.id.tvCantidad);
            this.detalles = (TextView) base.findViewById(R.id.tvDetalle);
            this.precio = (TextView) base.findViewById(R.id.tvPrecio);
            this.btnMas= (Button) base.findViewById(R.id.btnMas);
            this.btnMenos= (Button) base.findViewById(R.id.btnMenos);
        }
    }
}