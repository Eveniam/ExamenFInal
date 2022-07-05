package com.example.examenfinal.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "libro")
public class Libro {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String resumen;
    public String coorde;

    @ColumnInfo(name = "image_url")
    public String imageurl;
}
