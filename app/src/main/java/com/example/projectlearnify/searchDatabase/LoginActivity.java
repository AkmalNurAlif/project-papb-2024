package com.example.projectlearnify.searchDatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectlearnify.R;
import com.example.projectlearnify.SignUpPelajarActivity;
import com.example.projectlearnify.SignUpPengajarActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView tvMasuk = findViewById(R.id.tvMasuk);
        tvMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRoleSelectionDialog();
            }
        });
    }

    private void showRoleSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Siapa anda ?")
                .setItems(new CharSequence[]{"Pengajar", "Siswa"}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent;
                        if (which == 0) {
                            intent = new Intent(LoginActivity.this, SignUpPengajarActivity.class);
                        } else {
                            intent = new Intent(LoginActivity.this, SignUpPelajarActivity.class);
                        }
                        startActivity(intent);
                    }
                });
        builder.create().show();
    }
}
