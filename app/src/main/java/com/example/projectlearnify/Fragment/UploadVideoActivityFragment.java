package com.example.projectlearnify.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectlearnify.R;
import com.example.projectlearnify.database.UploadVideo;
import com.example.projectlearnify.database.UploadVideoFirebaseDao;

public class UploadVideoActivityFragment extends Fragment {

    private static final int PICK_VIDEO_REQUEST_CODE = 101;
    private Button btnSubmit;
    private View btnPilihVideo;
    private EditText editTextNamaDokumen, editTextDeskripsiVideo;
    private String selectedFilePath;

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

        btnPilihVideo = view.findViewById(R.id.btnPilihVideo);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        editTextNamaDokumen = view.findViewById(R.id.editTextNamaDokumen);
        editTextDeskripsiVideo = view.findViewById(R.id.editTextDeskripsiDokumen);

        btnPilihVideo.setOnClickListener(v -> chooseVideo());

        btnSubmit.setOnClickListener(v -> {
            if (selectedFilePath != null) {
                String title = editTextNamaDokumen.getText().toString().trim();
                String description = editTextDeskripsiVideo.getText().toString().trim();

                if (!title.isEmpty() && !description.isEmpty()) {
                    saveVideoToDatabase(selectedFilePath, title, description);
                } else {
                    Toast.makeText(requireContext(), "Title and description cannot be empty", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(requireContext(), "No video selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void chooseVideo() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        startActivityForResult(intent, PICK_VIDEO_REQUEST_CODE);
    }

    private void saveVideoToDatabase(String videoFilePath, String videoTitle, String videoDescription) {
        new Thread(() -> {
            try {
                UploadVideo uploadVideo = new UploadVideo(videoTitle, videoDescription, videoFilePath);
                UploadVideoFirebaseDao.getInstance().insert(uploadVideo);
                requireActivity().runOnUiThread(() -> Toast.makeText(requireContext(), "Video saved successfully", Toast.LENGTH_SHORT).show());
            } catch (Exception e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(() -> Toast.makeText(requireContext(), "Error: Failed to save video to database", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_VIDEO_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedVideoUri = data.getData();
            if (selectedVideoUri != null) {
                selectedFilePath = selectedVideoUri.toString();
                Toast.makeText(requireContext(), "Selected video: " + selectedFilePath, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
