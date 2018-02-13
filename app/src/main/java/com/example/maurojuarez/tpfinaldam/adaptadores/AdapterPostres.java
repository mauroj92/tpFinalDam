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

public class AdapterPostres extends ArrayAdapter<Plato> {
    LayoutInflater inflater;
    List<Plato> platos;


    public AdapterPostres(@NonNull Context context, List<Plato> platos) {
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

        }



        holder.nombre.setText(this.getItem(position).getNombre());
        holder.cantidad.setText(holder.cantidad.getText().toString());
        holder.precio.setText("$: " + this.getItem(position).getPrecio().toString());
        holder.detalles.setText(this.getItem(position).getDetalle());

        final View.OnClickListener onClickListener_mas = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string =  finalHolder.cantidad.getText().toString();
                String[] parts = string.split(":");
                Integer cantidad = Integer.valueOf(parts[1]);
                cantidad++;
                finalHolder.cantidad.setText("Cantidad: " + String.valueOf(cantidad));
            }
        };

        final View.OnClickListener onClickListener_menos = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = finalHolder.cantidad.getText().toString();
                String[] parts = string.split(":");
                Integer cantidad = Integer.valueOf(parts[1]);
                cantidad--;
                finalHolder.cantidad.setText("Cantidad: " + String.valueOf(cantidad));
            }
        };
        holder.btnMenos.setOnClickListener(onClickListener_menos);
        holder.btnMas.setOnClickListener(onClickListener_mas);

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