package com.example.ma.roombase.controller;

import android.content.Context;

import com.example.ma.roombase.dao.Person;
import com.example.ma.roombase.dao.PersonDaoUtil;
import com.example.ma.roombase.util.ResultListener;

import java.util.List;

/**
 * Created by Nicolas Dubiansky on 16/01/18.
 */

public class PersonController {
    private Context context;

    public PersonController(Context context) {
        this.context = context;
    }

    public void deletePerson(Person person, final ResultListener<Boolean> resultListener) {
        PersonDaoUtil personDaoUtil = new PersonDaoUtil(context);
        personDaoUtil.deletePerson(person, new ResultListener<Boolean>() {
            @Override
            public void finish(Boolean resultado) {
                resultListener.finish(resultado);
            }
        });
    }

    public void insertPerson(Person person, final ResultListener<Long> resultListener) {
        PersonDaoUtil personDaoUtil = new PersonDaoUtil(context);
        personDaoUtil.insertPerson(person, new ResultListener<Long>() {
            @Override
            public void finish(Long resultado) {
                resultListener.finish(resultado);
            }
        });
    }

    public void getAllPersons(final ResultListener<List<Person>> resultListener) {
        //PROTOCOLO Posible: 1) CARGO INFO DE LA BASE DE DATOS Y LA MUESTRO
        //2) HAGO PEDIDO A INTERNET EN CASO QUE HAYA Y REEMPLAZO LO QUE MOSTRE CON LA BASE , CON EL RESULTADO DE INTERNET
        //3) ACTUALIZO LA INFO DE LA BASE DE DATOS CON LA NUEVA INFO DE INTERNET
        if (hayInternet()) {
            //TODO le pego a web service con retrofit
        } else {
            PersonDaoUtil personDaoUtil = new PersonDaoUtil(context);
            personDaoUtil.getAllPersons(new ResultListener<List<Person>>() {
                @Override
                public void finish(List<Person> resultado) {
                    resultListener.finish(resultado);
                }
            });
        }

    }

    private boolean hayInternet() {
        //TODO FORMA DE SABER SI HAY INTERNET, PERO PARA ESTE EJEPMLO PARA SIMULAR QUE NO HAYA CONEXION, RETORNAMOS FALSE
        return false;
        /*ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();*/
    }

    public void updatePerson(Person person, final ResultListener<Boolean> resultListener) {
        PersonDaoUtil personDaoUtil = new PersonDaoUtil(context);
        personDaoUtil.updatePerson(person, new ResultListener<Boolean>() {
            @Override
            public void finish(Boolean resultado) {
                resultListener.finish(resultado);
            }
        });
    }
}
