package com.example.digital.buscadormercadolibre.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.digital.buscadormercadolibre.R;
import com.example.digital.buscadormercadolibre.controller.ProductoController;
import com.example.digital.buscadormercadolibre.model.ContenedorProductos;
import com.example.digital.buscadormercadolibre.model.Producto;
import com.example.digital.buscadormercadolibre.util.ResultListerner;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductoAdapter.ListenerProductoAdapter {

    private Button buttonBuscar;
    private EditText editTextProductoBuscado;
    private RecyclerView recyclerViewProducto;
    private ProductoAdapter productoAdapter;
    private Button buttonCompartir;
    private EditText editTextCompartir;
    private CallbackManager callbackManager;
    private LoginButton loginButtonFacebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();

        loginButtonFacebook = (LoginButton) findViewById(R.id.login_button);
        loginButtonFacebook.setReadPermissions("email");

        // Callback registration
        loginButtonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(MainActivity.this, "LOGIN CORRECTO", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "LOGIN CALCELADO", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(MainActivity.this, "Ha ocurrido un horror", Toast.LENGTH_SHORT).show();
                    }
                });

                buttonBuscar = findViewById(R.id.button_buscardor);
        editTextProductoBuscado = findViewById(R.id.editText_buscador);
        recyclerViewProducto = findViewById(R.id.recyclerView_Productos);
        buttonCompartir = findViewById(R.id.button_compartir);
        editTextCompartir = findViewById(R.id.activityMain_EditTextCompartir);


    buttonCompartir.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_SUBJECT, "Compartir");

            String textoACompartir = editTextCompartir.getText().toString();

            share.putExtra(Intent.EXTRA_TEXT,textoACompartir);
            startActivity(Intent.createChooser(share, "Share Link"));

        }
    });

        productoAdapter = new ProductoAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);

        recyclerViewProducto.setLayoutManager(linearLayoutManager);
        recyclerViewProducto.setAdapter(productoAdapter);




        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductoController productoController = new ProductoController();
                String producto = editTextProductoBuscado.getText().toString();

                productoController.getProductoBySearch(producto, new ResultListerner<ContenedorProductos>() {
                    @Override
                    public void notificar(ContenedorProductos resultado) {
                        productoAdapter.setListaDeProducto(resultado.getResults());
                    }
                });

            }
        });
    }

    @Override
    public void informarSeleccion(Producto producto) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}

