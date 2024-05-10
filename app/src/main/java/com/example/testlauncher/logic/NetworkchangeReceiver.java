package com.example.testlauncher.logic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import com.example.testlauncher.tool.LogUtil;

import java.net.NetworkInterface;


public class NetworkchangeReceiver extends BroadcastReceiver {

    private static final String TAG = "NetworkchangeReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork != null) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
                // 检查网络是否可用
                boolean isConnected = networkCapabilities != null
                        && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
                if (isConnected) {
                    // 网络连接可用，可以进行网络操作
                    Toast.makeText(context, "Network available", Toast.LENGTH_SHORT).show();
                    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        LogUtil.d(TAG, "===当前在使用Mobile流量上网===");
                    } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        LogUtil.d(TAG, "====当前在使用WiFi上网===");
                    } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)) {
                        LogUtil.d(TAG, "=====当前使用蓝牙上网=====");
                    } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        LogUtil.d(TAG, "=====当前使用以太网上网=====");
                    } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                        LogUtil.d(TAG, "===当前使用VPN上网====");
                    } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE)) {
                        LogUtil.d(TAG, "===表示此网络使用Wi-Fi感知传输====");
                    } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_LOWPAN)) {
                        LogUtil.d(TAG, "=====表示此网络使用LoWPAN传输=====");
                    } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_USB)) {
                        LogUtil.d(TAG, "=====表示此网络使用USB传输=====");
                    }
                } else {
                    // 网络连接不可用或无网络
                    Toast.makeText(context, "Network unavailable", Toast.LENGTH_SHORT).show();
                }
            } else {
                // 无网络连接
            }
        } else {
            // 对于Android API 28及以下版本，使用旧的方法
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                // 网络连接可用
            } else {
                // 网络连接不可用或无网络
            }
        }


    }
}
