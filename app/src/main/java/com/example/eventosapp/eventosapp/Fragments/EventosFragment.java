package com.example.eventosapp.eventosapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.eventosapp.eventosapp.Activities.MainActivity;
import com.example.eventosapp.eventosapp.Adapters.AdapterEvento;
import com.example.eventosapp.eventosapp.Class.Evento;
import com.example.eventosapp.eventosapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventosFragment extends Fragment implements AdapterEvento.Click {


    private List<Evento> eventoList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private RequestQueue requestQueue;
    int requestCount = 1;
    static final String URL_EVENTOS = MainActivity.HOST + "/api/ListaEventos";

    public EventosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_eventos, container, false);

        recyclerView = v.findViewById(R.id.recycler_eventos);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        eventoList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getActivity());
        GetData();
        //obtener recycler
        adapter = new AdapterEvento(eventoList, getActivity(), this);
        recyclerView.setAdapter(adapter);

        return v;
    }

    private void GetData() {
        requestQueue.add(GetDataServer());
        requestCount++;
    }

    private JsonArrayRequest GetDataServer() {

        return new JsonArrayRequest(URL_EVENTOS,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            Evento evento = new Evento();
                            JSONObject json = null;
                            try {
                                json = response.getJSONObject(i);
                                evento.setNombreevento(json.getString("nombre_evento"));
                                evento.setImagenevento(json.getString("imagen"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            eventoList.add(evento);
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error en la conexión", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onItemClick(AdapterEvento.EventoViewHolder holder, int posicion) {
        EventoDetalleFragment eventoDetalleFragment = new EventoDetalleFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
        .replace(R.id.fragment_container, eventoDetalleFragment,"tag")
        .addToBackStack("tag")
        .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
