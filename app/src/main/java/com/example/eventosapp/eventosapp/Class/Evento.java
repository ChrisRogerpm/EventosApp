package com.example.eventosapp.eventosapp.Class;

public class Evento {
    private String nombreevento;
    private String fecha;
    private String lugar;
    private int imagenevento;
    private double latitud;
    private double longitud;

    public String getNombreevento() {
        return nombreevento;
    }

    public void setNombreevento(String nombreevento) {
        this.nombreevento = nombreevento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getImagenevento() {
        return imagenevento;
    }

    public void setImagenevento(int imagenevento) {
        this.imagenevento = imagenevento;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
