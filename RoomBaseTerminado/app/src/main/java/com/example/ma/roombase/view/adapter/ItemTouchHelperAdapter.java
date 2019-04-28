package com.example.ma.roombase.view.adapter;

/**
 * Created by Anthony on 13/11/2017.
 */

public interface ItemTouchHelperAdapter {
    public void onMove(int posicionAnterior, int posicionPosterior);
    public void  onSwipe(int posicion);
}
