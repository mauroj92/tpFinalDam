package com.example.maurojuarez.tpfinaldam.adaptadores;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.maurojuarez.tpfinaldam.R;
import com.example.maurojuarez.tpfinaldam.modelo.Pedido;

import java.util.List;

/**
 * Created by Nicolas on 12/2/2018.
 */

public class AdapterPedido extends ArrayAdapter<Pedido> {
    LayoutInflater inflater;
    List<Pedido> pedidos;


    public AdapterPedido(@NonNull Context context, List<Pedido> pedidos) {
        super(context, R.layout.row_pedido, pedidos);
        inflater = LayoutInflater.from(context);
        this.pedidos = pedidos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        if (row == null){
            row = inflater.inflate(R.layout.row_pedido, parent, false);
        }

        ViewHolder holder = (ViewHolder) row.getTag();

        if (holder == null) {
            holder = new ViewHolder(row);
            row.setTag(holder);
        }

        holder.nombre.setText(this.getItem(position).getNombre());
        holder.mesa.setText(this.getItem(position).getMesa().toString());
        holder.hora.setText(this.getItem(position).getHora());

        return row ;
    }

    class ViewHolder {
        TextView nombre;
        TextView mesa;
        TextView hora;

        public ViewHolder(View base) {
            this.nombre = (TextView) base.findViewById(R.id.txvNombrePedido);
            this.mesa = (TextView) base.findViewById(R.id.txvMesaPedido);
            this.hora = (TextView) base.findViewById(R.id.txvHoraPedido);
        }
    }
}
