package com.example.digital.segundoentregable.model;

import java.io.Serializable;

public class Receta implements Serializable{
    private String tituloReceta;
    private String ingredientesReceta;
    private String preparacionReceta;
    private Integer imagenReceta;

    public Receta(String tituloReceta, String ingredientesReceta, String preparacionReceta, Integer imagenReceta) {
        this.tituloReceta = tituloReceta;
        this.ingredientesReceta = ingredientesReceta;
        this.preparacionReceta = preparacionReceta;
        this.imagenReceta = imagenReceta;
    }

    public Receta() {

    }

    public String getTituloReceta() {
        return tituloReceta;
    }

    public String getIngredientesReceta() {
        return ingredientesReceta;
    }

    public String getPreparacionReceta() {
        return preparacionReceta;
    }

    public Integer getImagenReceta() {
        return imagenReceta;
    }

    public void setTituloReceta(String tituloReceta) {
        this.tituloReceta = tituloReceta;
    }

    public void setIngredientesReceta(String ingredientesReceta) {
        this.ingredientesReceta = ingredientesReceta;
    }

    public void setPreparacionReceta(String preparacionReceta) {
        this.preparacionReceta = preparacionReceta;
    }

    public void setImagenReceta(Integer imagenReceta) {
        this.imagenReceta = imagenReceta;
    }
}
