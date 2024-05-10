package com.example.testlauncher.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import com.example.testlauncher.logic.NetworkRepository;
import com.example.testlauncher.tool.LogUtil;

import java.net.NetworkInterface;


public class NetworkchangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //接收到网络变化的广播后，执行检测网络状态
        NetworkRepository.getInstance().checkNetworkStatus(context);
    }
}
