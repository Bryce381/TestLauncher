package com.example.testlauncher.logic.model;

// 网络使用情况统计模型
public class NetworkUsageModel {
    private static String appName;
    private static long uploadData;
    private static long downloadData;

    public NetworkUsageModel(String appName, long uploadData, long downloadData) {
        this.appName = appName;
        this.uploadData = uploadData;
        this.downloadData = downloadData;
    }

    // getters and setters
    public static String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public static long getUploadData() {
        return uploadData;
    }
    public void setUploadData(long uploadData) {
        this.uploadData = uploadData;
    }
    public static long getDownloadData() {
        return downloadData;
    }
    public void setDownloadData(long downloadData) {
        this.downloadData = downloadData;
    }
    public long getTotalData() {
        return uploadData + downloadData;
    }
    public void setTotalData(long totalData) {
        this.uploadData = totalData;
        this.downloadData = totalData;
    }
}
