package com.example.projectlearnify.searchDatabase;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class MateriRepository {
    private MateriDao materiDao;
    private LiveData<List<MateriEntity>> allMateri;

    public MateriRepository(Application application) {
        searchDatabase database = searchDatabase.getInstance(application);
        materiDao = database.materiDao();
        allMateri = materiDao.getAllMateri();
    }

    public LiveData<List<MateriEntity>> getAllMateri() {
        return allMateri;
    }

    public void insert(MateriEntity materi) {
        searchDatabase.databaseWriteExecutor.execute(() -> {
            materiDao.insert(materi);
        });
    }

    public LiveData<List<MateriEntity>> searchMateri(String keyword) {
        return materiDao.searchMateri(keyword);
    }
}
