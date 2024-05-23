package com.example.projectlearnify.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.projectlearnify.R;
import com.example.projectlearnify.database.AppDatabase;
import com.example.projectlearnify.database.UploadVideo;


public class UploadVideoActivityFragment extends Fragment {

    private static final int PICK_FILE_REQUEST_CODE = 100;
    private AppDatabase db;

    public UploadVideoActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_uploadvideo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Your fragment initialization code here
        db = Room.databaseBuilder(requireContext(),
                AppDatabase.class, "upload_videos_database").build();

        Button btnPilihVideo = view.findViewById(R.id.btnPilihVideo);
        btnPilihVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseVideo(); // Memanggil metode yang benar
            }
        });
    }

    private void chooseVideo() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        try {
            startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Error: Cannot open file picker", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            Uri selectedFileUri = data.getData();
            Toast.makeText(requireContext(), "Selected file: " + selectedFileUri.toString(), Toast.LENGTH_SHORT).show();

            // Save the selected file to the local database
            saveVideoToDatabase(selectedFileUri.toString());
        }
    }

    private void saveVideoToDatabase(String videoFilePath) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        UploadVideo uploadVideo = new UploadVideo("Default Title", "Default Description", videoFilePath);
                        db.uploadVideoDao().insert(uploadVideo);
                    } catch (Exception e) {
                        e.printStackTrace();
                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(requireContext(), "Error: Failed to save video to database", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Error: Failed to start database save thread", Toast.LENGTH_SHORT).show();
        }
    }
}
