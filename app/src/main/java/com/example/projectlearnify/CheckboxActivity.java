package com.example.projectlearnify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CheckboxActivity extends AppCompatActivity {
    private CheckBox videoCheckBox;
    private CheckBox pdfCheckBox;
    private Button lanjutButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_checkbox);

        videoCheckBox = findViewById(R.id.checkBoxVideo);
        pdfCheckBox = findViewById(R.id.checkBoxPDF);
        lanjutButton = findViewById(R.id.button);

        lanjutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoCheckBox.isChecked()) {
                    Intent intent = new Intent(CheckboxActivity.this, UploadVideoActivity.class);
                    intent.putExtra("selection", "video");
                    startActivity(intent);
                } else if (pdfCheckBox.isChecked()) {
                    Intent intent = new Intent(CheckboxActivity.this, MainActivity.class);
                    intent.putExtra("selection", "pdf");
                    startActivity(intent);
                } else {
                }
            }
        });
    }
}

