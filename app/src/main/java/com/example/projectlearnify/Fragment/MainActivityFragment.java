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

import com.example.projectlearnify.ConfirmationActivity;
import com.example.projectlearnify.R;
import com.example.projectlearnify.database.UploadFile;
import com.example.projectlearnify.database.UploadFileFirebaseDao;

public class MainActivityFragment extends Fragment {

    private static final int PICK_FILE_REQUEST_CODE = 100;
    private Button btnSubmit;
    private View btnPilihFile;
    private EditText editTextNamaDokumen;
    private EditText editTextDeskripsiDokumen;
    private String selectedFilePath;

    public MainActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_fragment_main_activity, container, false);

        btnPilihFile = view.findViewById(R.id.btnPilihFile);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        editTextNamaDokumen = view.findViewById(R.id.editTextNamaDokumen);
        editTextDeskripsiDokumen = view.findViewById(R.id.editTextDeskripsiDokumen);

        btnPilihFile.setOnClickListener(v -> chooseFile(requireActivity()));

        btnSubmit.setOnClickListener(v -> {
            if (selectedFilePath != null) {
                String title = editTextNamaDokumen.getText().toString().trim();
                String description = editTextDeskripsiDokumen.getText().toString().trim();
                if (!title.isEmpty() && !description.isEmpty()) {
                    saveFileToDatabase(selectedFilePath, title, description);
                } else {
                    Toast.makeText(requireContext(), "Please enter title and description", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(requireContext(), "No file selected", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void chooseFile(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        try {
            startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "Error: Cannot open file picker", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveFileToDatabase(String filePath, String fileTitle, String fileDescription) {
        new Thread(() -> {
            try {
                UploadFile uploadFile = new UploadFile(fileTitle, fileDescription, filePath);
                UploadFileFirebaseDao.getInstance().insert(uploadFile);
                requireActivity().runOnUiThread(() -> Toast.makeText(requireContext(), "File saved successfully", Toast.LENGTH_SHORT).show());
            } catch (Exception e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(() -> Toast.makeText(requireContext(), "Error: Failed to save file to database", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedFileUri = data.getData();
            if (selectedFileUri != null) {
                selectedFilePath = selectedFileUri.toString();
                Toast.makeText(requireContext(), "Selected file: " + selectedFilePath, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
