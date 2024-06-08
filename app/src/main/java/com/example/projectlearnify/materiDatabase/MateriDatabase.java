package com.example.projectlearnify.materiDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Materi.class}, version = 1)
public abstract class MateriDatabase extends RoomDatabase {
    private static MateriDatabase instance;
    public abstract MateriDAO materiDao();

    public static synchronized MateriDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MateriDatabase.class, "Materi_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
