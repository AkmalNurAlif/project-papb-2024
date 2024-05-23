package com.example.projectlearnify.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "upload_files")
public class UploadFile {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "filePath")
    private String filePath;

    @ColumnInfo(name = "FileTitle")
    private String fileTitle;

    @ColumnInfo(name = "FileDescription")
    private String fileDescription;

    public UploadFile(String filePath, String fileTitle, String fileDescription) {
        this.filePath = filePath;
        this.fileTitle = fileTitle;
        this.fileDescription = fileDescription;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }
}
