package com.example.ma.roombase.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Nicolas Dubiansky on 16/01/18.
 */


@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "database-name";
    private static AppDatabase INSTANCE;

    public abstract PersonDao personDao();

    public static AppDatabase getInstace(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME).build();

            //TODO si se necesita sacar async task porque se complejiza mucho, descomentar lo de abajo
            //INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
            //      AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();

        }
        return INSTANCE;
    }


}
