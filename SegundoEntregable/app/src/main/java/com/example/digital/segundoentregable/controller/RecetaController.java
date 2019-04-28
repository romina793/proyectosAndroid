package com.example.digital.segundoentregable.controller;

import com.example.digital.segundoentregable.dao.RecetaDao;
import com.example.digital.segundoentregable.dao.RecetaInternetDao;
import com.example.digital.segundoentregable.model.Receta;

import java.util.List;

public class RecetaController {

    private RecetaDao recetaDao;
    private RecetaInternetDao recetaInternetDao;


    public List<Receta> getRecetas(){
        if (hayInternet()) {
            return  recetaInternetDao.traerDeInternet();
        } else {
            recetaDao = new RecetaDao();
            return recetaDao.cargarReceta();
        }
    }

    public Boolean hayInternet(){
        return false;
    }


}
