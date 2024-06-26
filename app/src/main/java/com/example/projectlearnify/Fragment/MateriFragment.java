package com.example.projectlearnify.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectlearnify.R;
import com.example.projectlearnify.searchDatabase.CobaIntent;
import com.example.projectlearnify.searchDatabase.Materi;
import com.example.projectlearnify.searchDatabase.MateriAdapter;


import java.util.ArrayList;

public class MateriFragment extends Fragment {

    private ArrayList<Materi> materiiFiltered;
    private String[] judulMateri;
    private String[] isiMateri;
    private RecyclerView recyclerView;

    public MateriFragment() {
    }

    public static MateriFragment newInstance(String param1, String param2) {
        MateriFragment fragment = new MateriFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString("param1");
            String mParam2 = getArguments().getString("param2");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_materi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        recyclerView = view.findViewById(R.id.rvFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        MateriAdapter materiAdapter = new MateriAdapter(getContext(), judulMateri, isiMateri);
        recyclerView.setAdapter(materiAdapter);

        materiAdapter.setOnItemClickListener(new MateriAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Materi materi = materiiFiltered.get(position);

                Intent intent = new Intent(getContext(), CobaIntent.class);
                intent.putExtra("materi_judul", materi.getJudul());
                intent.putExtra("materi_isi", materi.getIsi());
                startActivity(intent);
            }
        });

        SearchView searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                materiAdapter.filter(newText);
                return true;
            }
        });
    }

    private void dataInitialize() {
        materiiFiltered = new ArrayList<>();

        judulMateri = getResources().getStringArray(R.array.judul_materi_array);
        isiMateri = getResources().getStringArray(R.array.isi_materi_array);

        for (int i = 0; i < judulMateri.length; i++){
            Materi materi = new Materi(judulMateri[i], isiMateri[i]);
            materiiFiltered.add(materi);
        }
    }
}

