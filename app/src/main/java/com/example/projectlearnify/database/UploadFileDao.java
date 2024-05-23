package com.example.projectlearnify.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UploadFileDao {
    @Insert
    void insert(UploadFile uploadFile);

    @Query("SELECT * FROM upload_files")
    List<UploadFile> getAllUploadFiles();
}
