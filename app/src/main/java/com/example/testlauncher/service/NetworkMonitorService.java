package com.example.testlauncher.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.testlauncher.TestLauncherApplication;
import com.example.testlauncher.logic.NetworkRepository;
import com.example.testlauncher.logic.model.NetworkStatusModel;
import com.example.testlauncher.tool.LogUtil;

public class NetworkMonitorService extends Service {

    private static final String TAG = "DEBUG";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return flags;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
