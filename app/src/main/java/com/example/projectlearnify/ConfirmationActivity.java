package com.example.projectlearnify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectlearnify.database.UploadFile;
import com.example.projectlearnify.database.UploadFileFirebaseDao;

public class ConfirmationActivity extends AppCompatActivity {
    private Button kembaliButton;
    private Button unggahLagiButton;
    private String previousSelection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_confirmation);

        kembaliButton = findViewById(R.id.btnKembali);
        unggahLagiButton = findViewById(R.id.btnUnggah);

        kembaliButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        unggahLagiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (previousSelection != null && previousSelection.equals("video")) {
                    intent = new Intent(ConfirmationActivity.this, UploadVideoActivity.class);
                } else {
                    intent = new Intent(ConfirmationActivity.this, MainActivity.class);
                }
                startActivity(intent);
            }
        });
    }

    public void setPreviousSelection(String previousSelection) {
        this.previousSelection = previousSelection;
    }

    public void saveFileToDatabase(String filePath, String fileTitle, String fileDescription) {
        try {
            new Thread(() -> {
                try {
                    UploadFile uploadFile = new UploadFile(filePath, fileTitle, fileDescription);
                    UploadFileFirebaseDao.getInstance().insert(uploadFile);
                    runOnUiThread(() -> Toast.makeText(ConfirmationActivity.this, "File saved successfully", Toast.LENGTH_SHORT).show());
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(() -> Toast.makeText(ConfirmationActivity.this, "Error: Failed to save file to database", Toast.LENGTH_SHORT).show());
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ConfirmationActivity.this, "Error: Failed to start database save thread", Toast.LENGTH_SHORT).show();
        }
    }
}
