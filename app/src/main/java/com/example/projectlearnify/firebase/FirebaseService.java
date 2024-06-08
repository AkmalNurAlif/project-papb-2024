package com.example.projectlearnify.firebase;

import com.example.projectlearnify.database.UploadFile;
import com.example.projectlearnify.database.UploadVideo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.function.Consumer;

public class FirebaseService {

    private DatabaseReference database;

    public FirebaseService() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    public void insertUploadFile(UploadFile uploadFile) {
        database.child("upload_files").push().setValue(uploadFile);
    }

    public void getAllUploadFiles(Consumer<List<UploadFile>> callback) {
        // Logic to retrieve all UploadFiles from Firebase and pass them to callback
    }

    public void insertUploadVideo(UploadVideo uploadVideo) {
        database.child("upload_videos").push().setValue(uploadVideo);
    }

    public void getAllUploadVideos(Consumer<List<UploadVideo>> callback) {
        // Logic to retrieve all UploadVideos from Firebase and pass them to callback
    }
}
