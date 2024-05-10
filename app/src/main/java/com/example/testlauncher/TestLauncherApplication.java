package com.example.testlauncher;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.testlauncher.network.NetworkchangeReceiver;
import com.example.testlauncher.service.NetworkMonitorService;

public class TestLauncherApplication extends Application {
private static TestLauncherApplication context;
private IntentFilter intentFilter;
private NetworkchangeReceiver networkChangeReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Intent intent = new Intent(this, NetworkMonitorService.class);
        startService(intent);

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkchangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    public static TestLauncherApplication getContext() {
        if (context == null) {
            context = new TestLauncherApplication();
        }
        return context;
    }
}
