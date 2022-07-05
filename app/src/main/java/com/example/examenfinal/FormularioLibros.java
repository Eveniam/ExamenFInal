package com.example.examenfinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.examenfinal.entities.Image;
import com.example.examenfinal.entities.ImagePost;
import com.example.examenfinal.entities.Libro;
import com.example.examenfinal.factories.RetrofitFactory;
import com.example.examenfinal.factories.RetrofitImageFactory;
import com.example.examenfinal.services.ImageService;
import com.example.examenfinal.services.LibroService;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FormularioLibros extends AppCompatActivity {

    static  final int REQUEST_IMAGE_CAPTURE = 1000;
    static final int REQUEST_CAMERA_PERMISSION = 100;

    private EditText editTitle;
    private EditText editResumen;
    private EditText editCoordenadas;
    private ImageView ivImage;
    private String imageBASE64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_libros);

        Button btnSave = findViewById(R.id.btnGuardar);
        Button btnTakePhoto = findViewById(R.id.btnFoto);

        editTitle = findViewById(R.id.txtTitle);
        editResumen = findViewById(R.id.txtResumen);
        editCoordenadas = findViewById(R.id.txtCoordenadas);
        ivImage = findViewById(R.id.ivImage);

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    takePhoto();
                } else{
                    requestPermissions(new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLibro();
            }
        });
    }

    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try{
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e){

        }
    }

    private void saveLibro() {
        Retrofit retrofit = RetrofitFactory.build(getApplicationContext());

        LibroService service = retrofit.create(LibroService.class);

        Libro libro = new Libro();
        libro.title = String.valueOf(editTitle.getText());
        libro.resumen = String.valueOf(editResumen.getText());
        libro.coorde = String.valueOf(editCoordenadas.getText());



        ImagePost imagePost = new ImagePost();
        imagePost.image = imageBASE64;
        Retrofit retrofitimage = RetrofitImageFactory.build(getApplicationContext());
        ImageService imageService = retrofitimage.create(ImageService.class);

        Call<Image> callImage = imageService.create(imagePost);
        callImage.enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {

                libro.imageurl = response.body().data.link;

                Call<Libro> callMovie = service.create(libro);
                callMovie.enqueue(new Callback<Libro>() {
                    @Override
                    public void onResponse(Call<Libro> call, Response<Libro> response) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Libro> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            imageBASE64 = android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT);

            ivImage.setImageBitmap(imageBitmap);
        }
    }
}