package com.example.projectlearnify;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.projectlearnify.database.AppDatabase;
import com.example.projectlearnify.database.UploadVideo;


public class UploadVideoActivity extends AppCompatActivity {
    private static final int PICK_FILE_REQUEST_CODE = 100;
    private Button btnSubmit;
    private View btnPilihVideo;
    private AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_uploadvideo);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "upload_videos_database").build();

        btnPilihVideo = findViewById(R.id.btnPilihVideo);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnPilihVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PilihVideo(); // Memanggil metode yang benar
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadVideoActivity.this, ConfirmationActivity.class);
                intent.putExtra("uploadType", "video");
                startActivity(intent);
            }
        });
    }

    private void PilihVideo() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedFileUri = data.getData();
            Toast.makeText(this, "Selected file: " + selectedFileUri.toString(), Toast.LENGTH_SHORT).show();

            saveVideoToDatabase(selectedFileUri.toString());
        }
    }

    private void saveVideoToDatabase(String videoFilePath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UploadVideo uploadVideo = new UploadVideo("Default Title", "Default Description", videoFilePath);
                db.uploadVideoDao().insert(uploadVideo);
            }
        }).start();
    }
}
