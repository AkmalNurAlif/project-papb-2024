package com.example.projectlearnify.materiDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projectlearnify.materiDatabase.Materi;

import java.util.List;

@Dao
public interface MateriDAO {
    @Query("SELECT * FROM materi")
    List<Materi> getAll();

    @Insert
    void insert(Materi materi);

    @Delete
    void delete(Materi materi);
}
