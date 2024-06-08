package com.example.projectlearnify.searchDatabase;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class MateriViewModel extends AndroidViewModel {
    private MateriRepository repository;
    private LiveData<List<MateriEntity>> allMateri;

    public MateriViewModel(Application application) {
        super(application);
        repository = new MateriRepository(application);
        allMateri = repository.getAllMateri();
    }

    public LiveData<List<MateriEntity>> getAllMateri() {
        return allMateri;
    }

    public LiveData<List<MateriEntity>> searchMateri(String keyword) {
        return repository.searchMateri(keyword);
    }

    public void insert(MateriEntity materi) {
        repository.insert(materi);
    }
}
