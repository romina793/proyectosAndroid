package com.example.ma.roombase.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Nicolas Dubiansky on 16/01/18.
 */

@Dao
public interface PersonDao {

    @Query("SELECT * FROM person")
    List<Person> getAllPersons();

    @Query("SELECT * FROM Person WHERE last_name = :lastname")
    List<Person> getPersonsForLastname(String lastname);

    //puede devolver un Long, que especifica el nuevo ID de la fila insertada
    @Insert(onConflict = OnConflictStrategy.FAIL)
    Long insertPerson(Person person);

    //puede devolver un Array de Long, que especifica los nuevos IDs de las filas insertadas
    @Insert
    Long[] insertPersons(List<Person> persons);

    //puede devolver un int, que especifica la cantidad de filas eliminadas
    @Delete
    int deletePerson(Person person);

    //puede devolver un int, que especifica la cantidad de filas actualizadas
    @Update()
    int updatePerson(Person person);
}
