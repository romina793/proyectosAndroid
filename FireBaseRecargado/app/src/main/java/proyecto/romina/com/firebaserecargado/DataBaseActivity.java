package proyecto.romina.com.firebaserecargado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

public class DataBaseActivity extends AppCompatActivity {

    private FirebaseDatabase database;

    private Button botoncargarContactos;
    private Button botonAgregarContacto;
    private EditText editTextNombre;
    private EditText editTextApellido;
    private EditText editTextTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

         database = FirebaseDatabase.getInstance();

        botoncargarContactos = findViewById(R.id.button_agregarContacto);

        botonAgregarContacto = findViewById(R.id.button_TraerContactos);
        editTextNombre = findViewById(R.id.textView_nombre);
        editTextApellido = findViewById(R.id.textView_apellido);
        editTextTelefono = findViewById(R.id.textView_telefono);

        botoncargarContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacto contactoCargar = new Contacto(editTextNombre.getText().toString(), editTextApellido.getText().toString(), editTextTelefono.getText().toString());

                cargarContactoEnFirebase(contactoCargar);
            }
        });

    }

    public void cargarContactoEnFirebase (Contacto contacto){

        //SE PUEDE USAR EL .PUSH() CUANDO NO ME INTERESA LA INFO EN DETALLE. 

        database.getReference("Contactos").child(contacto.getNombre()).setValue(contacto);


    }

}
