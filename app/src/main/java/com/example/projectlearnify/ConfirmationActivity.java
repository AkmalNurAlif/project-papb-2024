package com.example.projectlearnify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.projectlearnify.database.AppDatabase;


public class ConfirmationActivity extends AppCompatActivity {
    private Button kembaliButton;
    private Button unggahLagiButton;
    private String previousSelection;
    private AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_confirmation);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "upload_files_database").build();

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
                if (previousSelection.equals("video")) {
                    intent = new Intent(ConfirmationActivity.this, UploadVideoActivity.class);
                } else {
                    intent = new Intent(ConfirmationActivity.this, MainActivity.class);
                }
                startActivity(intent);
            }
        });
    }
}
