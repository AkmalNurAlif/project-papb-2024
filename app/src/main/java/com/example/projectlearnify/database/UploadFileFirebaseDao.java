package com.example.projectlearnify.database;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UploadFileFirebaseDao {
    private static UploadFileFirebaseDao instance;
    private DatabaseReference databaseReference;

    private UploadFileFirebaseDao() {
        // Initialize Firebase Database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("uploads");
    }

    public static synchronized UploadFileFirebaseDao getInstance() {
        if (instance == null) {
            instance = new UploadFileFirebaseDao();
        }
        return instance;
    }

    public void insert(UploadFile uploadFile) {
        String key = databaseReference.child("Files").push().getKey();
        if (key != null) {
            databaseReference.child("Files").child(key).setValue(uploadFile)
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
    public void getAllFileFromFirebase(final FirebaseUploadFileCallback callback) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<UploadFile> UploadFile = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UploadFile uploadFile = snapshot.getValue(UploadFile.class);
                    UploadFile.add(uploadFile);
                }
                callback.onDataLoaded(UploadFile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
            }
        });
    }
    public interface FirebaseUploadFileCallback {
        void onDataLoaded(List<UploadFile> progressUsers);
        void onError(String errorMessage);
    }
}


