package com.example.projectlearnify.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UploadVideoDao {
    @Insert
    void insert(UploadVideo uploadVideo);

    @Query("SELECT * FROM upload_videos")
    List<UploadVideo> getAllUploadVideos();
}

