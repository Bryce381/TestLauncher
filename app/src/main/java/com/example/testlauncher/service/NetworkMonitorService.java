package com.example.testlauncher.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import androidx.annotation.Nullable;

import com.example.testlauncher.TestLauncherApplication;
import com.example.testlauncher.logic.NetworkRepository;
import com.example.testlauncher.logic.model.NetworkStatusModel;
import com.example.testlauncher.tool.LogUtil;

import java.text.NumberFormat;

public class NetworkMonitorService extends Service {


    private long mCurrentUP=0;//当前的上行流量
    private long mCurrentDOWN=0;//当前的下行流量
    private long mLastUP=0;//上次的上行流量
    private long mLastDOWN=0;//上次的下行流量

    // 进行换算过后的上传下载速率
    private String lUP = "";
    private String lDOWN = "";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub

        //初始化获得流量总量
        mLastUP = TrafficStats.getTotalTxBytes();
        mLastDOWN = TrafficStats.getTotalRxBytes();

        handler.sendEmptyMessage(128);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 定时获取上下行速率
     */
    Handler handler=new Handler(Looper.getMainLooper()){
        public void handleMessage(android.os.Message msg) {

            if(msg.what==666){
                getRate();
            }

            mLastUP = TrafficStats.getTotalTxBytes();
            mLastDOWN = TrafficStats.getTotalRxBytes();
            handler.sendEmptyMessageDelayed(666, 1000);//每秒更新一次
        };



    };
    /**
     * 获取当前的上下行速率（换算之后）
     */
    private void getRate() {

        mCurrentUP = TrafficStats.getTotalTxBytes() - mLastUP;
        mCurrentDOWN = TrafficStats.getTotalRxBytes() - mLastDOWN;

        // 对上传速率进行换算
        if (mCurrentUP >= 1000000) {
            // 字节换算成M,设置精确到小数点后1位
            NumberFormat numberFormat = NumberFormat.getInstance();

            numberFormat.setMaximumFractionDigits(1);

            lUP = numberFormat.format(((float) mCurrentUP) / 1000000) + " MB";

        } else if (mCurrentUP >= 1000) {
            // 字节换算成K,设置精确到小数点后1位
            NumberFormat numberFormat = NumberFormat.getInstance();

            numberFormat.setMaximumFractionDigits(1);

            lUP = numberFormat.format(((float) mCurrentUP) / 1000) + " KB";
        } else {
            // 直接显示字节
            lUP = mCurrentUP + " B";
        }

        // 对下载速率进行换算
        if (mCurrentDOWN >= 1000000) {
            // 字节换算成M,设置精确到小数点后1位
            NumberFormat numberFormat = NumberFormat.getInstance();

            numberFormat.setMaximumFractionDigits(1);

            lDOWN = numberFormat.format(((float) mCurrentDOWN) / 1000000) + " MB";

        } else if (mCurrentDOWN >= 1000) {
            // 字节换算成K,设置精确到小数点后1位
            NumberFormat numberFormat = NumberFormat.getInstance();

            numberFormat.setMaximumFractionDigits(1);

            lDOWN = numberFormat.format(((float) mCurrentDOWN) / 1000) + " KB";
        } else {
            // 直接显示字节
            lDOWN = mCurrentDOWN + " B";
        }
        //当前上行流量，用来存储显示
        String now_up=lUP;
        //当前下行流量，存储显示
        String now_down=lDOWN;

        LogUtil.d("NetworkMonitorService", "now_up:"+now_up+" now_down:"+now_down);


    }

}
