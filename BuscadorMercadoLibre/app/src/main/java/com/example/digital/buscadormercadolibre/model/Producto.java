package com.example.digital.buscadormercadolibre.model;

import com.google.gson.annotations.SerializedName;

import java.io.InputStream;
import java.util.List;

public class Producto {

    @SerializedName("price")
    private String precio;
    @SerializedName("thumbnail")
    private String foto;
    @SerializedName("title")
    private String nombre;


    public Producto(String precio, String foto, String nombre) {
        this.precio = precio;
        this.foto = foto;
        this.nombre = nombre;
    }


    public Producto() {

    }


    public String getPrecio() {
        return precio;
    }

    public String getFoto() {
        return foto;
    }

    public String getNombre() {
        return nombre;
    }


    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public String toString() {
        return "Producto{" +
                "precio=" + precio +
                ", foto='" + foto + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
