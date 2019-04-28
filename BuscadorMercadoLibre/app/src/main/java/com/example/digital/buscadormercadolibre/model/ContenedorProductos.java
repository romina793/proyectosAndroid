package com.example.digital.buscadormercadolibre.model;

import java.util.List;

public class ContenedorProductos {

    private List<Producto> results;


    public ContenedorProductos(List<Producto> results) {
        this.results = results;
    }

    public ContenedorProductos() {

    }

    public List<Producto> getResults() {
        return results;
    }

    public void setResults(List<Producto> results) {
        this.results = results;
    }
}
