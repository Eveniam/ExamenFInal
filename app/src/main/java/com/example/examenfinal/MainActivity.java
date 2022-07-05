package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.examenfinal.adapters.LibroAdapter;
import com.example.examenfinal.database.AppDatabase;
import com.example.examenfinal.entities.Libro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FormularioLibros.class);
                startActivity(intent);
            }
        });

        db = AppDatabase.getDatabase(getApplicationContext());
        List<Libro> libros = db.libroDao().getAll();

        LibroAdapter adapter = new LibroAdapter(libros);
        RecyclerView rvMovies = findViewById(R.id.rvLibros);
        rvMovies.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvMovies.setHasFixedSize(true);
        rvMovies.setAdapter(adapter);
    }
}
