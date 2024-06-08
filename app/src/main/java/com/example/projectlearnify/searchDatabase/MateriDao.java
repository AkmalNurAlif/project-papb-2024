package com.example.projectlearnify.searchDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

@Dao
public interface MateriDao {
    @Insert
    void insert(MateriEntity materi);

    @Query("SELECT * FROM materi_pelajaran WHERE judul LIKE :keyword OR isi LIKE :keyword")
    LiveData<List<MateriEntity>> searchMateri(String keyword);

    @Query("SELECT * FROM materi_pelajaran")
    LiveData<List<MateriEntity>> getAllMateri();
}
