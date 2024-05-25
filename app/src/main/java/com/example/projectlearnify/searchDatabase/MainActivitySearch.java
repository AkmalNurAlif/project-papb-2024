package com.example.projectlearnify.searchDatabase;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectlearnify.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivitySearch extends AppCompatActivity {

    private List<Materi> data;
    private RecyclerView rvMateri;
    private MateriAdapter materiAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<>();

        Materi materi1 = new Materi("Cerita Pendek", "Cerita pendek adalah bentuk narasi belaka yang memusatkan cerita dalam lingkup yang lebih singkat dibandingkan dengan novel.");
        Materi materi2 = new Materi("Puisi", "Puisi adalah bentuk ekspresi sastra yang dipadukan dengan irama dan pemilihan kata yang indah.");
        Materi materi3 = new Materi("Novel", "Novel adalah karya sastra yang menggambarkan kehidupan dan konflik karakter dalam cerita yang panjang.");

        data.add(materi1);
        data.add(materi2);
        data.add(materi3);
        data.add(materi1);
        data.add(materi2);
        data.add(materi3);
        data.add(materi1);

        rvMateri = findViewById(R.id.rvMateri);

        materiAdapter = new MateriAdapter(MainActivitySearch.this, data);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivitySearch.this);
        rvMateri.setLayoutManager(layoutManager);
        rvMateri.setAdapter(materiAdapter);

        SearchView searchView = findViewById(R.id.search_view);
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
}
