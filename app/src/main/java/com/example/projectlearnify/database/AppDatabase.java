package com.example.projectlearnify.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.projectlearnify.materiDatabase.MateriDAO;

@Database(entities = {UploadFile.class, UploadVideo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract UploadFileDao uploadFileDao();
    public abstract UploadVideoDao uploadVideoDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "App_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
