package com.example.examenfinal.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favoritos")
public class Favoritos {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String resumen;

    @ColumnInfo(name = "image_url")
    public String imageurl;
}
