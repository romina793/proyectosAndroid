package com.example.digital.segundoentregable.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.digital.segundoentregable.R;
import com.example.digital.segundoentregable.model.Receta;
import com.example.digital.segundoentregable.view.adapter.RecetaAdapter;
import com.example.digital.segundoentregable.dao.RecetaDao;

import java.util.List;

public class RecyclerViewFragmentReceta extends Fragment implements RecetaAdapter.ListenerRecetaAdapter {


    private ListenerRecyclerViewFragment listenerRecyclerViewFragment;
    private RecyclerView recyclerViewReceta;


    public RecyclerViewFragmentReceta() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listenerRecyclerViewFragment = (ListenerRecyclerViewFragment) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.recyclerview_fragment_receta, container, false);

        recyclerViewReceta  = view.findViewById(R.id.recycler_View_Receta);

        List<Receta> recetas = new RecetaDao().cargarReceta();

        RecetaAdapter recetaAdapter = new RecetaAdapter(recetas,this);
        //creamos el layoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerViewReceta.setLayoutManager(linearLayoutManager);
        recyclerViewReceta.setAdapter(recetaAdapter);
        recyclerViewReceta.setHasFixedSize(true);

        return view;
    }

    @Override
    public void informarSeleccion(Receta receta) {
        listenerRecyclerViewFragment.informarSeleccion(receta);
    }

    public interface ListenerRecyclerViewFragment{
        public void informarSeleccion(Receta receta);
    }


}
