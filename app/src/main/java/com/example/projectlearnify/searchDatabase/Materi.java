package com.example.projectlearnify.searchDatabase;

public class Materi {
    private String judul;
    private String isi;

    public Materi() {
        // Diperlukan untuk Firebase
    }

    public Materi(String judul, String isi) {
        this.judul = judul;
        this.isi = isi;
    }

    public String getJudul() {
        return judul;
    }

    public String getIsi() {
        return isi;
    }
}
