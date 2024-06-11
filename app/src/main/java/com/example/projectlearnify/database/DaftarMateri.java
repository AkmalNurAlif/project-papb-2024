package com.example.projectlearnify.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.projectlearnify.R;

import java.util.ArrayList;
import java.util.List;

class DaftarMateri extends AppCompatActivity {
    private List<Kontak> data;
    private RecyclerView rvKontak;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.data = new ArrayList<Kontak>();

        Kontak a = new Kontak();
        Kontak b = new Kontak();
        Kontak c = new Kontak();
        Kontak d = new Kontak();
        Kontak e = new Kontak();
        Kontak f = new Kontak();

        this.data.add(a);
        this.data.add(b);
        this.data.add(c);
        this.data.add(d);
        this.data.add(e);
        this.data.add(f);

        this.rvKontak = this.findViewById(R.id.rvDaftarMapel);

        KontakMateri KA = new KontakMateri();

        RecyclerView.LayoutManager lm = new LinearLayoutManager(DaftarMateri.this, LinearLayoutManager.VERTICAL, false);

        this.rvKontak.setLayoutManager(lm);
        this.rvKontak.setAdapter(KA);
    }
}