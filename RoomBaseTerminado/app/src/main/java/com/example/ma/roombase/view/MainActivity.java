package com.example.ma.roombase.view;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ma.roombase.R;
import com.example.ma.roombase.controller.PersonController;
import com.example.ma.roombase.dao.Person;
import com.example.ma.roombase.util.ResultListener;
import com.example.ma.roombase.view.adapter.PersonRecyclerAdapter;
import com.example.ma.roombase.view.adapter.TocarItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton addPersonButton;
    private PersonController controller;
    private PersonRecyclerAdapter adapter;
    private LinearLayout addPersonContainer;
    private ImageView addPersonDoneButton;
    private ImageView closeEditPersonContainer;
    private EditText personNameEditText;
    private EditText personLastnameEditText;
    private EditText personFavFoodEditText;
    private Person currentEditingPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        adapter = new PersonRecyclerAdapter(new PersonRecyclerAdapter.PersonClickeable() {
            @Override
            public void editPerson(Person person) {
                currentEditingPerson = person;
                loadAddPersonContainer(person);
            }

            @Override
            public void deletePerson(final Person person) {
                controller.deletePerson(person, new ResultListener<Boolean>() {
                    @Override
                    public void finish(Boolean resultado) {
                        if (resultado == true) {
                            adapter.deletePerson(person);
                        } else {
                            Toast.makeText(MainActivity.this, "Error al eliminar: "
                                            + person.getLastname() + " " + person.getName(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        recyclerView.setAdapter(adapter);
        ItemTouchHelper.Callback callback = new TocarItem(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        clickAddPersonDoneButton();
        loadPersonsFromRoom();

    }

    private void initViews() {
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.colapseToolbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsedAppBarTitleStyle);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addPersonButton = findViewById(R.id.add_person_button);
        addPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPersonContainer.setVisibility(View.VISIBLE);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

        addPersonContainer = findViewById(R.id.add_person_container_id);
        addPersonDoneButton = findViewById(R.id.add_person_done_id);
        closeEditPersonContainer = findViewById(R.id.close_container_edit_person);
        closeEditPersonContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cleanAddPersonContainer();
            }
        });
        personNameEditText = findViewById(R.id.edittext_name_id);
        personLastnameEditText = findViewById(R.id.edittext_surname_id);
        personFavFoodEditText = findViewById(R.id.edittext_favfood_id);
        controller = new PersonController(this);
    }

    private void loadPersonsFromRoom() {
        controller.getAllPersons(new ResultListener<List<Person>>() {
            @Override
            public void finish(List<Person> resultado) {
                adapter.addPersons(resultado);
            }
        });
    }

    private void clickAddPersonDoneButton() {
        addPersonDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String personNewName = personNameEditText.getText().toString();
                String personNewLastName = personLastnameEditText.getText().toString();
                String personNewFavFood = personFavFoodEditText.getText().toString();
                if (!personNewName.isEmpty() &&
                        !personNewLastName.isEmpty() &&
                        !personNewFavFood.isEmpty()) {

                    if (currentEditingPerson != null) {
                        currentEditingPerson.setName(personNewName);
                        currentEditingPerson.setLastname(personNewLastName);
                        currentEditingPerson.setFavouriteFood(personNewFavFood);
                        updatePerson(currentEditingPerson);
                    } else {
                        Person person = new Person(personNewName,personNewLastName,personNewFavFood);
                        insertPerson(person);
                    }


                } else {
                    Toast.makeText(MainActivity.this, "Completar todos los campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updatePerson(Person person) {
        controller.updatePerson(person, new ResultListener<Boolean>() {
            @Override
            public void finish(Boolean resultado) {
                cleanAddPersonContainer();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void insertPerson(final Person person) {
        controller.insertPerson(person, new ResultListener<Long>() {
            @Override
            public void finish(Long resultado) {
                if (resultado != null) {
                    person.setId(resultado);
                    adapter.addPerson(person);
                    cleanAddPersonContainer();
                } else {
                    Toast.makeText(MainActivity.this, "Error al insertar nueva persona, verifique no exista", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void cleanAddPersonContainer() {
        personNameEditText.setText("");
        personLastnameEditText.setText("");
        personFavFoodEditText.setText("");
        addPersonContainer.setVisibility(View.GONE);
        currentEditingPerson = null;
    }

    private void loadAddPersonContainer(Person person) {
        personNameEditText.setText(person.getName());
        personLastnameEditText.setText(person.getLastname());
        personFavFoodEditText.setText(person.getFavouriteFood());
        addPersonContainer.setVisibility(View.VISIBLE);
    }
}
