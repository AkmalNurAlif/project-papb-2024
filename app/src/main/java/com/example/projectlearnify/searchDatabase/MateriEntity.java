package com.example.projectlearnify.searchDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "materi_pelajaran")
public class MateriEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String judul;
    private String isi;

    public int getId() {
        return id;
    }

    public String getIsi() {
        return isi;
    }

    public String getJudul() {
        return judul;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
}
