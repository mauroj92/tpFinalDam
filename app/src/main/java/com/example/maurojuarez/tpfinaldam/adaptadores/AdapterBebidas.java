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

import com.example.maurojuarez.tpfinaldam.R;
import com.example.maurojuarez.tpfinaldam.modelo.Plato;

import java.util.List;

/**
 * Created by Nicolas on 13/2/2018.
 */

public class AdapterBebidas extends ArrayAdapter<Plato> {
    LayoutInflater inflater;
    List<Plato> platos;


    public AdapterBebidas(@NonNull Context context, List<Plato> platos) {
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
        final ViewHolder finalHolder = holder;

        if (holder == null) {
            holder = new ViewHolder(row);

            row.setTag(holder);

            Button.OnClickListener onClickListener_mas = new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //Obtiene la posición donde se clikeó el botón
                    Integer posicionClick = (Integer) view.getTag(R.integer.posicion_plato);
                    //Se aumenta en 1 la cantidad
                    Integer cantidad = (Integer) view.getTag(R.integer.cantidad_plato) + 1;
                    getItem(posicionClick).setCantidad(cantidad);
                    notifyDataSetChanged();
                }
            };

            Button.OnClickListener onClickListener_menos = new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //Obtiene la posición donde se clikeó el botón
                    Integer posicionClick = (Integer) view.getTag(R.integer.posicion_plato);
                    //Se resta en 1 la cantidad
                    Integer cantidad = (Integer) view.getTag(R.integer.cantidad_plato);
                    if (cantidad > 0) {
                        getItem(posicionClick).setCantidad(cantidad - 1);
                        notifyDataSetChanged();
                    }

                }
            };

            holder.btnMenos.setOnClickListener(onClickListener_menos);
            holder.btnMas.setOnClickListener(onClickListener_mas);
        }


        Integer cantidad = this.getItem(position).getCantidad();
        holder.nombre.setText(this.getItem(position).getNombre());
        holder.cantidad.setText(String.valueOf(cantidad));
        holder.precio.setText("$: " + this.getItem(position).getPrecio().toString());
        holder.detalles.setText(this.getItem(position).getDetalle());

        holder.btnMas.setTag(R.integer.posicion_plato,position);
        holder.btnMas.setTag(R.integer.cantidad_plato,cantidad);
        holder.btnMenos.setTag(R.integer.posicion_plato,position);
        holder.btnMenos.setTag(R.integer.cantidad_plato,cantidad);

        return row ;
    }

    class ViewHolder {
        TextView nombre;
        TextView cantidad;
        TextView precio;
        TextView detalles;
        Button btnMas;
        Button btnMenos;


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