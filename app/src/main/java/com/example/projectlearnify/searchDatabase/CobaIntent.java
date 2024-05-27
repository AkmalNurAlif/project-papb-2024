package com.example.projectlearnify.searchDatabase;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectlearnify.R;

public class CobaIntent extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba_intent);

        String judulMateri = getIntent().getStringExtra("materi_judul");
        String isiMateri = getIntent().getStringExtra("materi_isi");

        TextView textView = findViewById(R.id.textView);
        textView.setText("Judul: " + judulMateri + "\nIsi: " + isiMateri);
    }

}
