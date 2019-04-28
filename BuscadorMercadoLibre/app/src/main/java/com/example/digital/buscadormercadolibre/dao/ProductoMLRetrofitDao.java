package com.example.digital.buscadormercadolibre.dao;

import com.example.digital.buscadormercadolibre.model.ContenedorProductos;
import com.example.digital.buscadormercadolibre.model.Producto;
import com.example.digital.buscadormercadolibre.service.ProductoService;
import com.example.digital.buscadormercadolibre.util.ResultListerner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoMLRetrofitDao {

    private String baseUrl;
    private Retrofit retrofit;
    private ProductoService productoService;


    public ProductoMLRetrofitDao() {

        //https://api.mercadolibre.com/sites/MLA/
        this.baseUrl = "https://api.mercadopago.com/v1/";
        this.retrofit = new Retrofit.Builder()
                .baseUrl(this.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.productoService = retrofit.create(ProductoService.class);
    }


    public void getProductoByNombre(String nombre, final ResultListerner<ContenedorProductos> listernerDelControler) {

        productoService.getProductoBySeach(nombre).enqueue(new Callback<ContenedorProductos>() {
            @Override
            public void onResponse(Call<ContenedorProductos> call, Response<ContenedorProductos> response) {
                ContenedorProductos contenedorProductos = response.body();
                listernerDelControler.notificar(contenedorProductos);

            }

            @Override
            public void onFailure(Call<ContenedorProductos> call, Throwable t) {
                System.out.println("Algo Falló");
            }
        });


    }
}

