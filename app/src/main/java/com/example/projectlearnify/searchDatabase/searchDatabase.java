package com.example.projectlearnify.searchDatabase;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MateriEntity.class}, version = 1)
public abstract class searchDatabase extends RoomDatabase {
    private static volatile searchDatabase instance;

    public abstract MateriDao materiDao();
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static searchDatabase getInstance(Application application) {
        if (instance == null) {
            synchronized (searchDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(application, searchDatabase.class, "materi_pelajaran")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
