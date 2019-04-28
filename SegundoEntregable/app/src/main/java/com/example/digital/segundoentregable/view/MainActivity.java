package com.example.digital.segundoentregable.view;


import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digital.segundoentregable.R;
import com.example.digital.segundoentregable.model.Receta;

public class MainActivity extends AppCompatActivity implements RecyclerViewFragmentReceta.ListenerRecyclerViewFragment, FragmentRecetaDetalle.ListenerFragmentReceta {


    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerViewRecetas;


    public static final String KEY_TITULO = "KEY_TITULO";
    private ImageView activityDetalle_imageView;
    private TextView textViewTituloReceta;
    private TextView textViewIngredientesReceta;
    private TextView textViewPreparacionReceta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);

        activityDetalle_imageView = findViewById(R.id.fragmentDetalle_imageView);
        textViewTituloReceta = findViewById(R.id.fragmentDetalle_textViewNombre);
        textViewIngredientesReceta = findViewById(R.id.celdaReceta_TextIngredienteReceta);
        textViewPreparacionReceta = findViewById(R.id.celdaReceta_TextPreparacionteReceta);

        navigationView = findViewById(R.id.navegation_View);
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerViewRecetas = findViewById(R.id.recycler_View_Receta);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {




            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.item_recetas:
                        setearFragment(new RecyclerViewFragmentReceta());


                        break;

                    case R.id.item_about_Us:
                        setearFragment(new AboutUsFragment());

                        break;
                    default:
                        Toast.makeText(MainActivity.this, "OPCIÓN INVÁLIDA", Toast.LENGTH_SHORT).show();

                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }

    public void setearFragment(Fragment fragmentGenerico) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, fragmentGenerico).commit();
    }

    @Override
    public void informarSeleccion(Receta receta) {

        Bundle bundle = new Bundle();

        bundle.putSerializable(FragmentRecetaDetalle.KEY_TITULO, receta);


        FragmentRecetaDetalle fragmentRecetaDetalle = new FragmentRecetaDetalle();

        //Seteo al fragment el bundle
        fragmentRecetaDetalle.setArguments(bundle);

        setearFragment(fragmentRecetaDetalle);


        Toast.makeText(this, "FRAGMENT DETALLE", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void notificar() {

    }
}
