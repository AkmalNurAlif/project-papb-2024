package com.example.projectlearnify.firebase;

public class VideoMateriData {
    private String fileVid;
    private String judulVid;

    private String descVid;
    private String key;

    public VideoMateriData(String fileVid, String judulVid, String descVid, String key) {
        this.fileVid = fileVid;
        this.judulVid = judulVid;
        this.descVid = descVid;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFileVid() {
        return fileVid;
    }

    public void setFileVid(String fileVid) {
        this.fileVid = fileVid;
    }

    public String getJudulVid() {
        return judulVid;
    }

    public void setJudulVid(String judulVid) {
        this.judulVid = judulVid;
    }

    public String getDescVid() {
        return descVid;
    }

    public void setDescVid(String descVid) {
        this.descVid = descVid;
    }

}
