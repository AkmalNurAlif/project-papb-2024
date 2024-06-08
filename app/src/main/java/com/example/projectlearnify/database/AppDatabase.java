package com.example.projectlearnify.database;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppDatabase {

    private static AppDatabase instance;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new AppDatabase();
        }
        return instance;
    }

    // Method to insert UploadFile and sync with Firebase
    public void insertUploadFileSync(final UploadFile uploadFile) {
        databaseWriteExecutor.execute(() -> {
            UploadFileFirebaseDao.getInstance().insert(uploadFile);
        });
    }

    // Method to insert UploadVideo and sync with Firebase
    public void insertUploadVideoSync(final UploadVideo uploadVideo) {
        databaseWriteExecutor.execute(() -> {
            UploadVideoFirebaseDao.getInstance().insert(uploadVideo);
        });
    }
}