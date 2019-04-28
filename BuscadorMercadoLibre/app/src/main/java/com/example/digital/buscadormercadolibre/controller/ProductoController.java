package com.example.digital.buscadormercadolibre.controller;

import com.example.digital.buscadormercadolibre.dao.ProductoMLRetrofitDao;
import com.example.digital.buscadormercadolibre.model.ContenedorProductos;
import com.example.digital.buscadormercadolibre.model.Producto;
import com.example.digital.buscadormercadolibre.util.ResultListerner;

import java.util.List;

import javax.xml.transform.Result;

public class ProductoController {

    public void getProductoBySearch (String nombre, final ResultListerner<ContenedorProductos> listernerDeLaVista){
        ProductoMLRetrofitDao productoMLRetrofitDao = new ProductoMLRetrofitDao();
        productoMLRetrofitDao.getProductoByNombre(nombre, new ResultListerner<ContenedorProductos>() {
            @Override
            public void notificar(ContenedorProductos resultado) {
                listernerDeLaVista.notificar(resultado);
            }
        });

    }

}
