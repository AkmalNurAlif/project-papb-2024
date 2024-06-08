package com.example.projectlearnify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectlearnify.searchDatabase.LoginActivity;

public class SignUpPengajarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_pengajar);

        TextView tvMasuk = findViewById(R.id.tvMasuk);
        tvMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpPengajarActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
