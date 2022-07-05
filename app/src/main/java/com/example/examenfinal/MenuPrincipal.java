package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.examenfinal.entities.Libro;
import com.example.examenfinal.factories.RetrofitFactory;
import com.example.examenfinal.services.LibroService;

import retrofit2.Retrofit;

public class MenuPrincipal extends AppCompatActivity {

    static  final int REQUEST_IMAGE_CAPTURE = 1000;
    static final int REQUEST_CAMERA_PERMISSION = 100;

    private EditText editTitle;
    private EditText editSynopsis;
    private ImageView ivImage;
    private String imageBASE64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        Button btnTodos = findViewById(R.id.btnTodos);
        Button btnFav = findViewById(R.id.btnFav);

        btnTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FavoritAvtivity.class);
                startActivity(intent);
            }
        });

    }
}