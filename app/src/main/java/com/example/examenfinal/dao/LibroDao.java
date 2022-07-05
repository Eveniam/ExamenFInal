package com.example.examenfinal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.examenfinal.entities.Libro;

import java.util.List;

@Dao
public interface LibroDao {
    @Query("SELECT * FROM libro")
    List<Libro> getAll();

    @Query("SELECT * FROM Libro WHERE id = :id")
    Libro fin(int id);

    @Insert
    void create(Libro contact);

    @Query("DELETE FROM libro")
    void deleteAll();
}
