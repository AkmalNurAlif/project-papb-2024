package com.example.projectlearnify.materiDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.RoomDatabase;

@Entity
public class Materi{
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "nomor materi")
    public String noMateri;
    @ColumnInfo(name = "judul materi")
    public String titleMateri;

}
