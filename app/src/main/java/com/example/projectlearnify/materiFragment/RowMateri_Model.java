package com.example.projectlearnify.materiFragment;

import android.net.Uri;

public class RowMateri_Model {
    private String tv_materi;
    private String tv_judulMateri;
    private Uri videoView;

    public RowMateri_Model(String tv_materi, String tv_judulMateri, Uri videoView) {
        this.tv_materi = tv_materi;
        this.tv_judulMateri = tv_judulMateri;
        this.videoView = videoView;
    }

    public String getTv_materi() {
        return tv_materi;
    }

    public void setTv_materi(String tv_materi) {
        this.tv_materi = tv_materi;
    }

    public String getTv_judulMateri() {
        return tv_judulMateri;
    }

    public void setTv_judulMateri(String tv_judulMateri) {
        this.tv_judulMateri = tv_judulMateri;
    }

    public Uri getVideoView() {
        return videoView;
    }

    public void setVideoView(Uri videoView) {
        this.videoView = videoView;
    }
}
