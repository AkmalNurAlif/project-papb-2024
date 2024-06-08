package com.example.projectlearnify;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectlearnify.database.UploadVideo;
import com.example.projectlearnify.database.UploadVideoFirebaseDao;

public class UploadVideoActivity extends AppCompatActivity {
    private static final int PICK_FILE_REQUEST_CODE = 100;
    private Button btnSubmit;
    private View btnPilihVideo;
    private String selectedFilePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_uploadvideo);

        btnPilihVideo = findViewById(R.id.btnPilihVideo);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnPilihVideo.setOnClickListener(v -> PilihVideo());

        btnSubmit.setOnClickListener(v -> {
            if (selectedFilePath != null) {
                saveVideoToDatabase(selectedFilePath);
                Intent intent = new Intent(UploadVideoActivity.this, ConfirmationActivity.class);
                intent.putExtra("uploadType", "video");
                startActivity(intent);
            } else {
                Toast.makeText(UploadVideoActivity.this, "No video selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void PilihVideo() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedFileUri = data.getData();
            if (selectedFileUri != null) {
                selectedFilePath = selectedFileUri.toString();
                Toast.makeText(this, "Selected file: " + selectedFilePath, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveVideoToDatabase(String videoFilePath) {
        new Thread(() -> {
            try {
                UploadVideo uploadVideo = new UploadVideo("Default Title", "Default Description", videoFilePath);
                UploadVideoFirebaseDao.getInstance().insert(uploadVideo);
                runOnUiThread(() -> Toast.makeText(UploadVideoActivity.this, "Video saved successfully", Toast.LENGTH_SHORT).show());
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(UploadVideoActivity.this, "Error: Failed to save video to database", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}
