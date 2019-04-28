package proyecto.romina.com.firebaserecargado;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class Main2Activity extends AppCompatActivity {


    private Button botonBajarFoto;
    private Button botonSubirFoto;
    private ImageView imageViewFoto;
    private StorageReference mStorageRef;

   // private File imageFile;

    //ESTO ES STORAGE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        botonBajarFoto = findViewById(R.id.button_BajarFoto);
        botonSubirFoto = findViewById(R.id.button_SubirFoto);
        imageViewFoto = findViewById(R.id.imageView_ViewFoto);


        botonBajarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            bajarFoto();

            }
        });

        //AMBOS BOTONES ESTÁN HACIENDO LO MISMO (por eso lo cambiamos a bajar)

        botonSubirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ESTO ES SOLO PARA LA CÁMARA
                //EasyImage.openCamera(Main2Activity.this , 1);
                //  ESTO ES PARA ABRIR LA GALERÍA DEL TELÉFONO
                //EasyImage.openGallery(Main2Activity.this, 1);
                //choose es para elegir qué hacer al hacer click
                EasyImage.openChooserWithGallery(Main2Activity.this, "qué hacer", 1);
            }
        });


    }

    //ESTO ES PARA DELEGAR ACTIVIDADES A OTROS COMANDOS, COMO LOGIN DE FACEBOOK O GOOGLEMAPS
    //COPIAR EL CÓDIGO DE LA LIBRERÍA

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, Main2Activity.this, new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                imageViewFoto.setImageBitmap(myBitmap);
                //Main2Activity.this.imageFile = imageFile;
                Toast.makeText(Main2Activity.this, "FOTO TOMADA", Toast.LENGTH_SHORT).show();

                //para que la suba automáticamente

                subirFoto(imageFile);
            }

            //ESTO ES PARA SETEAR SI HAY ERRORES
     /*       @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                super.onImagePickerError(e, source, type);
            }*/
        });

    }


    public void subirFoto (File imageFile){

        Uri file = Uri.fromFile(imageFile);
        StorageReference riversRef = mStorageRef.child("image").child(imageFile.getName()); //ES EL NOMBRE DEL ARCHIVO
        //EL CHILD ES PARA LA REFERENCIA

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getUploadSessionUri();
                        Toast.makeText(Main2Activity.this, "FOTO SUBIDA CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        //creamos una exception, para imprimir en la consulta toda la lista de errores
                        exception.printStackTrace();
                        Toast.makeText(Main2Activity.this, "HA OCURRIDO UN ERROR", Toast.LENGTH_SHORT).show();

                        //HAY QUE HABILITAR STORAGE ANTES DESDE LA CONSOLA
                        // BORRAR ESTO EN LAS REGLAS DE LA CONSOLA :if request.auth != null
                    }
                });
    }


    public void bajarFoto (){

        //se puede traer directamente de glide o de picasso

       StorageReference hdtLondon = mStorageRef.child("images/hdt-london.jpg");

       File localFile = null;

       try {

           localFile = File.createTempFile("images", "jpg");

           final File finalLocalFile = localFile;

           hdtLondon.getFile(localFile)
                   .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                       @Override
                       public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                           // Successfully downloaded data to local file
                           // ...
                           Bitmap myBitmap = BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                           imageViewFoto.setImageBitmap(myBitmap);
                           Toast.makeText(Main2Activity.this, "FOTO BAJADA CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                       }
                   }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception exception) {
                   // Handle failed download
                   // ...
                   exception.printStackTrace();
                   Toast.makeText(Main2Activity.this, "HA OCURRIDO UN ERROR AL BAJAR LA FOTO", Toast.LENGTH_SHORT).show();

               }
           });
       }
       catch (IOException e){
           e.printStackTrace();
       }
    }

}
