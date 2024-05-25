package com.example.projectlearnify;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectlearnify.materiDatabase.Materi;
import com.example.projectlearnify.materiDatabase.MateriDatabase;

public class AddMateriActivity extends AppCompatActivity {
    private EditText etNomorMateri, etJudulMateri, etJudulVideo, etDescVideo;
    private Button btSave;
    private MateriDatabase materiDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_materi);

        etNomorMateri = findViewById(R.id.etNomorMateri);
        etJudulMateri = findViewById(R.id.etJudulMateri);
        etJudulVideo = findViewById(R.id.etJudulVideo);
        etDescVideo = findViewById(R.id.etDescVideo);

        btSave = findViewById(R.id.btSave);

        materiDatabase = MateriDatabase.getInstance(getApplicationContext());
        //Intent intent = getIntent();

        btSave.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                Materi materi = new Materi();
                materi.noMateri = etNomorMateri.getText().toString();
                materi.titleMateri = etJudulMateri.getText().toString();
                materi.titleVideo = etJudulVideo.getText().toString();
                materi.descVideo = etDescVideo.getText().toString();

                materiDatabase.materiDao().insert(materi);
                finish();
            }
        });

    }
}
