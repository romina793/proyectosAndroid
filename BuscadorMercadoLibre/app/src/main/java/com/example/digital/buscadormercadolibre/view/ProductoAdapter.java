package com.example.digital.buscadormercadolibre.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.digital.buscadormercadolibre.R;
import com.example.digital.buscadormercadolibre.model.ContenedorProductos;
import com.example.digital.buscadormercadolibre.model.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter{

    private List<Producto> listaDeProducto;
    //paso 3
    private ListenerProductoAdapter listenerProductoAdapterAdapter;
    private Context context;



    public ProductoAdapter(List<ContenedorProductos> contenedorProductos, ListenerProductoAdapter listenerProductoAdapterAdapter) {
        this.listaDeProducto = listaDeProducto;
        this.listenerProductoAdapterAdapter = listenerProductoAdapterAdapter;
    }
    public ProductoAdapter( ListenerProductoAdapter listenerProductoAdapterAdapter) {
        this.listaDeProducto = new ArrayList<>();
        this.listenerProductoAdapterAdapter = listenerProductoAdapterAdapter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //buscamos el inflador desde el contexto.
        //lo guardamos en una variable para poder utilizarlo luego.
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());

        //inflamos la vista
        View viewCelda = inflador.inflate(R.layout.celdaproducto, parent, false);

        //creamos el viewholder del personaje pasandole la vista de la celda
        // lo mandamos a viewholder para que haga las búsquedas
        ProductoViewHolder productoViewHolder=  new ProductoViewHolder(viewCelda);

        //tiene que retornar un view holder
        return productoViewHolder;
    }

    public void setListaDeProducto(List<Producto> listaDeProducto) {
        this.listaDeProducto = listaDeProducto;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //este método se ejecuta cada vez que uno scrollea
        Producto producto =  listaDeProducto.get(position);
        //confía en mí que este viewholder es un personaje
        ProductoViewHolder productoViewHolderViewHolder = (ProductoViewHolder) holder;
        productoViewHolderViewHolder.bind(producto);

    }

    @Override
    public int getItemCount() {
        return listaDeProducto.size();
    }



    private class ProductoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewproducto;
        private TextView textViewNombreProducto;
        private TextView textViewPrecioProducto;
        private Producto producto;

        //ESTA ES LA PARTE XML

        public ProductoViewHolder(View itemView) {
            super(itemView);
            imageViewproducto = itemView.findViewById(R.id.celdaProducto_imageViewProducto);
            textViewNombreProducto = itemView.findViewById(R.id.celdaProducto_TextNombreProducto);
            textViewPrecioProducto = itemView.findViewById(R.id.celdaProducto_TextPrecioProducto);
            //5. le hacemos el OnClickListener a toda la celda (itemView)
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //6. al escuchador le tenemos que decir informar selección
                    listenerProductoAdapterAdapter.informarSeleccion(producto);

                }
            });

        }

        //ESTA ES LA PARTE DE PROGRAMACIÓN

        public void bind(Producto unProducto){
            //parte del paso 6
            this.producto = unProducto;
            textViewNombreProducto.setText(unProducto.getNombre());
            textViewPrecioProducto.setText(unProducto.getPrecio().toString());
            Picasso.get().load(unProducto.getFoto()).into(imageViewproducto);

        }

    }



    //1. definir una interface
    public interface ListenerProductoAdapter {
        //2. el método es lo que queremos comunicar
        public void informarSeleccion (Producto producto);

    }

}
