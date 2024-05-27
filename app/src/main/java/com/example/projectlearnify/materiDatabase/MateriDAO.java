package com.example.projectlearnify.materiDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Collection;

@Dao
public interface MateriDAO {
    @Query("SELECT * FROM materi")
    Collection<? extends Materi> getAll();

    @Insert
    void insert(Materi materi);

    @Delete
    void delete(Materi materi);
}
