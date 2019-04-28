package com.example.digital.segundoentregable.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.digital.segundoentregable.R;
import com.example.digital.segundoentregable.dao.RecetaDao;
import com.example.digital.segundoentregable.model.Receta;
import com.example.digital.segundoentregable.view.adapter.RecetaAdapter;

import java.util.List;


public class FragmentRecetaDetalle extends Fragment {


    public static final String KEY_TITULO = "KEY_TITULO";
    private ImageView fragmentDetalle_imageView;
    private TextView textViewTituloReceta;
    private TextView textViewIngredientesReceta;
    private TextView textViewPreparacionReceta;

  /*  activityDetalle_imageView = findViewById(R.id.activityDetalle_imageView);
    textViewTituloReceta = findViewById(R.id.activityDetalle_textViewNombre);
    textViewIngredientesReceta = findViewById(R.id.celdaReceta_TextIngredienteReceta);
    textViewPreparacionReceta = findViewById(R.id.celdaReceta_TextPreparacionteReceta);*/


    private View vistaDelFragment;
    //Atributo donde me voy a guardar mi Listener
    private ListenerFragmentReceta listenerFragmentReceta;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vistaDelFragment = inflater.inflate(R.layout.fragment_receta_detalle, container, false);
        Bundle bundle = getArguments();
        Receta receta = (Receta) bundle.getSerializable(KEY_TITULO);

        ImageView fragmentDetalle_imageView = vistaDelFragment.findViewById(R.id.fragmentDetalle_imageView);
        fragmentDetalle_imageView.setImageResource(receta.getImagenReceta());

        return vistaDelFragment;
    }

    //Cuando se pega el fragment en un contenedor de una activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Ese context lo trato como ListenerFragmentReceta
        this.listenerFragmentReceta = (ListenerFragmentReceta) context;
    }


    //Creamos una interface que va a funcionar como listener de este fragment para quien la implemente
    public interface ListenerFragmentReceta{
        //notificar al listener de este fragment y le va a pasar la información
        public void notificar ();
    }




}
