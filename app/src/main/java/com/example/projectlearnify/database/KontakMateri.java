package com.example.projectlearnify.database;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KontakMateri extends RecyclerView.Adapter {

    private String daftarMapel;

    public void Kontak(){

        this.daftarMapel = daftarMapel;
    }

    public String getMapel(){
        return daftarMapel;
    }

    public void setMapel(String string) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
