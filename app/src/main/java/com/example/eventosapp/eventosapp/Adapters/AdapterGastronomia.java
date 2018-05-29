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

public class AdapterGastronomia extends RecyclerView.Adapter<AdapterGastronomia.ViewHolder> {
    private List<Evento> items;
    private Context context;
    private RecyclerView recyclerView;
    private Click click;


    public interface Click {
        void onItemClick(ViewHolder holder, int posicion);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imagengastronomia;
        TextView nombregastronomia;
        TextView fechagastronomia;
        TextView lugargastronomia;

        public ViewHolder(View v) {
            super(v);
            imagengastronomia = (ImageView) v.findViewById(R.id.img_gastronomia);
            nombregastronomia = (TextView) v.findViewById(R.id.nombre_gastronomia);
            fechagastronomia = (TextView) v.findViewById(R.id.fecha_gastronomia);
            lugargastronomia = (TextView) v.findViewById(R.id.lugargastronomia);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            click.onItemClick(this, getAdapterPosition());
        }
    }

    public AdapterGastronomia(List<Evento> items, Context context,Click click) {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gastronomia_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Evento evento = items.get(i);
        viewHolder.nombregastronomia.setText(evento.getNombreevento());
        Glide.with(context).load(MainActivity.HOST +MainActivity.PATH+ evento.getImagenevento()).into(viewHolder.imagengastronomia);
    }
}

