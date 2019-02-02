package com.example.eventosapp.eventosapp.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.eventosapp.eventosapp.Activities.MainActivity;
import com.example.eventosapp.eventosapp.Activities.MapsActivity;
import com.example.eventosapp.eventosapp.R;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventoDetalleFragment extends Fragment {

    CheckBox checkBox_asistentes;
    CheckBox checkBox_favorito;
    ImageButton imageButton_mapa;


    ImageView imageview_eventodetalle;
    TextView fecha_evento_detalle, mes_evento_detalle, nombre_evento_detalle, direccion_evento_detalle, descripcion_evento_detalle;
    TextView tv_cant_asistntes;


    String imagen_portada, dia, mes, descripcion, id_evento_det, cantidad_asistentes, direccion,nombre_evento;
    double latitud, longitud;
    String URL_ASISTIR = MainActivity.HOST + "/api/MarcarAsistente";
    String URL_FAVORITO = MainActivity.HOST + "/api/MarcarFavorito";

    public EventoDetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_evento_detalle, container, false);


        checkBox_asistentes = (CheckBox) v.findViewById(R.id.chk_asistentes);
        checkBox_favorito = (CheckBox) v.findViewById(R.id.chk_favorito);
        imageButton_mapa = (ImageButton) v.findViewById(R.id.ubicacion_evento_detalle);
        imageview_eventodetalle = (ImageView) v.findViewById(R.id.imageview_eventodetalle);
        fecha_evento_detalle = (TextView) v.findViewById(R.id.fecha_evento_detalle);
        mes_evento_detalle = (TextView) v.findViewById(R.id.mes_evento_detalle);
        nombre_evento_detalle = (TextView) v.findViewById(R.id.nombre_evento_detalle);
        direccion_evento_detalle = (TextView) v.findViewById(R.id.direccion_evento_detalle);
        descripcion_evento_detalle = (TextView) v.findViewById(R.id.descripcion_evento_detalle);
        tv_cant_asistntes = (TextView) v.findViewById(R.id.tv_cant_asistntes);

        checkBox_asistentes.setChecked(false);
        checkBox_favorito.setChecked(false);
        ObtenerDatos();
        VerificarAsistir();
        VerificarFavorito();
        MostrarDatos();

        checkBox_asistentes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String id_usuario = ObtenerIDUsuario();
                    String id_evento = id_evento_det;
                    MarcarAsistir(id_usuario, id_evento);
                    checkBox_asistentes.setChecked(true);
                } else {
                    String id_usuario = ObtenerIDUsuario();
                    String id_evento = id_evento_det;
                    MarcarAsistir(id_usuario, id_evento);
                    checkBox_asistentes.setChecked(false);
                }
            }
        });

        checkBox_favorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String id_usuario = ObtenerIDUsuario();
                    String id_evento = id_evento_det;
                    MarcarFavorito(id_usuario, id_evento);
                    checkBox_favorito.setChecked(true);
                } else {
                    String id_usuario = ObtenerIDUsuario();
                    String id_evento = id_evento_det;
                    MarcarFavorito(id_usuario, id_evento);
                    checkBox_favorito.setChecked(false);
                }
            }
        });

        imageButton_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapsActivity mapsActivity = new MapsActivity();

                Intent i = new Intent(getActivity(), MapsActivity.class);
                i.putExtra("latitud",latitud);
                i.putExtra("longitud",longitud);
                i.putExtra("nombre",nombre_evento);
                startActivity(i);
            }
        });

        return v;
    }

    public void ObtenerDatos() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            imagen_portada = bundle.getString("imagen");
            dia = bundle.getString("dia");
            mes = bundle.getString("mes");
            descripcion = bundle.getString("descripcion");
            id_evento_det = bundle.getString("id_evento");
            cantidad_asistentes = bundle.getString("cantidad_asistentes");
            direccion = bundle.getString("direccion");
            latitud = bundle.getDouble("latitud");
            longitud = bundle.getDouble("longitud");
            nombre_evento = bundle.getString("nombre_evento");
        }
    }

    public void MostrarDatos(){
        Glide.with(getActivity())
                .load(MainActivity.HOST + MainActivity.PATH + imagen_portada)
                .into(imageview_eventodetalle);
        fecha_evento_detalle.setText(dia);
        mes_evento_detalle.setText(mes);
        descripcion_evento_detalle.setText(descripcion);
        tv_cant_asistntes.setText(cantidad_asistentes);
        direccion_evento_detalle.setText(direccion);
        nombre_evento_detalle.setText(nombre_evento);
    }

    public String ObtenerIDUsuario() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MiSesion", Context.MODE_PRIVATE);
        String id_usuario = sharedPreferences.getString("id", "");
        return id_usuario;
    }

    public void MarcarAsistir(final String id_usuario, final String id_evento) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ASISTIR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", id_usuario);
                params.put("evento_id", id_evento);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    public void MarcarFavorito(final String id_usuario, final String id_evento) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FAVORITO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", id_usuario);
                params.put("evento_id", id_evento);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    public void VerificarAsistir() {

        final String id_usuario = ObtenerIDUsuario();
        final String id_evento = id_evento_det;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ASISTIR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Verificado")) {
                            checkBox_asistentes.setChecked(true);
                        } else {
                            checkBox_asistentes.setChecked(false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", id_usuario);
                params.put("evento_id", id_evento);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void VerificarFavorito() {
        final String id_usuario = ObtenerIDUsuario();
        final String id_evento = id_evento_det;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ASISTIR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Verificado")) {
                            checkBox_favorito.setChecked(true);
                        } else {
                            checkBox_favorito.setChecked(false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", id_usuario);
                params.put("evento_id", id_evento);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
