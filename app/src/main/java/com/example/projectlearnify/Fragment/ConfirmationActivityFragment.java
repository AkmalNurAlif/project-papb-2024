package com.example.projectlearnify.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectlearnify.MainActivity;
import com.example.projectlearnify.R;
import com.example.projectlearnify.UploadVideoActivity;


public class ConfirmationActivityFragment extends Fragment {
    private Button kembaliButton;
    private Button unggahLagiButton;
    private String previousSelection;

    public ConfirmationActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_confirmation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        kembaliButton = view.findViewById(R.id.btnKembali);
        unggahLagiButton = view.findViewById(R.id.btnUnggah);

        kembaliButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
            }
        });

        unggahLagiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (previousSelection.equals("video")) {
                    intent = new Intent(requireContext(), UploadVideoActivity.class);
                } else {
                    intent = new Intent(requireContext(), MainActivity.class);
                }
                requireActivity().startActivity(intent);
            }
        });
    }
}
