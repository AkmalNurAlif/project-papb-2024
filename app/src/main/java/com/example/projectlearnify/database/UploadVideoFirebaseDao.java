package com.example.projectlearnify.database;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UploadVideoFirebaseDao {

    private static UploadVideoFirebaseDao instance;
    private DatabaseReference databaseReference;

    private UploadVideoFirebaseDao() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("uploads/videos");
    }

    public static synchronized UploadVideoFirebaseDao getInstance() {
        if (instance == null) {
            instance = new UploadVideoFirebaseDao();
        }
        return instance;
    }

    public void insert(UploadVideo uploadVideo) {
        String key = databaseReference.push().getKey();
        if (key != null) {
            databaseReference.child(key).setValue(uploadVideo)
                    .addOnSuccessListener(aVoid -> {
                        // Data saved successfully
                        System.out.println("Data saved successfully");
                    })
                    .addOnFailureListener(e -> {
                        // Failed to save data
                        System.out.println("Failed to save data: " + e.getMessage());
                    });
        }
    }
    public void getAllVideoFromFirebase(final FirebaseUploadVideoCallback callback) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<UploadVideo> UploadVideo = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UploadVideo uploadVideo = snapshot.getValue(UploadVideo.class);
                    UploadVideo.add(uploadVideo);
                }
                callback.onDataLoaded(UploadVideo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
            }
        });
    }
    public interface FirebaseUploadVideoCallback {
        void onDataLoaded(List<UploadVideo> UploadVideo);
        void onError(String errorMessage);
    }
}
