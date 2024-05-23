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
import com.example.projectlearnify.database.UploadFile;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST_CODE = 100;


    private Button btnSubmit;
    private View btnPilihFile;

    private AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "upload_files_database").build();

        btnPilihFile = findViewById(R.id.btnPilihFile);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnPilihFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PilihFile();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
                intent.putExtra("uploadType", "video");
                startActivity(intent);
            }
        });
    }

    public void PilihFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // You can set your specific file type here
        startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
    }

    private void saveFileToDatabase(String filePath) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        UploadFile uploadFile = new UploadFile(filePath, "Default Title", "Default Description");
                        db.uploadFileDao().insert(uploadFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Error: Failed to save file to database", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: Failed to start database save thread", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedFileUri = data.getData();
            Toast.makeText(this, "Selected file: " + selectedFileUri.toString(), Toast.LENGTH_SHORT).show();

            // Save the selected file to the local database
            saveFileToDatabase(selectedFileUri.toString());
        }
    }

}

