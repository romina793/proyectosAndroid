package proyecto.romina.com.firebaserecargado;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG_FIREBASE = "TAG_FIREBASE";
    private EditText editTextEmail;
    private EditText editTextContraseña;
    private Button buttonLogin;
    private Button buttonRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.textView_email);
        editTextContraseña = findViewById(R.id.textView_contraseña);
        buttonLogin = findViewById(R.id.button_Login);
        buttonRegistrarse = findViewById(R.id.button_Registrase);

        buttonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearNuevoUsuario(editTextEmail.getText().toString(), editTextContraseña.getText().toString());
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loguearUsuario(editTextEmail.getText().toString(), editTextContraseña.getText().toString());
            }
        });
        //ESTO ES PARA DESLOGUEAR, PERO DEBERÍA ESTAR EN UN BOTÓN DE CERRAR SESIÓN
        mAuth.signOut();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            loginExioso();
        }

    }

    public void  crearNuevoUsuario(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG_FIREBASE, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            loginExioso();
                            // ESTO LO SACAMOS POR AHORA updateUI(user); porque depende de cada app, una vez logueado a dónde va USER INTERFACE
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG_FIREBASE, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // ESTO LO SACAMOS POR AHORA updateUI(null);
                        }

                        // ...
                    }
                });

    }


 public void loguearUsuario (String email, String password){

     mAuth.signInWithEmailAndPassword(email, password)
             .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()) {
                         // Sign in success, update UI with the signed-in user's information
                         Log.d(TAG_FIREBASE, "createUserWithEmail:success");
                         FirebaseUser user = mAuth.getCurrentUser();
                         loginExioso();

                     } else {
                         // If sign in fails, display a message to the user.
                         Log.w(TAG_FIREBASE, "createUserWithEmail:failure", task.getException());
                         Toast.makeText(LoginActivity.this, "Authentication failed.",
                                 Toast.LENGTH_SHORT).show();


                     // ...
                 }
             };





});}

    public  void loginExioso (){ //esto lo saco
        Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
        startActivity(intent);
        finish();
    }


}
