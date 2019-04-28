package com.example.digital.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class DetalleActivity extends AppCompatActivity {

    TextView textViewPersonaje;
    ImageView imageViewPersonaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Personaje persona = (Personaje) bundle.getSerializable("unPersonaje");
        textViewPersonaje = findViewById(R.id.activityDetalle_textViewTalento);
        textViewPersonaje.setText(persona.getNombre());
        imageViewPersonaje = findViewById(R.id.activityDetalle_imageView);
        imageViewPersonaje.setImageResource(persona.getImagen());
    }
}
