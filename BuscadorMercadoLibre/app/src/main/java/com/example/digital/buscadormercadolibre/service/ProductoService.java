package com.example.digital.buscadormercadolibre.service;

import com.example.digital.buscadormercadolibre.model.ContenedorProductos;
import com.example.digital.buscadormercadolibre.model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductoService {

    @GET ("search")
    Call<List<Producto>> getProducto ();

    @GET("search/{id}")
    Call<Producto> getProductoByNombre(@Path("id") String id);

    @GET ("search")
    Call<ContenedorProductos> getProductoBySeach (@Query("q")String quuery);


}
