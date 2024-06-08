package com.example.projectlearnify.materiFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectlearnify.materiDatabase.MateriDatabase;
import com.example.projectlearnify.materiDatabase.Materi;

import com.example.projectlearnify.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddMateri_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMateri_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText etNomorMateri, etJudulMateri, etJudulVideo, etDescVideo;
    private Button btSave;
    private MateriDatabase materiDatabase;

    public AddMateri_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMateri_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMateri_Fragment newInstance(String param1, String param2) {
        AddMateri_Fragment fragment = new AddMateri_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_materi_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        materiDatabase = MateriDatabase.getInstance(requireContext());

        btSave = view.findViewById(R.id.btSave);
        etNomorMateri = view.findViewById(R.id.etNomorMateri);
        etJudulMateri  = view.findViewById(R.id.etJudulMateri);
        etJudulVideo = view.findViewById(R.id.etJudulVideo);
        etDescVideo = view.findViewById(R.id.etDescVideo);


        btSave.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                Materi materi = new Materi();
                materi.noMateri = etNomorMateri.getText().toString();
                materi.titleMateri = etJudulMateri.getText().toString();
                materi.titleVideo = etJudulVideo.getText().toString();
                materi.descVideo = etDescVideo.getText().toString();

                materiDatabase.materiDao().insert(materi);
            }
        });
    }
}