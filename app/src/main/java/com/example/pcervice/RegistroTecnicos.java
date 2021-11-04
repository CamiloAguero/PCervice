package com.example.pcervice;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class RegistroTecnicos extends AppCompatActivity {

    //Generar atributo
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int GALLERY_INTENT = 2;
    //Referencia conexion firebase
    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tecnicos);
        //Recuperacion de instancia
        mStorage = FirebaseStorage.getInstance().getReference();
    }

    //Metodo para tomar la foto
    public void tomar_foto (View v){
        Intent take_picture_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(take_picture_intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(take_picture_intent,REQUEST_IMAGE_CAPTURE);
        }
    }

    public void subir_foto (View v){
        Permisos p = new Permisos(getApplicationContext());
        if(p.checkPermissionREAD_EXTERNAL_STORAGE(this)){
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
            startActivityForResult(i,GALLERY_INTENT);
        }
    }

    public void cargar_foto(View v){
        //Recuperar la uri
        ImageView foto = (ImageView) findViewById(R.id.foto_tecnicos);
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/pcervice-2ec29.appspot.com/o/user%2FMujjer%202.jpg?alt=media&token=247c9f62-4d58-4c8b-81c8-5e4a13492618")
                .fitCenter()
                .centerCrop()
                .into(foto);
        foto.getLayoutParams().width = 400;
        foto.getLayoutParams().height = 400;
    }

    public void pregunta(View v){
        Intent i = new Intent(this, PreguntaRC.class);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap image_bitmap = (Bitmap) extras.get("data");
            ImageView foto = (ImageView) findViewById(R.id.foto_tecnicos);
            foto.setImageBitmap(Bitmap.createScaledBitmap(image_bitmap,400,400,false));
            StorageReference foto_ref = mStorage.child("image/foto.jpg");
            //Generar arreglo de bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //Convierto el bitmap al formato y a la calidad que deseamos
            image_bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] data1 = baos.toByteArray();
            //Subo a firebase la foto
            UploadTask uptask = foto_ref.putBytes(data1);
        }else if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            Uri uri = data.getData();
            StorageReference file_path = mStorage.child("fotos").child(uri.getLastPathSegment());
            file_path.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), "Foto subida", Toast.LENGTH_SHORT).show();

                    file_path.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            ImageView foto = (ImageView) findViewById(R.id.foto_tecnicos);
                            Glide.with(RegistroTecnicos.this)
                                    .load(uri)
                                    .fitCenter()
                                    .centerCrop()
                                    .into(foto);
                            foto.getLayoutParams().width = 400;
                            foto.getLayoutParams().height = 400;
                        }
                    });
                }
            });
        }
    }
}