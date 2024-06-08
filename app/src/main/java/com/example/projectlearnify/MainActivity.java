package com.example.projectlearnify;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.projectlearnify.Fragment.ConfirmationActivityFragment;
import com.example.projectlearnify.Fragment.MainActivityFragment;
import com.example.projectlearnify.Fragment.UploadVideoActivityFragment;
import com.example.projectlearnify.database.UploadFile;
import com.example.projectlearnify.database.UploadFileFirebaseDao;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST_CODE = 100;
    private ViewPager2 viewPager;
    private FragmentStateAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);

        adapter = new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new ConfirmationActivityFragment();
                    case 1:
                        return new MainActivityFragment();
                    case 2:
                        return new UploadVideoActivityFragment();
                    default:
                        return null;
                }
            }

            @Override
            public int getItemCount() {
                return 3; // Three fragments
            }
        };

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Confirmation");
                            break;
                        case 1:
                            tab.setText("Main");
                            break;
                        case 2:
                            tab.setText("Upload Video");
                            break;
                    }
                }).attach();
    }

    public void PilihFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        try {
            startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: Cannot open file picker", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveFileToDatabase(String filePath) {
        try {
            new Thread(() -> {
                try {
                    UploadFile uploadFile = new UploadFile(filePath, "Default Title", "Default Description");
                    UploadFileFirebaseDao.getInstance().insert(uploadFile);
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "File saved successfully", Toast.LENGTH_SHORT).show());
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error: Failed to save file to database", Toast.LENGTH_SHORT).show());
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
