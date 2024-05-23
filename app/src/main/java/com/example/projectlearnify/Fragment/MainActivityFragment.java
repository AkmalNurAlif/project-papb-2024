package com.example.projectlearnify.Fragment;

import android.app.Activity;
import android.content.Intent;
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
import com.example.projectlearnify.database.UploadFile;


public class MainActivityFragment extends Fragment {

    private static final int PICK_FILE_REQUEST_CODE = 100;
    private Button btnSubmit;
    private View btnPilihFile;
    private AppDatabase db;

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

        btnPilihFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile(requireActivity());
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Your fragment initialization code here
        db = Room.databaseBuilder(requireContext(),
                AppDatabase.class, "upload_files_database").build();
    }

    public void chooseFile(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        try {
            activity.startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "Error: Cannot open file picker", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveFileToDatabase(String filePath) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        UploadFile uploadFile = new UploadFile(filePath, "Default Title", "Default Description");
                        db.uploadFileDao().insert(uploadFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(requireContext(), "Error: Failed to save file to database", Toast.LENGTH_SHORT).show();
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
