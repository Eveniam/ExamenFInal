package com.example.examenfinal.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.examenfinal.dao.LibroDao;
import com.example.examenfinal.entities.Libro;

@Database(entities = {Libro.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LibroDao libroDao();

    public static AppDatabase getDatabase(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "com.example.examenfinal.database.examenFinal.db")
                .allowMainThreadQueries()
                .build();
    }
}
