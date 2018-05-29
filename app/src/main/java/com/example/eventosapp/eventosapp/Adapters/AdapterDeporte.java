package com.example.eventosapp.eventosapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eventosapp.eventosapp.Activities.MainActivity;
import com.example.eventosapp.eventosapp.Class.Evento;
import com.example.eventosapp.eventosapp.R;

import java.util.List;

public class AdapterDeporte extends RecyclerView.Adapter<AdapterDeporte.ViewHolder> {
    private List<Evento> items;
    private Context context;
    private RecyclerView recyclerView;
    private Click click;


    public interface Click {
        void onItemClick(ViewHolder holder, int posicion);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imagendeporte;
        TextView nombredeporte;
        TextView fechadeporte;
        TextView lugardeporte;

        public ViewHolder(View v) {
            super(v);
            imagendeporte = (ImageView) v.findViewById(R.id.img_deporte);
            nombredeporte = (TextView) v.findViewById(R.id.nombre_deporte);
            fechadeporte = (TextView) v.findViewById(R.id.fecha_deporte);
            lugardeporte = (TextView) v.findViewById(R.id.lugardeporte);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            click.onItemClick(this, getAdapterPosition());
        }
    }

    public AdapterDeporte(List<Evento> items, Context context,Click click) {
        this.items = items;
        this.context = context;
        this.click = click;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.deporte_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Evento evento = items.get(i);
        viewHolder.nombredeporte.setText(evento.getNombreevento());
        Glide.with(context).load(MainActivity.HOST +MainActivity.PATH+ evento.getImagenevento()).into(viewHolder.imagendeporte);
    }
}


