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

public class AdapterEvento extends RecyclerView.Adapter<AdapterEvento.EventoViewHolder> {
    private List<Evento> items;
    private Context context;
    private RecyclerView recyclerView;
    private Click click;


    public interface Click {
        void onItemClick(EventoViewHolder holder, int posicion);
    }

    public class EventoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imagenevento;
        TextView nombreevento;
        TextView fechaevento;
        TextView lugarevento;

        public EventoViewHolder(View v) {
            super(v);
            imagenevento = (ImageView) v.findViewById(R.id.img_evento);
            nombreevento = (TextView) v.findViewById(R.id.nombre_evento);
            fechaevento = (TextView) v.findViewById(R.id.fecha_evento);
            lugarevento = (TextView) v.findViewById(R.id.lugarevento);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            click.onItemClick(this, getAdapterPosition());
        }
    }

    public AdapterEvento(List<Evento> items, Context context,Click click) {
        this.items = items;
        this.context = context;
        this.click = click;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public EventoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.evento_item, viewGroup, false);
        return new EventoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EventoViewHolder viewHolder, int i) {
        Evento evento = items.get(i);
        viewHolder.nombreevento.setText(evento.getNombreevento());
        Glide.with(context)
                .load(MainActivity.HOST +MainActivity.PATH+ evento.getImagenevento())
                .into(viewHolder.imagenevento);
    }
}
