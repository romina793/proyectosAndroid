package com.example.digital.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class PersonajesAdapter extends RecyclerView.Adapter{

    private List<Personaje> listaDePersonajes;
    private Context context;
    //paso 3
    private ListenerPersonajesAdapter listenerPersonajesAdapter;
    //parte del paso 6
    private Personaje personaje;

    public PersonajesAdapter(List<Personaje> listaDePersonajes, ListenerPersonajesAdapter listenerPersonajesAdapter) {

        this.listaDePersonajes = listaDePersonajes;
        //4. se agrega al constructor
        this.listenerPersonajesAdapter = listenerPersonajesAdapter;
    }


    //este es el método que infla la celda y crea el viewholder que recibe como parámetro la celda inflada
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //buscamos el inflador desde el contexto.
        //lo guardamos en una variable para poder utilizarlo luego.
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());

        //inflamos la vista
        View viewCelda = inflador.inflate(R.layout.celda_personaje, parent, false);

        //creamos el viewholder del personaje pasandole la vista de la celda
       // lo mandamos a viewholder para que haga las búsquedas
        PersonajesViewHolder personajesViewHolder=  new PersonajesViewHolder(viewCelda);

        //tiene que retornar un view holder
        return personajesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //este método se ejecuta cada vez que uno scrollea
        Personaje personaje = listaDePersonajes.get(position);
        //confía en mí que este viewholder es un personaje
        PersonajesViewHolder personajesViewHolder = (PersonajesViewHolder) holder;
        personajesViewHolder.bind(personaje);

    }

    @Override
    public int getItemCount() {
        return listaDePersonajes.size();
    }

    //el ViewHolder es el contenedor del modelo como xml

    private class PersonajesViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewpersonaje;
        private TextView textViewnombrePersonaje;
        private TextView textViewprogramaPersonaje;
        private Personaje personaje;

        //ESTA ES LA PARTE XML

        public PersonajesViewHolder(View itemView) {
            super(itemView);
            imageViewpersonaje = itemView.findViewById(R.id.imageViewCelda);
            textViewnombrePersonaje = itemView.findViewById(R.id.textViewNombre);
            textViewprogramaPersonaje = itemView.findViewById(R.id.textViewPrograma);
            //5. le hacemos el OnClickListener a toda la celda (itemView)
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //6. al escuchador le tenemos que decir informar selección
                    listenerPersonajesAdapter.informarSeleccion(personaje);

                }
            });

        }

        //ESTA ES LA PARTE DE PROGRAMACIÓN

        public void bind(Personaje unPersonaje){
            //parte del paso 6
            this.personaje = unPersonaje;
            textViewnombrePersonaje.setText(unPersonaje.getNombre());
            textViewprogramaPersonaje.setText(unPersonaje.getPrograma());
           imageViewpersonaje.setImageResource(unPersonaje.getImagen());

        }

    }


    //1. definir una interface
    public interface ListenerPersonajesAdapter {
        //2. el método es lo que queremos comunicar
        public void informarSeleccion (Personaje personaje);
    }


}
