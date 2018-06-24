package com.example.eventosapp.eventosapp.Activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.eventosapp.eventosapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitud,longitud;
    String nombre_evento;
    private static final int ZOOM_LEVEL = 15;
    private static final int TILT_LEVEL = 0;
    private static final int BEARING_LEVEL = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            latitud = extras.getDouble("latitud");
            longitud = extras.getDouble("longitud");
            nombre_evento = extras.getString("nombre");

        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(latLng).title(nombre_evento));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        CameraPosition camPos = new CameraPosition(latLng, ZOOM_LEVEL, TILT_LEVEL, BEARING_LEVEL);
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(camPos));
    }
}
