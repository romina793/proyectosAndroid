package com.example.digital.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PersonajesAdapter.ListenerPersonajesAdapter{

    private RecyclerView recyclerViewPersonajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerViewPersonajes = findViewById(R.id.recyclerViewPersonajes);
        List<Personaje> personajes = cargarPersonajes();
        PersonajesAdapter personajesAdapter= new PersonajesAdapter(personajes,this);

        //creamos el LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);

        //SON LAS ÚLTIMAS DOS LÍNEAS PARA UNIR TODO
        recyclerViewPersonajes.setLayoutManager(linearLayoutManager);
        recyclerViewPersonajes.setAdapter(personajesAdapter);
        recyclerViewPersonajes.setHasFixedSize(true);
    }


    private List<Personaje> cargarPersonajes (){
        List<Personaje> personajes = new ArrayList<>();
        personajes.add(new Personaje("Homero Simpsons", "Los Simpson", R.drawable.homero));
        personajes.add(new Personaje( "Elsa", "Frozen", R.drawable.x));
        personajes.add(new Personaje("Alf", "Alf", R.drawable.alf));
        personajes.add(new Personaje("Ricky Fort", "Felfort", R.drawable.fort));
        personajes.add(new Personaje("Lisa", "Los Simpson", R.drawable.x));
        personajes.add(new Personaje("Pepe Argento", "Casados con Hijos", R.drawable.x));


        return personajes;
    }

    @Override
    public void informarSeleccion(Personaje personaje) {

        Intent unIntent = new Intent(this, DetalleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("unPersonaje", personaje);
        unIntent.putExtras(bundle);
        startActivity(unIntent);
        //Toast.makeText(this, personaje.getNombre(), Toast.LENGTH_SHORT).show();



    }

}
