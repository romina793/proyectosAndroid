package com.example.ma.roombase.dao;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.example.ma.roombase.util.ResultListener;

import java.util.List;

/**
 * Created by ma on 16/01/18.
 */

public class PersonDaoUtil {

    private AppDatabase appDatabase;

    public PersonDaoUtil(Context context) {
        this.appDatabase = AppDatabase.getInstace(context);
    }

    public void deletePerson(Person person, ResultListener<Boolean> resultListener) {
        DeletePersonAsync deletePersonAsync = new DeletePersonAsync(person, resultListener);
        deletePersonAsync.execute();
    }

    public void insertPerson(Person person, ResultListener<Long> resultListenerDelController) {
        InsertPersonAsync insertPersonAsync = new InsertPersonAsync(person, resultListenerDelController);
        insertPersonAsync.execute();
        //SI FUESE PARA HACERLO EN EL HILO PRINCIPAL:
        //appDatabase.personDao().insertPerson(person);
        //PERO PARA ESTO DEBERIA PONER EN AppDataBase el "allowMainThreadQueries"

    }

    public void getAllPersons(ResultListener<List<Person>> resultListener) {
        LoadPersonAsync loadPersonAsync = new LoadPersonAsync(resultListener);
        loadPersonAsync.execute();
    }

    public void updatePerson(Person person, ResultListener<Boolean> resultListener) {
        UpdatePersonAsync updatePersonAsync = new UpdatePersonAsync(person,resultListener);
        updatePersonAsync.execute();
    }

    private class DeletePersonAsync extends AsyncTask<Void, Void, Integer> {

        private ResultListener<Boolean> resultListener;
        private Person person;

        public DeletePersonAsync(Person person, ResultListener<Boolean> resultListener) {
            this.resultListener = resultListener;
            this.person = person;
        }

        @Override
        protected Integer doInBackground(Void... voids) {

            return appDatabase.personDao().deletePerson(person);
        }


        @Override
        protected void onPostExecute(Integer deletedRows) {
            if (deletedRows >= 0) {
                resultListener.finish(true);
            } else {
                resultListener.finish(false);
            }
        }
    }

    private class InsertPersonAsync extends AsyncTask<Void, Void, Long> {

        private ResultListener<Long> resultListener;
        private Person person;

        public InsertPersonAsync(Person person, ResultListener<Long> resultListener) {
            this.resultListener = resultListener;
            this.person = person;
        }

        @Override
        protected Long doInBackground(Void... voids) {

            try {
                return appDatabase.personDao().insertPerson(person);
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
                return null;
            }
        }


        @Override
        protected void onPostExecute(Long insertId) {
            resultListener.finish(insertId);
        }
    }


    private class LoadPersonAsync extends AsyncTask<Void, Void, List<Person>> {

        private ResultListener<List<Person>> resultListener;

        public LoadPersonAsync(ResultListener<List<Person>> resultListener) {
            this.resultListener = resultListener;
        }

        @Override
        protected List<Person> doInBackground(Void... voids) {

            return appDatabase.personDao().getAllPersons();
        }


        @Override
        protected void onPostExecute(List<Person> personList) {
            resultListener.finish(personList);
        }
    }

    private class UpdatePersonAsync extends AsyncTask<Void, Void, Integer> {

        private ResultListener<Boolean> resultListener;
        private Person person;

        public UpdatePersonAsync(Person person, ResultListener<Boolean> resultListener) {
            this.resultListener = resultListener;
            this.person = person;
        }

        @Override
        protected Integer doInBackground(Void... voids) {

            return appDatabase.personDao().updatePerson(person);
        }


        @Override
        protected void onPostExecute(Integer updatedRows) {
            if (updatedRows > 0) {
                resultListener.finish(true);
            } else {
                resultListener.finish(false);
            }
        }
    }

}


