package com.example.examenfinal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinal.adapters.LibroAdapter;
import com.example.examenfinal.database.AppDatabase;
import com.example.examenfinal.entities.Libro;

import java.util.List;

public class FavoritAvtivity extends AppCompatActivity {
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_activity);

    }
}
