package com.example.digital.recyclerview;

import java.io.Serializable;

public class Personaje implements Serializable{

    private String nombre;
    private String programa;
    private Integer imagen; // porque apunta la dirección donde está ubicada la imagen. Por eso Interger (identificador)


    //por buena costumbre crear siempre dos constructores, uno con la info y otro vacío.
    public Personaje() {

    }


    public Personaje(String nombre, String programa, Integer imagen) {
        this.nombre = nombre;
        this.programa = programa;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrograma() {
        return programa;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }


}
