package com.example.digital.segundoentregable.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.digital.segundoentregable.R;
import com.example.digital.segundoentregable.model.Receta;

import java.util.List;

public class RecetaAdapter extends RecyclerView.Adapter {

    private List<Receta> listaDeRecetas;
    private ListenerRecetaAdapter listenerRecetaAdapter;


    public RecetaAdapter(List<Receta> listaDeRecetas, ListenerRecetaAdapter listenerRecetaAdapter) {
        this.listaDeRecetas = listaDeRecetas;
        this.listenerRecetaAdapter = listenerRecetaAdapter;
    }

    public RecetaAdapter() {

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());
        View viewCelda = inflador.inflate(R.layout.celda_receta,parent,false);
        RecetaViewHolder recetaViewHolder = new RecetaViewHolder(viewCelda);
        return recetaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Receta personaje = listaDeRecetas.get(position);
        RecetaViewHolder recetaViewHolder = (RecetaViewHolder) holder;
        recetaViewHolder.bind(personaje);
    }

    @Override
    public int getItemCount() {
        return listaDeRecetas.size();
    }

    private class RecetaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewReceta;
        private TextView textViewTitulo;
        private TextView textViewIngredientes;
        private TextView textViewPreparacion;
        private Receta receta;

        public RecetaViewHolder(final View itemView) {
            super(itemView);
            imageViewReceta = itemView.findViewById(R.id.celdaRecetas_imageViewReceta);
            textViewTitulo = itemView.findViewById(R.id.celdaReceta_TextTituloReceta);
            textViewIngredientes = itemView.findViewById(R.id.celdaReceta_TextIngredienteReceta);
            textViewPreparacion =itemView.findViewById(R.id.celdaReceta_TextPreparacionteReceta);
            //le hacemos el onCLickListener a toda la celda (itemView)
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerRecetaAdapter.informarSeleccion(receta);
                }
            });
        }

        public void bind(Receta unaReceta){
            this.receta = unaReceta;

            textViewTitulo.setText(unaReceta.getTituloReceta());
            textViewIngredientes.setText(unaReceta.getIngredientesReceta());

            if (unaReceta.getImagenReceta() == null){
                imageViewReceta.setImageResource(R.drawable.fort);
            }else{
                imageViewReceta.setImageResource(unaReceta.getImagenReceta());
            }
        }
    }



    public interface ListenerRecetaAdapter {
        public void informarSeleccion (Receta receta);
    }
}
