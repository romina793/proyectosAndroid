package com.example.ma.roombase.dao;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Nicolas Dubiansky on 16/01/18.
 */

@Entity(tableName = "person",
        indices = {@Index(value = {"first_name", "last_name"},
        unique = true)})
public class Person {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "first_name")
    private String name;

    @ColumnInfo(name = "last_name")
    private String lastname;

    @ColumnInfo(name = "fav_food")
    private String favouriteFood;

    public Person(){

    }

    public Person(String name, String surname, String favouriteFood) {
        this.name = name;
        this.lastname = surname;
        this.favouriteFood = favouriteFood;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFavouriteFood() {
        return favouriteFood;
    }

    public void setFavouriteFood(String favouriteFood) {
        this.favouriteFood = favouriteFood;
    }

    @Override
    public boolean equals(Object obj) {
        Person person1 = (Person)obj;
        return (this.lastname.equals(((Person) obj).getLastname()) &&
                this.name.equals(person1.getName()));
    }


}
