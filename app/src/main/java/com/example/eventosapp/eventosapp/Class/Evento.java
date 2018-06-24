package com.example.eventosapp.eventosapp.Class;

public class Evento {
    private int asistentes;
    private int Id_Evento;
    private String nombreevento;
    private String descripcion_evento;
    private String fecha;
    private String hora;
    private String direccion;
    private String imagenevento;
    private double latitud;
    private double longitud;

    public int getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(int asistentes) {
        this.asistentes = asistentes;
    }

    public int getId_Evento() {
        return Id_Evento;
    }

    public void setId_Evento(int id_Evento) {
        Id_Evento = id_Evento;
    }

    public String getNombreevento() {
        return nombreevento;
    }

    public void setNombreevento(String nombreevento) {
        this.nombreevento = nombreevento;
    }

    public String getDescripcion_evento() {
        return descripcion_evento;
    }

    public void setDescripcion_evento(String descripcion_evento) {
        this.descripcion_evento = descripcion_evento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    public String getImagenevento() {
        return imagenevento;
    }

    public void setImagenevento(String imagenevento) {
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
