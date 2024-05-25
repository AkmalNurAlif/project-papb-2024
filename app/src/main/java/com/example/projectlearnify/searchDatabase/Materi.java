package com.example.projectlearnify.searchDatabase;

public class Materi {

    public String Judul;

    public String Isi;

    public Materi(String judul, String isi){
        this.Judul = judul;
        this.Isi = isi;
    }

    public String getJudul() {
        return Judul;
    }

    public String getIsi() {
        return Isi;
    }
}
