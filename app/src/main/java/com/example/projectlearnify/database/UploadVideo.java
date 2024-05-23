package com.example.projectlearnify.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "upload_videos")
public class UploadVideo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "videoTitle")
    private String videoTitle;
    @ColumnInfo(name = "videoDescription")
    private String videoDescription;
    @ColumnInfo(name = "videoFilePath")
    private String videoFilePath;

    public UploadVideo(String videoTitle, String videoDescription, String videoFilePath) {
        this.videoTitle = videoTitle;
        this.videoDescription = videoDescription;
        this.videoFilePath = videoFilePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getVideoFilePath() {
        return videoFilePath;
    }

    public void setVideoFilePath(String videoFilePath) {
        this.videoFilePath = videoFilePath;
    }
}
