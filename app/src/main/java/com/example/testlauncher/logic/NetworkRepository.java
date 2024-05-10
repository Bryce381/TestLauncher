package com.example.testlauncher.logic;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

import com.example.testlauncher.logic.model.NetworkStatusModel;
import com.example.testlauncher.tool.LogUtil;

public class NetworkRepository {
    private static final String TAG = "DEBUG";

    private static NetworkRepository instance = new NetworkRepository();

    private NetworkRepository() {
    }
    public static NetworkRepository getInstance() {
        if (instance == null) {
            instance = new NetworkRepository();
        }
        return instance;
    }

    public void checkNetworkStatus(Context context) {
        LogUtil.d(TAG, "NetworkStatusModel");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            LogUtil.d(TAG, "NetworkStatusModel---"+"connectivityManager not null");
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork != null) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
                if (networkCapabilities != null) {
                    NetworkStatusModel.setConnected(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET));
                    if(NetworkStatusModel.isConnected()){
                        LogUtil.d(TAG, "Network connected true---"+"network connected type:"+getNetworkType(context));
                    }
                    NetworkStatusModel.setConnectionType(getNetworkType(context));
                } else {
                    NetworkStatusModel.setConnected(false);
                    NetworkStatusModel.setConnectionType("No connection");
                }
            }
        }
        LogUtil.d(TAG, "NetworkStatusModel---"+"NetworkStatusModel end");
    }

    private String getNetworkType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork != null) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
                if (networkCapabilities != null) {
                    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return "WiFi";
                    } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return "Mobile data";
                    } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        return "Ethernet";
                    }
                }
            }
        }
        return "Unknown";
    }

}
