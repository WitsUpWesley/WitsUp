package com.example.witsup;

import android.net.Uri;
public class FilePathh {
    String filePathhID, filePathhName;
   String filePathhURL;

    public FilePathh(String filePathhID, String filePathhName, String filePathhURL) {
        this.filePathhID = filePathhID;
        this.filePathhName = filePathhName;
        this.filePathhURL = filePathhURL;
    }

    public String getFilePathhID() {
        return filePathhID;
    }

    public String getFilePathhName() {
        return filePathhName;
    }

    public String getFilePathhURL() {
        return filePathhURL;
    }
}
