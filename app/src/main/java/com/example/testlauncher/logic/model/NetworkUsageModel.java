package com.example.testlauncher.logic.model;

// 网络使用情况统计模型
public class NetworkUsageModel {
    private static String appName;
    private static String uploadData;
    private static String downloadData;

    public NetworkUsageModel(String uploadData, String downloadData) {
        this.uploadData = uploadData;
        this.downloadData = downloadData;
    }

    // getters and setters
    public static String getUploadData() {
        return uploadData;
    }
    public void setUploadData(String uploadData) {
        this.uploadData = uploadData;
    }
    public static String getDownloadData() {
        return downloadData;
    }
    public void setDownloadData(String downloadData) {
        this.downloadData = downloadData;
    }

    public String getTotalData() {
        return uploadData + downloadData;
    }
    public void setTotalData(String uploadData,String downloadData) {
        this.uploadData = uploadData;
        this.downloadData = downloadData;
    }
}
